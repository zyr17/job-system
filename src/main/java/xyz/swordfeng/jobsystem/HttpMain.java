package xyz.swordfeng.jobsystem;

import fi.iki.elonen.NanoHTTPD;

import java.io.IOException;

public class HttpMain extends NanoHTTPD {
    public HttpMain() throws IOException {
        super(8000);
        start();
        System.out.println("server running at 8000");
    }

    public static void main(String[] args) throws IOException {
        new HttpMain();
    }

    @Override
    public Response serve(IHTTPSession session) {
        return newFixedLengthResponse("hello");
    }
}
