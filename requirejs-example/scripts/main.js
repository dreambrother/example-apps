require.config({
    paths: {
        app: 'make-alias-for-this-folder'
    }
})

require(['modules/module-one', 'modules/module-two', 'app/module-three'], function(mOne, mTwo, mThree) {
    console.log(mOne.add(1, 5))
    console.log(mOne.sub(5, 2))
    console.log(mTwo.div(10, 2))
    console.log(mThree.mul(3, 5))
})
