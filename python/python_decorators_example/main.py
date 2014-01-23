__author__ = 'nik'

def simple_decorator(fn):
    def decorate():
        print "before execution"
        print fn()
        print "after execution"
    return decorate

@simple_decorator
def test1():
    return "execute test1()"

test1()

print "########################################"

def callable_decorator(val):
    print "create decorator with " + val
    def create_decorator(fn):
        def decorate():
            print "before execution with callable decorator"
            print fn()
            print "after execution with callable decorator"
        return decorate
    return create_decorator

@callable_decorator("test2 decorator")
def test2():
    return "execute test2()"

test2()

print "########################################"

def decorator_for_fun_with_param(fun):
    def decorator(foo, bar):
        print foo, bar
    return decorator

@decorator_for_fun_with_param
def test3(foo, bar):
    print "This shouldn't been printed!"

test3('arg1', 'arg2')
