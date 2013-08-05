describe("Underscore.js API examples", function() {
    it("each() example", function() {
        var list = [1, 2, 3];
        var expected = 6;
        var result = 0;
        _.each(list, function(item) { result += item });
        expect(result).toEqual(expected);
    });
    it("map() example", function() {
        var list = [1, 2, 3];
        var expected = [2, 4, 6];
        var result = _.map(list, function(num) { return num * 2; });
        expect(result).toEqual(expected);
    });
    it("reduce() example", function() {
        var list = [32, 4, 2];
        var expected = 4;
        var result = _.reduce(list, function(a, b) { return a / b });
        expect(result).toEqual(expected);
    });
    it("reduceRight() example", function() {
        var list = [2, 4, 32];
        var expected = 4;
        var result = _.reduceRight(list, function(a, b) { return a / b });
        expect(result).toEqual(expected);
    });
    it("filter() example", function() {
        var list = [1, 2, 3, 4, 5, 6];
        var expected = [2, 4, 6];
        var result = _.filter(list, function(a) { return a % 2 === 0 });
        expect(result).toEqual(expected);
    });
    it("where() example", function() {
        var objs = [{id: 1, name: "foo"}, {id: 2, name: "bar"}, {id: 3, name: "foo"}];
        var expected = [{id: 1, name: "foo"}, {id: 3, name: "foo"}];
        var result = _.where(objs, {name: "foo"});
        expect(result).toEqual(expected);
    });
    it("pluck() example", function() {
        var objs = [{id: 1, name: "foo"}, {id: 2, name: "bar"}, {id: 3, name: "foo"}];
        var expected = [1, 2, 3];
        var result = _.pluck(objs, "id");
        expect(result).toEqual(expected);
    });
    it("range() example", function() {
        var expected = [2, 4, 6];
        expect(_.range(2, 7, 2)).toEqual(expected);
    });
});

