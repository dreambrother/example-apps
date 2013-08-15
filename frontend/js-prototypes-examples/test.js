describe("Base object", function() {
    it("should have properties", function() {
        var sut = new Base(1, 2);
        expect(sut.x).toBe(1);
        expect(sut.y).toBe(2);
    });

    it("should have methods of prototype", function() {
        var sut = new Base(1, 2);
        expect(sut.sum()).toBe(3);
    });

    it("should have static function", function() {
        expect(Base.staticSum(2, 5)).toBe(7);
    });
});

describe("Child object", function() {
    it("should have parent properties", function() {
        var sut = new Child(3, 4);
        expect(sut.x).toBe(3);
        expect(sut.y).toBe(4);
    });

    it("should have own properties", function() {
        var sut = new Child(1, 1, 8);
        expect(sut.z).toBe(8);
    });

    it("should have parent methods", function() {
        var sut = new Child(1, 5);
        expect(sut.sum()).toBe(6);
    });
});
