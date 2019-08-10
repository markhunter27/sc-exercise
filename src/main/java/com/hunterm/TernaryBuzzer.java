package com.hunterm;

public class TernaryBuzzer implements FizzBuzzer {

    @Override
    public String fizzbuzz(int input) {
        return isLucky(input) ? "lucky" :
                (input % 3 == 0 && input % 5 == 0 ) ? "fizzbuzz" :
                (input % 3 == 0) ? "fizz" :
                (input % 5 == 0) ? "buzz" :
                Integer.toString(input);
    }

    private boolean isLucky(int input) {
        return Integer.toString(input).contains("3");
    }
}
