package chnp.manager.controller;

import chnp.manager.common.VerificationCode;
import chnp.manager.service.TsUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class LoginController {
	private static final Logger log = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private TsUserService tsUserService;

	@RequestMapping("/login")
	public String login() {
		return "login";
	}

	@RequestMapping("/verification")
	public void getCode(VerificationCode verificationCode, HttpServletRequest req, HttpServletResponse resp) {
		verificationCode.generate();
		// 将四位数字的验证码保存到Session中。
		HttpSession session = req.getSession();
		session.setAttribute("code", verificationCode.getCodeString());

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