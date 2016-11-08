package br.com.project.everest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {

    @RequestMapping(value = { "/", "/hello**" }, method = RequestMethod.GET)
    public ModelAndView welcomePage() {
        ModelAndView model = new ModelAndView();
        model.addObject("title", "Spring Security Example");
        model.addObject("message", "This is Hello World!");
        model.setViewName("hello");
        System.out.println("° ° ° ° welcomePage running. model = " + model);
        return model;
    }
}
