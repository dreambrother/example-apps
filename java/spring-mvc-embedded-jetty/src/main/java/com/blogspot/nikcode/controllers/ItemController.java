package com.blogspot.nikcode.controllers;

import com.blogspot.nikcode.domain.Item;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;

@Controller
public class ItemController {

    @RequestMapping("/items")
    @ResponseBody
    public List<Item> items() {
        return Arrays.asList(new Item(1, "Item 1"), new Item(2, "Item 2"), new Item(3, "Item 3"));
    }
}
