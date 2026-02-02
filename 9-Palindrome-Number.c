#include<stdbool.h>
bool isPalindrome(int x) {
    if(x < 0){
        return 0;
    }
    int digit ,org;
    long int rev = 0;
    org = x;
    while(x != 0){
        digit = x % 10;
        rev = rev*10 + digit;
        x = x / 10;
    }
    return (org == rev);
    
}