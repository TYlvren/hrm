package org.deepsl.hrm.exception;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CustomExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        e.printStackTrace();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("exception","出错了..");
        modelAndView.setViewName("exception/exception");
        return modelAndView;
    }
}
