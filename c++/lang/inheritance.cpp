#include <iostream>
using namespace std;

class Base
{
    private:
    int val;

    public:
    Base(int pVal=0): val(pVal)
    {}

    int getVal()
    {
        return val;
    }
};

class Derived: public Base
{
    private:
    int dVal;

    public:
    Derived(int pDVal, int pVal): Base(pVal), dVal(pDVal)
    {}

    int getDVal()
    {
        return dVal;
    }
};

int main()
{
    Base base(5);
    cout << base.getVal() << endl;

    Derived derived(1, 3);
    cout << derived.getVal() << endl;
    cout << derived.getDVal() << endl;
}
