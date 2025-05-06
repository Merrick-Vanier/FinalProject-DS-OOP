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
     * Allows
     * @param curMedia The media currently in the library
     * @param searchBy The property that the media is being searched by
     */
    public void search(List<Media> curMedia, String searchBy) {
        //TODO
    }

    /**
     * Allows the user to sign up as a patron of the library provided they use a valid email that has not
     * been used previously by another patron.
     * @param email the email the patron is trying to sign up with
     * @return The patron's information as a User object
     */
    public User signUp(String email) {
        //TODO
    }

    /**
     * Allows an employee to check out a book to a user provided the employee enters their password correctly
     * @param password the password provided by the employee
     * @param patron the person the media is being checked out to
     * @param checkedout the piece of media being checked out
     */
    public void checkOut(String password, User patron, Media checkedout) {
        //TODO
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
