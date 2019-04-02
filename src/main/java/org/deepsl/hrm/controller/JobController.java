package org.deepsl.hrm.controller;

import java.util.List;
import java.util.Map;

import org.deepsl.hrm.domain.Job;
import org.deepsl.hrm.service.HrmService;
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
    @RequestMapping("addJob")
    public String addJob(Model model,String flag,Job job){//job为showAddJob中提交的属性

        switch (flag){
            case "1":{//flag为1时 当点击left.jsp中添加职位按钮后进入  结果直接返回到showAddJob.jsp中即可
                return "job/showAddJob";
            }
            case "2":{//flag为2时 是在showAddJob.jsp中通过form表单隐藏属性进行提交的
                 hrmService.addJob(job);
                 return "forward:/job/selectJob";
            }
        }


        return "job/job";
    }
 
}
