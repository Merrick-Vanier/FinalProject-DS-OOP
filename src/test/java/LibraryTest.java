import org.example.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class LibraryTest {

    @AfterEach
    public void resetCounts() {
        User.idCount = 1;
        Media.callCount = 1;
    }

    //checkOut Tests

    @Test
    public void checkOut_Test1() {
        Library l1 = new Library();
        l1.users.add(new Patron("Bob", 0,""));
        l1.users.add(new Patron ("Jim", 5, ""));
        l1.users.add(new Employee("Jill", 0, "Cheese"));
        l1.users.add(new Employee("Jill", 0, "Nacho"));
        l1.media.add(new Book(0,0,"","","",0,0));
        l1.checkOut("Nacho", l1.users.get(0), l1.media.get(0));

        Map<User, List<Media>> e1 = new HashMap<>();
        List<Media> list1 = new LinkedList<>();
        list1.add(new Book(0,0,"","","",0,0));
        list1.get(0).setIsBorrowed(true);
        list1.get(0).setBorrower(1);
        list1.get(0).setCallNumber(1);
        User p1 = new Patron("Bob", 1, "");
        p1.setId(1);
        e1.putIfAbsent(p1,list1);

        Assertions.assertAll("",
                () -> Assertions.assertEquals(1, ((Patron) l1.users.get(0)).getBorrowed(), "Checking to see if the User's borrowed count increases"),
                () -> Assertions.assertEquals(true, l1.media.get(0).getIsBorrowed(), "checking if the media updates who borrowed it"),
                () -> Assertions.assertEquals(l1.loans, e1, "Checking if both loan maps are the same")
        );

    }
    @Test
    public void checkOut_Test2() {
        Library l1 = new Library();
        l1.users.add(new Patron("Bob", 0,""));
        l1.users.add(new Patron ("Jim", 5, ""));
        l1.users.add(new Employee("Jill", 0, "Cheese"));
        l1.users.add(new Employee("Jill", 0, "Nacho"));
        l1.media.add(new Book(0,0,"","","",0,0));
        l1.checkOut("Nacho", l1.users.get(1), l1.media.get(0));

        Map<User, List<Media>> e1 = new HashMap<>();

        Assertions.assertAll("",
                () -> Assertions.assertEquals(5, ((Patron) l1.users.get(1)).getBorrowed(), "Checking to see if the User's borrowed count increases"),
                () -> Assertions.assertEquals(false, l1.media.get(0).getIsBorrowed(), "checking if the media updates who borrowed it"),
                () -> Assertions.assertEquals(l1.loans, e1, "Checking if both loan maps are the same")
        );

    }
    @Test
    public void checkOut_Test3() {
        Library l1 = new Library();
        l1.users.add(new Patron("Bob", 0,""));
        l1.users.add(new Patron ("Jim", 5, ""));
        l1.users.add(new Employee("Jill", 0, "Cheese"));
        l1.users.add(new Employee("Jill", 0, "Nacho"));
        l1.media.add(new Book(0,0,"","","",0,0));
        l1.checkOut("Nach", l1.users.get(0), l1.media.get(0));

        Map<User, List<Media>> e1 = new HashMap<>();

        Assertions.assertAll("",
                () -> Assertions.assertEquals(0, ((Patron) l1.users.get(0)).getBorrowed(), "Checking to see if the User's borrowed count increases"),
                () -> Assertions.assertEquals(false, l1.media.get(0).getIsBorrowed(), "checking if the media updates who borrowed it"),
                () -> Assertions.assertEquals(l1.loans, e1, "Checking if both loan maps are the same")
        );

    }

    //signUp Tests

    @Test
    public void signUp_Test1() {
        Library l1 = new Library();

        List<User> u = new LinkedList<>();

        l1.signUp("waidhawd", "Bob");
        Assertions.assertEquals(u, l1.users);
    }

    @Test
    public void signUp_Test2() {
        Library l1 = new Library();
        l1.signUp("wow@wow.com", "Bob");

        List<User> u = new LinkedList<>();
        User u1 = new Patron("Bob", 0, "wow@wow.com");
        u1.setId(1);
        u.add(u1);

        Assertions.assertEquals(u, l1.users);
    }
    @Test
    public void signUp_Test3() {
        Library l1 = new Library();
        User u1 = new Patron("Bob", 0, "wow@wow.com");
        l1.users.add(u1);
        l1.signUp("wow@wow.com", "Carl");

        List<User> u = new LinkedList<>();
        u.add(u1);

        Assertions.assertEquals(u, l1.users);
    }

    //search tests

    @Test
    public void search_Test1() {
        List<Media> expected = new LinkedList<>();
        Media m1 = new Book(0,0,"Math","Math101","Mathguy",1990,0);
        Media m2 = new Book(0,0,"Math","Math201","Mathguy",1991,0);
        Media m3 = new DVD(0,0,"French","French","Bob",1990);
        Media m4 = new DVD(0,0,"English","Shakespeare","Jimbo",2002);

        Library l1 = new Library();
        l1.media.add(m1);
        l1.media.add(m2);
        l1.media.add(m3);
        l1.media.add(m4);
        List<Media> result = l1.search("Topic", "Math");
        expected.add(m1);
        expected.add(m2);

        Assertions.assertEquals(expected, result);
    }
    @Test
    public void search_Test2() {
        List<Media> expected = new LinkedList<>();
        Media m1 = new Book(0,0,"Math","Math101","Mathguy",1990,0);
        Media m2 = new Book(0,0,"Math","Math201","Mathguy",1991,0);
        Media m3 = new DVD(0,0,"French","French","Bob",1990);
        Media m4 = new DVD(0,0,"English","Shakespeare","Jimbo",2002);

        Library l1 = new Library();
        l1.media.add(m1);
        l1.media.add(m2);
        l1.media.add(m3);
        l1.media.add(m4);
        List<Media> result = l1.search("AuthOr", "Jimbo");
        expected.add(m4);

        Assertions.assertEquals(expected, result);
    }
    @Test
    public void search_Test3() {
        List<Media> expected = new LinkedList<>();
        Media m1 = new Book(0,0,"Math","Math101","Mathguy",1990,0);
        Media m2 = new Book(0,0,"Math","Math201","Mathguy",1991,0);
        Media m3 = new DVD(0,0,"French","French","Bob",1990);
        Media m4 = new DVD(0,0,"English","Shakespeare","Jimbo",2002);

        Library l1 = new Library();
        l1.media.add(m1);
        l1.media.add(m2);
        l1.media.add(m3);
        l1.media.add(m4);
        List<Media> result = l1.search("date", "1990");
        expected.add(m1);
        expected.add(m3);

        Assertions.assertEquals(expected, result);
    }
    @Test
    public void search_Test4() {
        List<Media> expected = new LinkedList<>();
        Media m1 = new Book(0,0,"Math","Math101","Mathguy",1990,0);
        Media m2 = new Book(0,0,"Math","Math201","Mathguy",1991,0);
        Media m3 = new DVD(0,0,"French","French","Bob",1990);
        Media m4 = new DVD(0,0,"English","Shakespeare","Jimbo",2002);

        Library l1 = new Library();
        l1.media.add(m1);
        l1.media.add(m2);
        l1.media.add(m3);
        l1.media.add(m4);
        List<Media> result = l1.search("date", "1800");

        Assertions.assertEquals(expected, result);
    }
    @Test
    public void search_Test5() {
        List<Media> expected = new LinkedList<>();
        Media m1 = new Book(0,0,"Math","Math101","Mathguy",1990,0);
        Media m2 = new Book(0,0,"Math","Math201","Mathguy",1991,0);
        Media m3 = new DVD(0,0,"French","French","Bob",1990);
        Media m4 = new DVD(0,0,"English","Shakespeare","Jimbo",2002);

        Library l1 = new Library();
        l1.media.add(m1);
        l1.media.add(m2);
        l1.media.add(m3);
        l1.media.add(m4);
        List<Media> result = l1.search("titlE", "French");
        expected.add(m3);

        Assertions.assertEquals(expected, result);
    }
    @Test
    public void search_Test6() {
        List<Media> expected = new LinkedList<>();
        Media m1 = new Book(0,0,"Math","Math101","Mathguy",1990,0);
        Media m2 = new Book(0,0,"Math","Math201","Mathguy",1991,0);
        Media m3 = new DVD(0,0,"French","French","Bob",1990);
        Media m4 = new DVD(0,0,"English","Shakespeare","Jimbo",2002);

        Library l1 = new Library();
        l1.media.add(m1);
        l1.media.add(m2);
        l1.media.add(m3);
        l1.media.add(m4);
        List<Media> result = l1.search("callnumber", "3");
        expected.add(m3);

        Assertions.assertEquals(expected, result);
    }
    @Test
    public void search_Test7() {
        List<Media> expected = new LinkedList<>();
        Media m1 = new Book(0,0,"Math","Math101","Mathguy",1990,0);
        Media m2 = new Book(0,0,"Math","Math201","Mathguy",1991,0);
        Media m3 = new DVD(0,0,"French","French","Bob",1990);
        Media m4 = new DVD(0,0,"English","Shakespeare","Jimbo",2002);

        Library l1 = new Library();
        l1.media.add(m1);
        l1.media.add(m2);
        l1.media.add(m3);
        l1.media.add(m4);
        List<Media> result = l1.search("wads", "1990");

        Assertions.assertEquals(expected, result);
    }
}
