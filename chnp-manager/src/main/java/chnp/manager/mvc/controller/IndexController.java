package chnp.manager.mvc.controller;

import chnp.manager.mvc.service.TsModuleService;
import chnp.manager.mvc.model.query.TsModuleQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	@Autowired
	private TsModuleService tsModuleService;

	@RequestMapping("/index")
	public String index(Model model) {
		TsModuleQuery tsModuleQuery = new TsModuleQuery();
		tsModuleQuery.setIsParent(1);
		model.addAttribute("modules", tsModuleService.findMenu(tsModuleQuery));

		return "index";
	}

}