package com.blogspot.nikcode.spring;

import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author nik
 */
@Controller
public class AppController {

    @Autowired
    private TextGenerator generator;
    
    @RequestMapping("/")
    public ModelAndView index() {
        return new ModelAndView("index", Collections.singletonMap("text", generator.generate()));
    }
}
