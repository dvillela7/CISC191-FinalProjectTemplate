package edu.sdccd.cisc191.template;

import static org.junit.jupiter.api.Assertions.*;

class ResponseTest {
    private Response response;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        response = new Response("Test");
    }

    @org.junit.jupiter.api.Test
    void testResponse() {
        assertEquals(response.toString(), "PASS");
    }
}