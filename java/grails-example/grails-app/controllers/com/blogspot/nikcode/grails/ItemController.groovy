package com.blogspot.nikcode.grails

class ItemController {

    def index() { 
        [items: Item.list()]
    }
}
