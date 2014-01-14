#include <iostream>

struct Foo 
{
    int foo;
    int bar;
};

int main() 
{
    Foo foo;
    foo.foo = 5;
    foo.bar = 15;
    std::cout << foo.foo << ", " << foo.bar << std::endl;
}
