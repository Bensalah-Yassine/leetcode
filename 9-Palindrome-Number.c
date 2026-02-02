bool isPalindrome(int x) {

    if(x == INT_MIN){
        return 0;
    }
    int flag = 0;
    if(x < 0){
        x = -x;
        flag = 1;
    }
    int last = 0;
    int dupe = x;
    int palin = 0;
    while(x > 0){
        last = x % 10;
        if((palin > INT_MAX / 10) || palin == INT_MAX/10 && last > 7) return 0;
        if((palin < INT_MIN / 10) || palin == INT_MIN/10 && last < -8) return 0;

        palin = palin*10 + last;
        x = x/10;
    }

    if(flag)
        return palin == -dupe;
    return palin == dupe;
}