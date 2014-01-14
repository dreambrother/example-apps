#include <iostream>

using namespace std;

int main()
{
    int *val = new int(10);
    cout << "Value: " << *val << endl;
    cout << "Reference: " << val << endl;
    delete val;
    cout << "Value after deletion: " << *val << endl;
    cout << "Reference after deletion: " << val << endl;

    int *arr = new int[3];
    arr[1] = 15;
    cout << "Second element val: " << *(arr + 1) << endl;
    cout << "Second element ref: " << arr + 1 << endl;
    delete[] arr;
    cout << "After deletion \n";
    cout << "Val: " << arr[1] << endl;
    cout << "Ref: " << &arr[1] << endl;
}
