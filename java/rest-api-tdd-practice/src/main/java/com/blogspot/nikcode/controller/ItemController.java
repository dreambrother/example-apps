package com.blogspot.nikcode.controller;

import com.blogspot.nikcode.domain.Item;
import com.blogspot.nikcode.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * User: nik
 * Date: 3/19/13
 * Time: 12:43 AM
 */
@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping(value = "/items", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public void saveItem(@RequestBody Item item) {
        itemService.save(item);
    }

    @RequestMapping(value = "/items/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Item getItem(@PathVariable("id") long id) {
        return itemService.get(id);
    }
}
