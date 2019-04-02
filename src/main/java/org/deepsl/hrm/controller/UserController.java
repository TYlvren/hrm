package org.deepsl.hrm.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.deepsl.hrm.domain.User;
import org.deepsl.hrm.service.HrmService;
import org.deepsl.hrm.util.common.HrmConstants;
import org.deepsl.hrm.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * 处理用户请求控制器
 * */
@Controller
@RequestMapping("user")
public class UserController {
	
	/**
	 * 自动注入HrmService
	 * */
	@Autowired
	@Qualifier("hrmService")
	private HrmService hrmService;
	/**
	 *
	 * 处理登录请求
	 * @param loginname 登录名
	 * @param password 密码
	 * @param session
	 * @param mv
	 * @return 跳转的视图
	 */
	@RequestMapping(value="/login")
	 public ModelAndView login(@RequestParam("loginname") String loginname,
			 @RequestParam("password") String password,
			 HttpSession session,
			 ModelAndView mv){
		// 调用业务逻辑组件判断用户是否可以登录
		User user = hrmService.login(loginname, password);
		if(user != null){
			// 将用户保存到HttpSession当中
			session.setAttribute(HrmConstants.USER_SESSION, user);
			// 客户端跳转到main页面
			mv.setViewName("redirect:/main");
		}else{
			// 设置登录失败提示信息
			mv.addObject("message", "登录名或密码错误!请重新输入");
			// 服务器内部跳转到登录页面
			mv.setViewName("forward:/loginForm");
			//mv.setViewName("loginForm");
		}
		return mv;
		
	}

	/**
	 * 处理注销用户请求
	 * @param session
	 * @return
	 */
	@RequestMapping("logout.action")
	public String logout(HttpSession session){
		if(session != null){
			session.invalidate();
		}
		return "index";
	}

	/**
	 * 处理查询请求
	 * @param pageIndex 请求的是第几页
	 * @param user 模糊查询参数
	 * @param model model
	 * */
	@RequestMapping("selectUser")
 	public String queryUser(User user,Integer pageIndex,Model model){
		PageModel pageModel = new PageModel();
		if(pageIndex == null){
			pageIndex = 1;
		}
		pageModel.setPageIndex(pageIndex );

		List<User> users = hrmService.findUser(user,pageModel);

		model.addAttribute("users",users);
		model.addAttribute("pageModel",pageModel);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("users",users);

		return "user/user";
	}


	/**
	 * 处理添加用户请求
	 * @param user
	 * @return
	 */
	@RequestMapping("addUser")
	public String addUser(User user){
		hrmService.addUser(user);
		return "user/selectUser";
	}


	/**
	 * 处理删除用户请求
	 * @param String ids 需要删除的id字符串
	 * @param ModelAndView mv
	 * */

}
