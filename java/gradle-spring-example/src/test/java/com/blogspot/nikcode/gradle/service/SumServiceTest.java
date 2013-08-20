package com.blogspot.nikcode.gradle.service;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SumServiceTest {

    @Test
    public void shouldSumTwoNumbers() {
        SumService sumService = new SumServiceImpl();
        assertEquals(134, sumService.sum(100, 34));
    }
}
