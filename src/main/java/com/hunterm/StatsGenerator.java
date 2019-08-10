package com.hunterm;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StatsGenerator {

    public Map<String, Long> fizzBuzzStats(String fbString){

        Supplier<Stream<String>> supplier = () -> Arrays.stream(fbString.split(" "));

        return Stream.of(new Object[][]{
                {"fizz", supplier.get().filter(str -> str.equals("fizz")).count()},
                {"buzz", supplier.get().filter(str -> str.equals("buzz")).count()},
                {"fizzbuzz", supplier.get().filter(str -> str.equals("fizzbuzz")).count()},
                {"lucky", supplier.get().filter(str -> str.equals("lucky")).count()},
                {"number", supplier.get().filter(this::isNumeric).count()}
        }).collect(Collectors.toMap(obArr -> (String) obArr[0], obArr -> (Long) obArr[1]));
    }

    private boolean isNumeric(String str){
        try { Integer.parseInt(str); return true; } catch (NumberFormatException nfe) { return false; }
    }
}
