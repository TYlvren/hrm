package org.deepsl.hrm.controller;


import java.util.List;

import org.deepsl.hrm.domain.Dept;
import org.deepsl.hrm.domain.Employee;
import org.deepsl.hrm.domain.Job;
import org.deepsl.hrm.service.HrmService;
import org.deepsl.hrm.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**   
 * @Description: 处理员工请求控制器   
 * @version V1.0   
 */
@Controller
@RequestMapping("employee")
public class EmployeeController {
    /**
     * 自动注入HrmService
     * */
    @Autowired
    @Qualifier("hrmService")
    private HrmService hrmService;
    @RequestMapping("selectEmployee")
    public String queryEmployee(Model model){
        List<Employee> employees = hrmService.findEmployee();
        model.addAttribute("employees",employees);
        System.out.println("新加入的测试代码");
        return "employee/employee";
    }
}
