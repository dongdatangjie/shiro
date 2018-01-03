package com.tangjie.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tangjie.model.User;
import com.tangjie.service.UserService;
import com.tangjie.util.ShiroKit;

@Controller
@RequestMapping("/")
public class LoginController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(String username, String password,Model model) {
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username,password);
		String emsg=null;
		try {
			subject.login(token);
		} catch (UnknownAccountException uae) {
			emsg = "用户名不存在";
		} catch (IncorrectCredentialsException ice) {
			emsg = "用户密码有误";
		} catch (AuthenticationException ae) {
			emsg = "其他异常：" + ae.getMessage();
		}
		if (ShiroKit.isEmpty(emsg)) {
			return "redirect:/admin/user/list";
		} else {
			model.addAttribute("emsg", emsg);
			return "/login";
		}
	}
	@RequestMapping(value = "/logout",method = RequestMethod.GET)
    public String logout(Model model,HttpServletRequest request){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        model.addAttribute("msg","您已经退出登录");
        return "redirect:/login";
    }

    @RequestMapping(value = "/unauth")
    public String unAuthorization(){
        return "/unauth";
    }
}
