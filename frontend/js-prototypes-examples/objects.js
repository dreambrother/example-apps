function TestClass(x) {
    this.prop = x
    this.constructor.staticMethod = function() { return 'test' }
    this.instanceMethod = function() { return 'test' }
}
TestClass.prototype = {
    prototypeMethod: function() { return 'test' }
}
TestClass.prototype.constructor = TestClass


function TestDescendantClass() {
}
TestDescendantClass.prototype = Object.create(TestClass.prototype) // or new TestClass()
TestDescendantClass.prototype.descPrototypeMethod = function() { return 'test' }
TestDescendantClass.prototype.constructor = TestDescendantClass

