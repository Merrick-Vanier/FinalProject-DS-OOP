package org.example;

import java.util.Comparator;
import java.util.Objects;

public class DVD extends Media {
    private String title;
    private String director;
    private int released;

    public DVD(int borrowTime, double feeCharge, String topic, String title, String director, int released) {
        super(borrowTime, feeCharge, topic);
        this.title = title;
        this.director = director;
        this.released = released;
    }

    @Override
    public String displayInfo() {
        return super.displayInfo();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        DVD dvd = (DVD) o;
        return released == dvd.released && Objects.equals(title, dvd.title) && Objects.equals(director, dvd.director);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), title, director, released);
    }

    @Override
    public String toString() {
        return "DVD{" +
                "title='" + title + '\'' +
                ", director='" + director + '\'' +
                ", released=" + released +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getReleased() {
        return released;
    }

    public void setReleased(int released) {
        this.released = released;
    }

    public class DVDComparator implements Comparator<Media> {
        public String sorting;

        public DVDComparator(String sorting) {
            this.sorting = sorting;
        }

        @Override
        public int compare(Media o1, Media o2) {
            if (o1 instanceof DVD && o2 instanceof DVD) {
                switch (sorting) {
                    case("title") -> {
                        return (((DVD) o1).title.compareTo(((DVD) o2).title));
                    }
                    default -> {
                        return (((DVD) o1).released - ((DVD) o2).released);
                    }
                }
            }
            return (o1.getCallNumber() - o2.getCallNumber());
        }
    }
}
