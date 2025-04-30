package org.example;

import java.util.Objects;

public class Employee extends User{
    private long phoneNumber;
    private String password;

    public Employee(String name, int phoneNumber, String password) {
        super(name);
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return phoneNumber == employee.phoneNumber && Objects.equals(password, employee.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phoneNumber, password);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "phoneNumber=" + phoneNumber +
                ", password='" + password + '\'' + super.toString() +
                '}';
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
