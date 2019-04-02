package org.deepsl.hrm.controller;

import java.util.List;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.deepsl.hrm.domain.Dept;
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
 * @Description: 处理部门请求控制器
 * @author   
 * @version V1.0   
 */

@Controller
@RequestMapping("dept")
public class DeptController {

    /**
     * 自动注入HrmService
     * */
    @Autowired
    @Qualifier("hrmService")
    private HrmService hrmService;

    @RequestMapping("selectDept")
    public String queryDept(Model model){
        List<Dept> depts = hrmService.findDept();
        model.addAttribute("depts",depts);
        return "dept/dept";
    }

}
