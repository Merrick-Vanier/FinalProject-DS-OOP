package org.example;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Library {
    public List<User> users = new LinkedList<>();
    public List<Media> media = new LinkedList<>();
    public Map<User, List<Media>> loans = new HashMap<>();

    
}
