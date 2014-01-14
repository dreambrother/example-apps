#include <iostream>

namespace Foo {
    int foo(int x, int y) {
        return x + y;
    }
}

namespace Bar {
    int foo(int x, int y) {
        return x - y;
    }
}

int main() 
{
    using namespace std;
    cout << "Foo::foo " << Foo::foo(10, 5) << endl;   
    cout << "Bar::foo " << Bar::foo(10, 5) << endl;
}

