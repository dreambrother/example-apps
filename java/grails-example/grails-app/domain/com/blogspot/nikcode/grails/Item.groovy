package com.blogspot.nikcode.grails

class Item {

    Long id
    String name
    Long price

    static constraints = {
        price min: 1L
        name blank: false
    }
}
