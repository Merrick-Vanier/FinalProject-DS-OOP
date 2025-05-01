package org.example;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Library {
    public List<User> users = new LinkedList<>();
    public List<Media> media = new LinkedList<>();
    public Map<User, List<Media>> loans = new HashMap<>();

    public void search(List<Media> curMedia, String searchBy) {
        //TODO
    }

    public User signUp(String email) {
        //TODO
    }

    public void checkOut(String password) {
        //TODO
    }

    public void exportLibrary(String path, List<Media> curMedia) {
        //TODO
    }

    public List<Media> importLibrary (String path) {
        //TODO
    }
}
