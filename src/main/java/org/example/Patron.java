package org.example;

import java.util.Objects;

public class Patron extends User {
    private int borrowed;
    private String email;

    public Patron(String name, int borrowed, String email) {
        super(name);
        this.borrowed = borrowed;
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Patron patron = (Patron) o;
        return borrowed == patron.borrowed && Objects.equals(email, patron.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), borrowed, email);
    }

    @Override
    public String toString() {
        return "Patron{" +
                "borrowed=" + borrowed +
                ", email='" + email + '\'' +
                '}';
    }

    public int getBorrowed() {
        return borrowed;
    }

    public void setBorrowed(int borrowed) {
        this.borrowed = borrowed;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
