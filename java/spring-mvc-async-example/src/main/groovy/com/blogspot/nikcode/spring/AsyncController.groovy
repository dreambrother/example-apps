package com.blogspot.nikcode.spring

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.context.request.async.DeferredResult

import java.util.concurrent.Callable
import java.util.concurrent.Executors

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
    Callable<String> test() {
        return { 'Async works' } as Callable<String>
    }

    @RequestMapping(value = '/async-deferred-test')
    @ResponseBody
    DeferredResult<String> anotherTest() {
        DeferredResult<String> result = new DeferredResult<String>()
        Executors.newSingleThreadExecutor().submit({ result.result = 'Deferred async works' } as Runnable)
        result
    }
}
