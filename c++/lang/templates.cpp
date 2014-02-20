#include<iostream>
using namespace std;

template <typename T>
class Container
{
    private:
    T value;

    public:
    Container(T val)
    {
        this->value = val;
    }
    T getValue() { return value; }
};

int main()
{
    Container<int> foo(5);
    Container<string> bar("bar");

    cout << foo.getValue() << endl;
    cout << bar.getValue() << endl;
}

