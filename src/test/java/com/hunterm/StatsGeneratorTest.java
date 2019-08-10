package com.hunterm;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class StatsGeneratorTest {

    private static final Logger logger = LoggerFactory.getLogger(StatsGeneratorTest.class);

    private StatsGenerator sut = new StatsGenerator();

    @Test
    public void statsStructureTest() {
        Map<String, Long> stats = sut.fizzBuzzStats("");
        assertEquals("Stats map should have 5 entries", 5, stats.size());
        assertTrue("Stats map should have a fizz count entry", stats.containsKey("fizz"));
        assertTrue("Stats map should have a buzz count entry", stats.containsKey("buzz"));
        assertTrue("Stats map should have a fizzbuzz count entry", stats.containsKey("fizzbuzz"));
        assertTrue("Stats map should have a lucky count entry", stats.containsKey("lucky"));
        assertTrue("Stats map should have a number count entry", stats.containsKey("number"));
    }

    @Test
    public void zeroCountTest() {
        Map<String, Long> stats = sut.fizzBuzzStats("");
        assertEquals("Stats should have a zero count for an empty string", Long.valueOf(0), stats.get("fizz"));
        assertEquals("Stats should have a zero count for an empty string", Long.valueOf(0), stats.get("buzz"));
        assertEquals("Stats should have a zero count for an empty string", Long.valueOf(0), stats.get("fizzbuzz"));
        assertEquals("Stats should have a zero count for an empty string", Long.valueOf(0), stats.get("lucky"));
        assertEquals("Stats should have a zero count for an empty string", Long.valueOf(0), stats.get("number"));
    }

    @Test
    public void nonZeroFizzCountTest(){
        Map<String, Long> stats = sut.fizzBuzzStats("fizz");
        assertEquals("Stats should have a positive fizz count", Long.valueOf(1), stats.get("fizz"));
        verifyZeroCounts(stats, Arrays.asList("buzz", "fizzbuzz", "lucky", "number"));
    }

    @Test
    public void nonZeroBuzzCountTest(){
        Map<String, Long> stats = sut.fizzBuzzStats("buzz");
        assertEquals("Stats should have a positive buzz count", Long.valueOf(1), stats.get("buzz"));
        verifyZeroCounts(stats, Arrays.asList("fizz", "fizzbuzz", "lucky", "number"));
    }

    @Test
    public void nonZeroFizzBuzzCountTest(){
        Map<String, Long> stats = sut.fizzBuzzStats("fizzbuzz");
        assertEquals("Stats should have a positive fizzbuzz count", Long.valueOf(1), stats.get("fizzbuzz"));
        verifyZeroCounts(stats, Arrays.asList("fizz", "buzz", "lucky", "number"));
    }

    @Test
    public void nonZeroLuckyCountTest(){
        Map<String, Long> stats = sut.fizzBuzzStats("lucky");
        assertEquals("Stats should have a positive lucky count", Long.valueOf(1), stats.get("lucky"));
        verifyZeroCounts(stats, Arrays.asList("fizz", "buzz", "fizzbuzz", "number"));
    }

    @Test
    public void nonZeroNumberCountTest(){
        Map<String, Long> stats = sut.fizzBuzzStats("9");
        assertEquals("Stats should have a positive lucky count", Long.valueOf(1), stats.get("number"));
        verifyZeroCounts(stats, Arrays.asList("fizz", "buzz", "fizzbuzz", "lucky"));
    }

    @Test
    public void randomMixTest() {
        Map<String, Long> stats = sut.fizzBuzzStats("fizz fizz fizzbuzz buzz 8 42 lucky fizz fizzbuzz luck 2");
        assertEquals( Long.valueOf(3), stats.get("fizz"));
        assertEquals( Long.valueOf(1), stats.get("buzz"));
        assertEquals( Long.valueOf(2), stats.get("fizzbuzz"));
        assertEquals( Long.valueOf(1), stats.get("lucky"));
        assertEquals( Long.valueOf(3), stats.get("number"));
    }

    private void verifyZeroCounts(Map<String, Long> stats, List<String> shouldBeZero){
        Iterator<String> iter = shouldBeZero.iterator();
        while(iter.hasNext()){
            String chk = iter.next();
            assertEquals("Stats map should have a zero count for " + chk, Long.valueOf(0), stats.get(chk));
        }
    }
}
