package com.blogspot.nikcode.jersey;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * User: nik
 * Date: 4/28/13
 * Time: 2:23 AM
 */
@Path("/test")
public class Controller {

    @GET
    public String test() {
        return "test";
    }
}
