package edu.sdccd.cisc191.template;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Response {
    private String msg;

    @JsonIgnore
    private static final ObjectMapper objectMapper = new ObjectMapper();
    public static String toJSON(Response res) throws Exception {
        return objectMapper.writeValueAsString(res);
    }
    public static Response fromJSON(String input) throws Exception{
        return objectMapper.readValue(input, Response.class);
    }
    protected Response() {}

    public Response(String msg) {
        this.msg = msg;
    }

    public String getMsg() { return msg; }

    @Override
    public String toString() {
        return String.format("Response[msg=%s]", msg);
    }
}