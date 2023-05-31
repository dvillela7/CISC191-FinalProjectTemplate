package edu.sdccd.cisc191.template;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Request {
    private String id, name;
    private String[] val;

    @JsonIgnore
    private static final ObjectMapper objectMapper = new ObjectMapper();
    public static String toJSON(Request req) throws Exception {
        return objectMapper.writeValueAsString(req);
    }
    public static Request fromJSON(String input) throws Exception{
        return objectMapper.readValue(input, Request.class);
    }
    protected Request() {}

    public Request(String name, String id, String[] val) {
        this.id = id;
        this.name = name;
        this.val = val;
    }

    public String getId() { return this.id; }

    public String getName() { return this.name; }

    public String[] getVal() { return this.val; }

    @Override
    public String toString() {
        return String.format("Request[id=%s]", id);
    }
}