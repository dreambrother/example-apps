package com.blogspot.nikcode.spring

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

/**
 *
 */
@Controller
class SyncController {

    @RequestMapping(value = '/sync-freeze')
    @ResponseBody
    String freeze() {
        Thread.sleep(Long.MAX_VALUE)
    }

    @RequestMapping(value = '/sync-test')
    @ResponseBody
    String test() {
        'Sync works'
    }
}
