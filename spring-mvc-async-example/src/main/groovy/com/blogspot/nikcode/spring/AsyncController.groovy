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
        // don't set result
        new DeferredResult<>()
    }

    @RequestMapping(value = '/async-test')
    @ResponseBody
    DeferredResult<String> test() {
        new DeferredResult<>(result: 'Async works')
    }
}
