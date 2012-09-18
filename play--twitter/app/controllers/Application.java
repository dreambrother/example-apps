package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class Application extends Controller {
    
    public static final String LOGIN_COOKIE = "rememberme";

    public static void index() {
        List<Tweet> tweets = Tweet.find("ORDER BY creationTime DESC").fetch(10);
        render(tweets);
    }
    
    public static void addTweet(String text) {
        Long userId = Long.valueOf(request.cookies.get(LOGIN_COOKIE).value);
        User user = User.findById(userId);
        Tweet tweet = new Tweet(text, new Date(), user);
        tweet.save();
        redirect("/");
    }
    
    public static void addPage() {
        render();
    }
    
    public static void login(String login, String currentUrl) {
        User user = User.find("name = ?", login).first();
        if (user == null) {
            user = new User(login);
            user.save();
        }
        response.setCookie(LOGIN_COOKIE, user.getId().toString(), "30min");
        redirect(currentUrl);
    }
    
    public static void logout() {
        response.removeCookie(LOGIN_COOKIE);
        redirect("/");
    }
}
