package chnp.manager.controller;

import chnp.common.utils.StringUtil;
import chnp.manager.common.entity.ResponseJson;
import chnp.manager.common.VerificationCode;
import chnp.manager.common.service.UtilService;
import chnp.manager.service.TsUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
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
						   @RequestParam(name = "vericode") String vc) throws Exception {
		ResponseJson responseJson = new ResponseJson();
		if (StringUtil.isEmpty(un)) responseJson.error("请输入登陆账号");
		else if (StringUtil.isEmpty(up)) responseJson.error("请输入登陆密码");
		else if (StringUtil.isEmpty(vc)) responseJson.error("请输入验证码");
		else {
			Session session = utilService.getSession();
			Object vericode = session.getAttribute("verificationCode");
			if (null == vericode) responseJson.error("请刷新验证码");
			else if (!vc.toLowerCase().equals(vericode)) responseJson.error("验证码不正确");
			else {
				UsernamePasswordToken token = new UsernamePasswordToken(un, up);
				try {
					SecurityUtils.getSubject().login(token);
					responseJson.success("登陆成功");
				}catch (AuthenticationException ae) {
					responseJson.error(ae.getMessage());
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