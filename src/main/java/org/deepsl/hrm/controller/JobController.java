package org.deepsl.hrm.controller;

import java.util.List;

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
 * @Description: 处理职位请求控制器  
 * @version V1.0   
 */

@Controller
@RequestMapping("job")
public class JobController {

    /**
     * 自动注入HrmService
     * */
    @Autowired
    @Qualifier("hrmService")
    private HrmService hrmService;

    @RequestMapping("selectJob")
    public String findJob(Model model){
        List<Job> jobs = hrmService.findJob();
        model.addAttribute("jobs",jobs);
        return "job/job";
    }
 
}
