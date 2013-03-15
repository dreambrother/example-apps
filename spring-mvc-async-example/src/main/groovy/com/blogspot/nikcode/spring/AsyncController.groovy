package com.blogspot.nikcode.spring

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.context.request.async.DeferredResult

/**
 *
 */
@Controller
class AsyncController {

    @RequestMapping(value = '/async-freeze')
    @ResponseBody
    DeferredResult<String> freeze() {
        Thread.sleep(Long.MAX_VALUE)
    }

    @RequestMapping(value = '/async-test')
    @ResponseBody
    String test() {
        'Async works'
    }
}
