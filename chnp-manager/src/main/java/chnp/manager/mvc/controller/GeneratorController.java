package chnp.manager.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/generator")
public class GeneratorController {

	@RequestMapping("/index")
	public String index(Model model) {

		return "index";
	}

}