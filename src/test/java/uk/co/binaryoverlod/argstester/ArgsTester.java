package uk.co.binaryoverlod.argstester;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class ArgsTester {

    @Test
    void testArgsNormal() {
        assertArrayEquals(new String[]{"onearg"}, ArgsSplitter.split("onearg"));
        assertArrayEquals(new String[]{"two", "args"}, ArgsSplitter.split("two args"));
        assertArrayEquals(new String[]{"three", "single", "args"}, ArgsSplitter.split("three single args"));
    }

    @Test
    void testArgsQuoted() {
        assertArrayEquals(new String[]{"onearg"}, ArgsSplitter.split("\"onearg\""));
        assertArrayEquals(new String[]{"two args"}, ArgsSplitter.split("\"two args\""));
        assertArrayEquals(new String[]{"three single args"}, ArgsSplitter.split("\"three single args\""));
        assertArrayEquals(new String[]{"onearg", "with", "extra"}, ArgsSplitter.split("\"onearg\" with extra"));
        assertArrayEquals(new String[]{"two args", "with", "extra"}, ArgsSplitter.split("\"two args\" with extra"));
    }

    @Test
    void testIgnoreQuotes() {
        assertArrayEquals(new String[]{"hel\"lo", "\""}, ArgsSplitter.split("hel\"lo \""));
    }

    @Test
    void testUnusual() {
        assertArrayEquals(new String[]{"!eval", "CascadeBot.INS.getUser(\"Test\")"}, ArgsSplitter.split("!eval CascadeBot.INS.getUser(\"Test\")"));
        assertArrayEquals(new String[]{"!eval", "CascadeBot.INS.getUser(\"Test\",", "\"123\")"}, ArgsSplitter.split("!eval CascadeBot.INS.getUser(\"Test\", \"123\")"));
    }

}
