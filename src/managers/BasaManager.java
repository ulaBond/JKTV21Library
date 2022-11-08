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
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("JKTV21LibraryPU");
    private EntityManager em = emf.createEntityManager();
    private EntityTransaction tx = em.getTransaction();

    public void saveBooks(List<Book> books) {
        tx.begin();
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            if(book.getId() == null){
                em.persist(book);
                break;
            }
        }
        tx.commit();
    }  

    public List<Book> loadBooks() {
        List<Book> books = (List<Book>) em.createQuery("SELECT b FROM Book b").getResultList();
        return books;
    }
     
}
