package com.blogspot.nikcode.grails



import grails.test.mixin.*
import org.junit.*

import static org.junit.Assert.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Item)
class ItemTests {
    
    @Test
    void validateShouldReturnTrueForSuccededValidation() {
        def item = new Item(id: 1, name: 'Item', price: 5000L)
        assert item.validate()
    }

    @Test
    void validateShouldReturnFalseWhenPriceLesserThanZero() {
       def item = new Item(id: 1, name: 'Item', price: -10L)
       assertFalse item.validate()
    }
    
    @Test
    void validateShouldReturnFalseWhenNameIsBlank() {
        def item = new Item(id: 1, name: '', price: 100L)
        assertFalse item.validate()
    }
}

