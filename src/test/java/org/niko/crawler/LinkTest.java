package org.niko.crawler;

import org.junit.Test;

import static org.junit.Assert.*;

public class LinkTest {

    @Test
    public void testEquals() throws Exception {
        assertEquals(new Link("sameurl", 1), new Link("sameurl", 2));
        assertNotEquals(new Link("difurl", 1), new Link("anotherurl", 2));
    }

    @Test
    public void testHashCode() throws Exception {
        assertEquals(new Link("sameurl", 1).hashCode(), new Link("sameurl", 2).hashCode());
        assertNotEquals(new Link("difurl", 1).hashCode(), new Link("anotherurl", 2).hashCode());
    }
}