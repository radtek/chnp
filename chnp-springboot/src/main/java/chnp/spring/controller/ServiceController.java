package chnp.spring.controller;

import chnp.spring.common.entity.ResponseJson;
import chnp.spring.model.domain.ModuleInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/service")
public class ServiceController {
	private static final Logger log = LoggerFactory.getLogger(ServiceController.class);

	@ResponseBody
	@RequestMapping("/register")
	public String register(@RequestParam(name = "moduleNo")String moduleNo) {
		ResponseJson responseJson = new ResponseJson();

		ModuleInfo moduleInfo = moduleInfoService.getByModuleNo(reqParams.getString(MemeryData.REQ_MODULE_NO));
		if (null == moduleInfo) {
			log.info("指定模块不存在");
			responseJson.setResultCode(MemeryData.CODE_REFUSE_REQUEST);
			responseJson.setErrorInfo("指定模块不存在");
		}else if (1 == moduleInfo.getModuleStatus()) {
			log.info("指定模块不可用");
			responseJson.setResultCode(MemeryData.CODE_REFUSE_REQUEST);
			responseJson.setErrorInfo("指定模块不可用");
		}else {
			if (1 == moduleInfo.getServiceStatus())
				moduleInfoService.register(moduleInfo.getModuleId());
			log.info("注册模块" + moduleInfo.getModuleNo());

			// 返回下次心跳的间隔时长。默认：10秒
			String heartInterval = MemeryData.getConfig(MemeryData.CONST_SERVICE_HEART_INTERVAL);
			responseJson.setHeartInterval(StringUtils.isEmpty(heartInterval) ? 10 : Integer.valueOf(heartInterval));

			responseJson.setResultCode(MemeryData.CODE_OK);
		}
		return null;
	}

	@ResponseBody
	@RequestMapping("/heart")
	public String heart(@RequestParam(name = "moduleNo")String moduleNo) {
		return null;
	}

	@ResponseBody
	@RequestMapping("/cancel")
	public String cancel(@RequestParam(name = "moduleNo")String moduleNo) {
		return null;
	}

}