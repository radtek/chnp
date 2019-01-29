package chnp.manager.mvc.controller;

import chnp.manager.common.entity.ResponseJson;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/template")
public class TemplateController {

	@RequestMapping("/index")
	public String index(Model model) {

		return "template/index";
	}

	@ResponseBody
	@RequestMapping("/page")
	public String page() {
		ResponseJson responseJson = new ResponseJson();

		return responseJson.toJSONString();
	}

}