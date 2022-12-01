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
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("JKTV21LibraryPU");//ссылка на файл Persistence для связи с БД
    private final EntityManager em = emf.createEntityManager();//EntityManager 
    private final EntityTransaction tx = em.getTransaction();

    public List<Book> loadBooks() { //вывод всех названий книг и авторов из БД
        List<Book> books = (List<Book>) em.createQuery("SELECT b FROM Book b").getResultList();
        return books;
    }
    public void saveBooks(Book book) {
        tx.begin();//начинается транзакция с БД
        if(book.getId() == null){
            em.persist(book);
        }else{
            em.merge(book);//обновление БД
        }                
        tx.commit();//завершается транзакция с БД
    }        
        
    
    public List<Author> loadAuthors() { //вывод всех авторов из БД
        List<Author> authors = (List<Author>) em.createQuery("SELECT a FROM Author a").getResultList();
        return authors;
    }

    public void saveBook(Book book) {
        tx.begin();
        if(book.getId() == null){
            em.persist(book);
        }else{
            em.merge(book);
        }        
        tx.commit();
    }
    
    public void saveAuthor(Author author) {
        tx.begin();
        if(author.getId() == null){
            em.persist(author);
        }else{
            em.merge(author);
        }        
        tx.commit();
    }

    public Author getAuthor(int numberAuthor) {
        try {
            return em.find(Author.class, (long)numberAuthor); //int numberAuthor преобразовывем в long 
        } catch (Exception e) {
            return new Author();
        }
    }

    Book getBook(int numberBook) {
        try {
            return em.find(Book.class, (long)numberBook); //int numberAuthor преобразовывем в long 
        } catch (Exception e) {
            return new Book();
        }
    }
     
}
