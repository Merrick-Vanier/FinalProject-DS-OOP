package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.*;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class Library {
    public List<User> users = new LinkedList<>();
    public List<Media> media = new LinkedList<>();
    public Map<User, List<Media>> loans = new HashMap<>();

    /**
     * Allows users to search up books based on a topic, the publication date, call number, title or author;
     * returning the media with the searched properties and nothing if no media falls under the search.
     * @param searchBy The property that the media is being searched by
     * @return List of media that falls under the searched property
     */
    public List<Media> search(String searchBy, String property) {
        List<Media> searched = new LinkedList<>();
        switch (searchBy.toLowerCase()) {
            case "topic" -> {
                for (Media media1 : media) {
                    if (media1.getTopic().toLowerCase().contains(property.toLowerCase())) {
                        searched.add(media1);
                    }
                }
            }
            case "date" -> {
                for (Media media1 : media) {
                    if(media1 instanceof Book) {
                        if (((Book) media1).getPublished() == parseInt(property)) {
                            searched.add(media1);
                        }
                    }
                    if(media1 instanceof DVD) {
                        if (((DVD) media1).getReleased() == parseInt(property)) {
                            searched.add(media1);
                        }
                    }
                }
            }
            case "callnumber" -> {
                for (Media media1 : media) {
                    if (media1.getCallNumber() == parseInt(property)) {
                        searched.add(media1);
                    }
                }
            }
            case "title" -> {
                for (Media media1 : media) {
                    if(media1 instanceof Book) {
                        if (((Book) media1).getTitle().toLowerCase().contains(property.toLowerCase())) {
                            searched.add(media1);
                        }
                    }
                    if(media1 instanceof DVD) {
                        if (((DVD) media1).getTitle().toLowerCase().contains(property.toLowerCase())) {
                            searched.add(media1);
                        }
                    }
                }
            }
            case "author" -> {
                for (Media media1 : media) {
                    if(media1 instanceof Book) {
                        if (((Book) media1).getAuthors().toLowerCase().contains(property.toLowerCase())) {
                            searched.add(media1);
                        }
                    }
                    if(media1 instanceof DVD) {
                        if (((DVD) media1).getDirector().toLowerCase().contains(property.toLowerCase())) {
                            searched.add(media1);
                        }
                    }
                }
            }
            default -> {
                System.out.println("Invalid sorting term");
                return searched;
            }
        }

        if (searched.isEmpty()) {
            System.out.println("No items matched the searched for term");
        }
        return searched;
    }

    /**
     * Allows the user to sign up as a patron of the library provided they use a valid email that has not
     * been used previously by another patron, adding their information to the users list.
     * @param email the email the patron is trying to sign up with
     */
    public void signUp(String email, String name) {
        if (email == null || !email.contains("@")) {
            System.out.println("Invalid email");
            return;
        }
        for (User patron : users) {
            if (patron instanceof Patron && ((Patron) patron).getEmail().equals(email)) {
                System.out.println("Email already used by another patron");
                return;
            }
        }
        users.add(new Patron(name, 0, email));
    }

    /**
     * Allows an employee to check out a book to a user provided the employee enters their password correctly
     * @param password the password provided by the employee
     * @param patron the person the media is being checked out to
     * @param checkedOut the piece of media being checked out
     */
    public void checkOut(String password, User patron, Media checkedOut) {
        boolean pasExists = false;
        for (User user : users) {
            if (user instanceof Employee && ((Employee) user).getPassword().equals(password)) {
                pasExists = true;
                break;
            }
        }

        if (!pasExists) {
            System.out.println("Incorrect password");
            return;
        }
        if (patron instanceof Patron) {
            if (((Patron) patron).getBorrowed() >= 5) {
                System.out.println("Patron has borrowed the max amount of things allowed");
                return;
            }
            else {
                ((Patron) patron).setBorrowed(((Patron) patron).getBorrowed() + 1);
            }
        }


        List<Media> checkOuts = new LinkedList<>();
        if (loans.containsKey(patron)) {
            checkOuts = loans.get(patron);
        }
        checkOuts.add(checkedOut);

        loans.putIfAbsent(patron, checkOuts);

        checkedOut.setIsBorrowed(true);
        checkedOut.setBorrower(patron.getId());

    }

    /**
     * Exports a copy of all media information from the library onto a csv file
     * @param path the location of the csv file the media is being copied to
     */
    public void exportLibrary(String path) {
        File file = new File(path);

        try (FileWriter fileWriter = new FileWriter(file)) {
            for (Media media1 : media) {
                fileWriter.write(media1.getBorrowTime() + "," +
                        media1.getFeeCharge() + "," +
                        media1.getTopic() + ",");
                if (media1 instanceof Book) {
                    fileWriter.write(((Book) media1).getTitle() + "," +
                            ((Book) media1).getAuthors() + "," +
                            ((Book) media1).getPublished() + "," +
                            ((Book) media1).getPages() + "," +
                            "Book\n");
                }
                else {
                    fileWriter.write(((DVD) media1).getTitle() + "," +
                            ((DVD) media1).getDirector() + "," +
                            ((DVD) media1).getReleased() + "," +
                            "DVD \n");
                }
            }
        }
        catch (java.io.IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Imports new media information from a csv file adding it to the current pool of media information
     * @param path the location from which the imported csv file is located
     */
    public void importLibrary (String path) {
        File file = new File(path);

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                String[] split = scanner.nextLine().split(",");
                if (split[split.length - 1].equals("Book")) {
                    media.add(new Book(parseInt(split[0]), parseDouble(split[1]), split[2], split[3], split[4], parseInt(split[5]), parseInt(split[6])));
                }
                else {
                    media.add(new DVD(parseInt(split[0]), parseDouble(split[1]), split[2], split[3], split[4], parseInt(split[5])));
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
