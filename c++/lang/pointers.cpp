#include <iostream>

int main() 
{
    using namespace std;

    int val = 5;
    int *valRef = &val;

    cout << "referece: " << valRef << endl;
    cout << "value: " << *valRef << endl;
}
