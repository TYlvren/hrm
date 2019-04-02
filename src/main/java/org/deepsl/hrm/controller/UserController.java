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
	 * 自动注入UserService
	 * */
	@Autowired
	@Qualifier("hrmService")
	private HrmService hrmService;
		
	/**
	 * 处理登录请求
	 * @param loginname  登录名
	 * @param  password 密码
	 * @return 跳转的视图
	 * */
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
		}
		return mv;
		
	}

	@RequestMapping("user/selectUser")
	public String selectAllUserByPage(@RequestParam(value = "pageNum", defaultValue = "1")int pageNum, Model model) {
		//System.out.println("page:"+ pageNum);
		PageHelper.startPage(pageNum, 5);
		List<User> users = hrmService.findUser();
		PageInfo pageInfo = new PageInfo(users, 5);
		model.addAttribute("pageInfo", pageInfo);
		return "/user/user";
	}
	@RequestMapping("user/updateUser")
	public String updateUserById(@RequestParam(value = "flag", defaultValue = "0")int flag, User user, Model model) {
		//System.out.println("update");
		if (flag == 1) {
			User userById = hrmService.findUserById(user);
			//System.out.println(userById);
			model.addAttribute("user", userById);
			return "/user/showUpdateUser";
		} else {
			System.out.println(user);
			hrmService.modifyUser(user);
			return "forward:/user/selectUser";
		}
	}
    @RequestMapping("user/addUser")
    public String addUser(@RequestParam(value = "flag", defaultValue = "0")int flag, User user) {
		System.out.println("add");
		if (flag == 1) {
			return "/user/showAddUser";
		} else {
			hrmService.addUser(user);
			return "forward:/user/selectUser";
		}

    }

	/**
	 * 处理查询请求
	 * @param pageIndex 请求的是第几页
	 * @param employee 模糊查询参数
	 * @param Model model
	 * */


    /**
     * 处理删除用户请求
     *
     * @param ids 需要删除的id字符串
     */
    @RequestMapping("user/removeUser")
    public String removeUser(String ids) {
		//System.out.println("sssss");
        //System.out.println(ids);
		String[] split = ids.split(",");
		for (String s : split) {
			hrmService.removeUserById(Integer.valueOf(s));
		}
		return "forward:/user/selectUser";
    }

}
