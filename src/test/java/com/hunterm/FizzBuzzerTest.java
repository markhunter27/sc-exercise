package com.hunterm;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.StringJoiner;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(MockitoJUnitRunner.class)
public class FizzBuzzerTest {

    private static final Logger logger = LoggerFactory.getLogger(FizzBuzzerTest.class);
    private int testRange = 200;

 // private FizzBuzzer sut = new TernaryBuzzer();
    private FizzBuzzer sut = new RefactoredBuzzer();

    @Test
    public void walkingSkeleton() {
        IntStream.rangeClosed(0, testRange).forEach(i ->
            assertNotEmpty(sut.fizzbuzz(i))
        );
    }
    private void assertNotEmpty(String result) {
        assertNotEquals( "method should not return an empty string", "", result);
        assertNotEquals( "method should not return null", null, result);
    }

    @Test
    public void fizzOnDivisableBy3Test(){

        assertEquals("The number 6 should generate the string 'fizz'", "fizz", sut.fizzbuzz(6));

        IntStream.rangeClosed(0, testRange).filter(i -> !isLucky(i)).filter(i -> i % 3 == 0).filter(i -> i % 5 != 0).forEach(i ->
            assertEquals("Non lucky numbers divisable by 3 (and not 5) should generate the string 'fizz' : Failed @ " + i, "fizz", sut.fizzbuzz(i))
        );
    }

    @Test
    public void buzzOnDivisableBy5Test(){

        assertEquals("The number 5 should geberate the string 'buzz'", "buzz", sut.fizzbuzz(5));

        IntStream.rangeClosed(0, testRange).filter(i -> !isLucky(i)).filter(i -> i % 5 == 0).filter(i -> i % 3 != 0).forEach(i ->
            assertEquals("Non lucky numbers divisable by 5 (and not 3) should generate the string 'buzz' : Failed @ " + i, "buzz", sut.fizzbuzz(i))
        );
    }

    @Test
    public void fizzbuzzOnDivisableBy3and5Test(){

        assertEquals("The number 15 should geberate the string 'fizzbuzz'", "fizzbuzz", sut.fizzbuzz(15));

        IntStream.rangeClosed(0, testRange).filter(i -> !isLucky(i)).filter(i -> i % 3 == 0).filter(i -> i % 5 == 0).forEach(i ->
            assertEquals("Non lucky numbers divisable by both 3 and 5 should generate the string 'fizzbuzz' : Failed @ " + i, "fizzbuzz", sut.fizzbuzz(i))
        );
    }

    @Test
    public void luckyOnDigit3Test(){

        assertEquals("The number 135 should generate the string 'lucky'", "lucky", sut.fizzbuzz(132));

        IntStream.rangeClosed(0, testRange).filter(this::isLucky).forEach(i ->
            assertEquals("Numbers containing a digit 3 should generate the string 'lucky', regardless of being divisable by 3 or 5 : Failed @ " + i, "lucky", sut.fizzbuzz(i))
        );
    }

    @Test
    public void echoOnNoMatchTest(){
        IntStream.rangeClosed(0, testRange).filter(i -> !isLucky(i)).filter(i -> i % 3 != 0).filter(i -> i % 5 != 0).forEach(i ->
            assertEquals("Non lucky numbers divisable by neither 3 or 5 should be echoed back in string form : Failed @ " + i, Integer.toString(i), sut.fizzbuzz(i))
        );
    }

    @Test
    public void fullTest(){
        StringJoiner sj = new StringJoiner(" ");
        IntStream.rangeClosed(1, 20).forEach(i ->
            sj.add(sut.fizzbuzz(i))
        );
        logger.info("Full Test : " + sj);
     // assertEquals("1 2 fizz 4 buzz fizz 7 8 fizz buzz 11 fizz 13 14 fizzbuzz 16 17 fizz 19 buzz", sj.toString());
        assertEquals("1 2 lucky 4 buzz fizz 7 8 fizz buzz 11 fizz lucky 14 fizzbuzz 16 17 fizz 19 buzz", sj.toString());
    }

    private boolean isLucky(int i){
        return Integer.toString(i).contains("3");
    }
}
