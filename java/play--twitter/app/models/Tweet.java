package models;

import java.util.*;
import javax.persistence.*;

import play.db.jpa.*;

@Entity
public class Tweet extends Model {
    
    public String text;
    public Date creationTime;
    
    @ManyToOne
    public User author;
    
    public Tweet(String text, Date creationTime, User author) {
        this.text = text;
        this.creationTime = creationTime;
        this.author = author;
    }
}
