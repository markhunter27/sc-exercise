package com.hunterm;

import java.util.Optional;

public class RefactoredBuzzer implements FizzBuzzer {

    @Override
    public String fizzbuzz(int input) {
        String criteriaMatched = isLucky(input).orElse(((input % 3 == 0) ? "fizz" : "") + ((input % 5 == 0) ? "buzz" : ""));
        return criteriaMatched.isEmpty() ? Integer.toString(input) : criteriaMatched;
    }
    private Optional<String> isLucky(int input) {
        return Optional.ofNullable(Integer.toString(input).contains("3") ? "lucky" : null);
    }
}
