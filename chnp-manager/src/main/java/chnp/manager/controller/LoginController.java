package chnp.manager.controller;

import chnp.common.utils.StringUtil;
import chnp.manager.common.ResponseJson;
import chnp.manager.common.VerificationCode;
import chnp.manager.common.service.UtilService;
import chnp.manager.model.domain.TsUser;
import chnp.manager.service.TsUserService;
import org.apache.shiro.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class LoginController {
	private static final Logger log = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private UtilService utilService;
	@Autowired
	private TsUserService tsUserService;

	@RequestMapping("/login")
	public String login() {
		return "login";
	}

	@ResponseBody
	@RequestMapping("/logining")
	public String logining(@RequestParam(name = "username") String un,
						   @RequestParam(name = "userpswd") String up,
						   @RequestParam(name = "vericode") String vc,
						   HttpServletResponse resp) throws Exception {
		ResponseJson responseJson = new ResponseJson();
		TsUser tsUser = tsUserService.getByUserName(un);
		if (null == tsUser || StringUtil.isEmpty(tsUser.getUserPswd()))
			responseJson.error("登录名或密码不正确");
		else {
			Session session = utilService.getSession();
			String vericode = (String) session.getAttribute("verificationCode");
			if (!StringUtil.areNotEmpty(vc, vericode) || !vc.toLowerCase().equals(vericode))
				responseJson.error("验证码不正确");
			else {
				String md5 = StringUtil.MD5Encode(tsUser.getUserPswd() + vericode.toLowerCase());
				if (!md5.equals(up)) responseJson.error("登录名或密码不正确");
				else {
					// TODO:
					session.setAttribute("isOnline", 1);

					resp.sendRedirect("/index");
				}
			}
		}
		return responseJson.toJSONString();
	}

	@RequestMapping("/verification")
	public void getCode(VerificationCode verificationCode, HttpServletResponse resp) {
		verificationCode.generate();
		// 将四位数字的验证码保存到Session中。
		Session session = utilService.getSession();
		session.setAttribute("verificationCode", verificationCode.getCodeString().toLowerCase());

		// 禁止图像缓存。
		resp.setHeader("Pragma", "no-cache");
		resp.setHeader("Cache-Control", "no-cache");
		resp.setDateHeader("Expires", 0);
		resp.setContentType("image/jpeg");

		// 将图像输出到Servlet输出流中。
		try {
			ServletOutputStream sos = resp.getOutputStream();
			ImageIO.write(verificationCode.getBufferedImage(), "jpeg", sos);
			sos.close();
		} catch (IOException e) {
			log.error(e.toString(), e);
		}
	}

}