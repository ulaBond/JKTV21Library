
package entity;

import java.io.Serializable;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Book implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//в БД будет автоинкремент в поле Id
    private Long id;
    private String title;
    @OneToMany()//cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE}
    private List<Author> authors = new ArrayList<>();;

    public Book() {
 
    }
    
    public Book(String title, List<Author> authors) {
        this.title = title;
        this.authors = authors;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }   

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
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
               // + ", autors=" + Arrays.toString(authors) 
                + '}';
    }

    public void addAuthor(Author author) {
        //Author[] newAuthors = Arrays.copyOf(authors, authors.length + 1);
        //newAuthors [newAuthors.length-1] = author;
        this.authors.add(author);
    }
    public void removeAuthor(int numberOfAuthor){
        this.authors.remove(numberOfAuthor);
    }
}