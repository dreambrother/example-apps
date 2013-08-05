import com.blogspot.nikcode.grails.*

class BootStrap {

    def init = { servletContext ->
        if (!Item.count()) {
            new Item(id: 1, name: "Item1", price: 5000L).save(failOnError: true)
            new Item(id: 2, name: "Item2", price: 10000L).save(failOnError: true)
        }
    }
    def destroy = {
    }
}
