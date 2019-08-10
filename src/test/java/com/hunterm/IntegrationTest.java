package com.hunterm;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class IntegrationTest {

    private static final Logger logger = LoggerFactory.getLogger(IntegrationTest.class);

    private FizzBuzzer fb = new RefactoredBuzzer();
    private StatsGenerator sg = new StatsGenerator();

    @Test
    public void fullFizzBuzzWithStatsTest(){

        StringJoiner sj = new StringJoiner(" ");
        IntStream.rangeClosed(1, 20).forEach(i ->
            sj.add(fb.fizzbuzz(i))
        );
        logger.info(sj.toString());
        assertEquals("1 2 lucky 4 buzz fizz 7 8 fizz buzz 11 fizz lucky 14 fizzbuzz 16 17 fizz 19 buzz", sj.toString());

        Map<String, Long> stats = sg.fizzBuzzStats(sj.toString());
    //  stats.keySet().forEach(k -> logger.info(k + ": " + stats.get(k)));

        logger.info("fizz" + ": " + stats.get("fizz"));
        logger.info("buzz" + ": " + stats.get("buzz"));
        logger.info("fizzbuzz" + ": " + stats.get("fizzbuzz"));
        logger.info("lucky" + ": " + stats.get("lucky"));
        logger.info("number" + ": " + stats.get("number"));

        assertEquals( Long.valueOf(4), stats.get("fizz"));
        assertEquals( Long.valueOf(3), stats.get("buzz"));
        assertEquals( Long.valueOf(1), stats.get("fizzbuzz"));
        assertEquals( Long.valueOf(2), stats.get("lucky"));
        assertEquals( Long.valueOf(10), stats.get("number"));
    }
}
