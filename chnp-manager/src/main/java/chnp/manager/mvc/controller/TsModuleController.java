package chnp.manager.mvc.controller;

import chnp.manager.common.entity.ResponseJson;
import chnp.manager.mvc.model.domain.TsModule;
import chnp.manager.mvc.model.query.TsModuleQuery;
import chnp.manager.mvc.service.TsModuleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/tsmodule")
public class TsModuleController {
	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private TsModuleService tsModuleService;

	@RequestMapping
	public String index(Model model) {
		return "tsmodule/index";
	}

	@RequestMapping("/new")
	public String _new(Model model, TsModule tsModule) {
		model.addAttribute("tsModule", tsModule);
		return "tsmodule/new";
	}

	@RequestMapping("/edit")
	public String _edit(Model model, @RequestParam(name = "id")Integer id) {
		TsModule tsModule = tsModuleService.getById(id);
		if (null == tsModule) tsModule = new TsModule();
		model.addAttribute("tsModule", tsModule);
		return "tsmodule/edit";
	}


	@ResponseBody
	@RequestMapping("/del")
	public String del(@RequestParam(name = "ids") String ids) {
		ResponseJson responseJson = new ResponseJson();

		List<String> fails = new ArrayList<>();
		String[] idList = ids.split(",");
		for (String sId : idList) {
			try {
				tsModuleService.deleteById(Integer.valueOf(sId));
			}catch (Exception e) {
				log.error("ID为" + sId + "的菜单删除失败", e);
				fails.add(sId);
			}
		}

		StringBuilder info = new StringBuilder();
		for (String sId : fails) {
			TsModule tsModule = tsModuleService.getById(Integer.valueOf(sId));
			if (null != tsModule) info.append(",").append(tsModule.getName());
		}

		if (info.length() > 0) {
			responseJson.error(info.substring(1) + " 删除失败！");
		}else responseJson.success("删除成功！");

		return responseJson.toJSONString();
	}

	/**<p>菜单数据获取接口</p>
	 *
	 * @param tsModuleQuery 查询条件
	 * @return 菜单数据列表
	 */
	@ResponseBody
	@RequestMapping("/data")
	public List<TsModule> data(TsModuleQuery tsModuleQuery) {
		tsModuleQuery.setOrderCondition("parent_id, sort");
		return tsModuleService.findAllByCondition(tsModuleQuery);
	}

}