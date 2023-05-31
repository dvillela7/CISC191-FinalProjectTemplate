package edu.sdccd.cisc191.template;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RequestTest {
    private Request request;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        request = new Request("JOHN", "TEST", new String[] {"TEST"});
    }

    @org.junit.jupiter.api.Test
    void testResponse() {
        assertEquals(request.toString(), "PASS");
    }
}