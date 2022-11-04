/* */
package managers;

import entity.Author;
import entity.Book;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/* */
public class BasaManager {

    public void saveBooks(Book[] books) {
        List<Book> listBook = new ArrayList<>();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JKTV21LibraryPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        for (int i = 0; i < books.length; i++){
            Book book = books[i];
            if(book.getId() == null){
                em.persist(book);
                for (int j = 0; j < book.getAuthors().length; j++) {
                    Author author = book.getAuthors()[j];
                    em.persist(author);                    
                }
                break;
            }            
        }
        tx.commit();        
    }    
}
