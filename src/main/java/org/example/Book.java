package org.example;

import java.util.Objects;

public class Book extends Media {
    private String title;
    private String authors;
    private int published;
    private int pages;

    public Book(int borrowTime, double feeCharge, String topic, String title, String authors, int published, int pages) {
        super(borrowTime, feeCharge, topic);
        this.title = title;
        this.authors = authors;
        this.published = published;
        this.pages = pages;
    }

    @Override
    public String displayInfo() {
        return super.displayInfo();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Book book = (Book) o;
        return published == book.published && pages == book.pages && Objects.equals(title, book.title) && Objects.equals(authors, book.authors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), title, authors, published, pages);
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", authors='" + authors + '\'' +
                ", published=" + published +
                ", pages=" + pages +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public int getPublished() {
        return published;
    }

    public void setPublished(int published) {
        this.published = published;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }
}
