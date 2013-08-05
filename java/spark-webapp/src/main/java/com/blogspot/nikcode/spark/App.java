package com.blogspot.nikcode.spark;

import spark.Request;
import spark.Response;
import spark.Route;

import static spark.Spark.*;

public class App {

    public static void main(String[] args) {
        get(new Route("/") {

            @Override
            public Object handle(Request rqst, Response rspns) {
                return "Hello Spark!";
            }
        });
        
        get(new Route("/hello/:name") {

            @Override
            public Object handle(Request rqst, Response rspns) {
                return "Hello, " + rqst.params("name");
            }
        });
        
        get(new Route("/error") {

            @Override
            public Object handle(Request rqst, Response rspns) {
                halt(500);
                return null;
            }
        });
    }
}
