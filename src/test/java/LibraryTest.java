import org.example.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class LibraryTest {

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
}
