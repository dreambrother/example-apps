#include<iostream>
using namespace std;

class NotVirtBase
{
    public:
    int getFoo() { return 5; }
};

class NotVirtDerived: public NotVirtBase
{
    public:
    int getFoo() { return 10; }
};

void testSimpleNotVirtCalls()
{
   NotVirtBase* base = new NotVirtBase();
   cout << base->getFoo() << endl;

   NotVirtDerived* derived = new NotVirtDerived();
   cout << derived->getFoo() << endl;

   delete base;
   delete derived;
}

void testAssignedNotVirtCalls()
{
    NotVirtBase* base = (NotVirtBase*)(new NotVirtDerived());
    cout << base->getFoo() << endl;

    delete base;
}

class VirtBase
{
    public:
    virtual int getFoo() { return 5; }
};

class VirtDerived: public VirtBase
{
    public:
    virtual int getFoo() { return 10; }
};

void testVirtCalls()
{
    VirtBase* base = (VirtBase*)(new VirtDerived());
    cout << base->getFoo() << endl;

    delete base;
}

int main()
{
    testSimpleNotVirtCalls();
    testAssignedNotVirtCalls();
    testVirtCalls();
}
