var Base = (function() {
    var Base = function(x, y) {
        this.x = x;
        this.y = y;
    };
    Base.prototype.sum = function() {
        return this.x + this.y;
    }
    Base.staticSum = function(x, y) {
        return x + y;
    }
    return Base;
})();

var Child = (function(Ancestor) {
    var Child = function(x, y, z) {
        Ancestor.call(this, x, y);
        this.z = z;
    }
    Child.prototype = Object.create(Ancestor.prototype);
    Child.prototype.constructor = Child;
    return Child;
})(Base);
