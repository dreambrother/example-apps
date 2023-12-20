package me.nikshmakov.logbook;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class LogController {

    @PostMapping("/api/process")
    public String process(String request) {
        return "OK " + RandomStringUtils.randomAlphanumeric(10) + System.lineSeparator();
    }
}
