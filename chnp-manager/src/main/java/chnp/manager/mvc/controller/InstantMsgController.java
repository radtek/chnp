package chnp.manager.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/instmsg")
public class InstantMsgController {

	@RequestMapping
	public String index() {
		return "instmsg/index";
	}

}