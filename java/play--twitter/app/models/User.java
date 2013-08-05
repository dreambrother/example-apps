package models;

import java.util.*;
import javax.persistence.*;

import play.db.jpa.*;

@Entity
public class User extends Model {
    
    public String name;
    
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    public List<Tweet> tweets;
    
    public User(String name) {
        this.name = name;
    }
}
