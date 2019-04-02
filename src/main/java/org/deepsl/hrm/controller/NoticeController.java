package org.deepsl.hrm.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.deepsl.hrm.domain.Notice;
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
import org.springframework.web.servlet.ModelAndView;

/**
 * @version V1.0
 * @Description: 处理公告请求控制器
 */

@Controller
@RequestMapping("notice")
public class NoticeController {
    /**
     * 自动注入HrmService
     */
    @Autowired
    @Qualifier("hrmService")
    private HrmService hrmService;

    @RequestMapping("selectNotice")
    public String queryNotice(Model model) {
        List<Notice> notices = hrmService.findNotice();
        model.addAttribute("notices", notices);
        return "notice/notice";
    }

}
