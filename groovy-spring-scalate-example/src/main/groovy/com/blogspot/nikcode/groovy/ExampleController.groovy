package com.blogspot.nikcode.groovy

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.servlet.ModelAndView

/**
 *
 */
@Controller
class ExampleController {

    def service

    @RequestMapping("/example")
    @ResponseBody
    def execute() {
        service.exampleString()
    }

    @RequestMapping("/example-with-view")
    def executeWithView() {
        new ModelAndView("example", [name: "test"])
    }
}
