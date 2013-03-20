require(['module-one', 'module-two'], function(mOne, mTwo) {
    console.log(mOne.add(1, 5))
    console.log(mOne.sub(5, 2))
    console.log(mTwo.div(10, 2))
})
