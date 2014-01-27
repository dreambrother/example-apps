#include <iostream>

class Foo
{
    private:
        int x, y; 
        
    public:
        static const int CONST = 5;

        Foo(int x=1, int y=2)
        {
            this->x = x;
            this->y = y;
        }
        void print()
        {
            std::cout << x << ", " << y << std::endl;
        }
        ~Foo()
        {
            std::cout << "destructor" << std::endl;
        }
};

int main()
{
    Foo* foo = new Foo(3, 5);
    foo->print();
    delete foo;

    Foo* fooWithDefaultParams = new Foo();
    fooWithDefaultParams->print();
    delete fooWithDefaultParams;

    std::cout << Foo::CONST << std::endl;
    
    Foo fooOnStack(4, 8);
    fooOnStack.print();
}
