describe("Custom object tests", function() {
    var sut = new TestClass()
    
    it("Should have prototype method", function() {        
        var result = sut.prototypeMethod()
        expect(result).toBeDefined()
    })
    
    it("Should have static method", function() {
        var result = TestClass.staticMethod()
        expect(result).toBeDefined()
    })
    
    it("Should be instance of it's class", function() {
        var result = sut instanceof TestClass
        expect(result).toBe(true)
    })
    
    it("Should store property", function() {
        var sutWithProp = new TestClass(1)
        var result = sutWithProp.prop
        expect(result).toBe(1)
    })
    
    it("Should have instance method", function() {
        var result = sut.instanceMethod()
        expect(result).toBeDefined()
    })
})

describe("Descendant object test", function() {
    var descSut = new TestDescendantClass()
    
    it("Should inherit prototype methods", function() {
        var result = descSut.prototypeMethod()
        expect(result).toBeDefined()
    })
    
    it("Should be instanceof it's ancestor", function() {
        var result = descSut instanceof TestClass
        expect(result).toBe(true)
    })
    
    it("Should have own methods", function() {
        var result = descSut.descPrototypeMethod()
        expect(result).toBeDefined()
    })
    
    it("Should not inherit instance methods", function() {
        var callback = function() { descSut.instanceMethod() }
        expect(callback).toThrow()
    })
})

