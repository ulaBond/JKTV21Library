
package entity;

import java.util.Arrays;

public class Book {
    private String title;
    private Author[] authors = new Author[0];

    public Book() {
    }

    public Book(String title, Author[] authors) {
        this.title = title;
        this.authors = authors;
    }

    public Author[] getAuthors() {
        return authors;
    }

    public void setAuthors(Author[] authors) {
        this.authors = authors;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    @Override
    public String toString() {
        return "Book{"
                + "title=" + title
                + ", autors=" + Arrays.toString(authors) 
                + '}';
    }

    public void addAuthor(Author author) {
        Author[] newAuthors = Arrays.copyOf(authors, authors.length + 1);
        newAuthors [newAuthors.length-1] = author;
        this.authors = newAuthors;
    }
}