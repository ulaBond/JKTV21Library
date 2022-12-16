/* */
package managers;

import entity.Author;
import entity.Book;
import entity.History;
import entity.Reader;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
//import managers.interfaces.SaveManagerInterface;

/* */
public class BasaManager implements SaveManagerInterface {
    private final EntityManagerFactory emf;
    private final EntityManager em; 

    public BasaManager(){
        emf = Persistence.createEntityManagerFactory("JKTV21LibraryPU");//ссылка на файл Persistence для связи с БД
        em = emf.createEntityManager();
    }    
    
    @Override
    public void saveBooks(List<Book> books) {
        em.getTransaction().begin();
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            for (int j = 0; j < book.getAuthors().size(); j++) {
                Author author = book.getAuthors().get(j);
                if(author.getId() == null){
                    em.persist(author);                    
                }else{
                    em.merge(author);
                }                
            }
            if(book.getId() == null){
                em.persist(book);
            }else{
                em.merge(book);
            }
        }
        em.getTransaction().commit();
    }        
        
    @Override
    public List<Book> loadBooks() { //вывод всех названий книг и авторов из БД
        return em.createQuery("SELECT b FROM Book b")
                .getResultList();
    }
    
    @Override
    public void saveReaders(List<Reader> readers) {
        em.getTransaction().begin();
        for (int i = 0; i < readers.size(); i++) {
            Reader reader = readers.get(i);
            if(reader.getId() == null){
                    em.persist(readers);                    
                }else{
                    em.merge(readers);
                }                
            }
        em.getTransaction().commit();
        }     
        
    @Override
    public List<Reader> loadReaders() { //вывод всех названий книг и авторов из БД
        return em.createQuery("SELECT r FROM Book r")
                .getResultList();
    }
    
    @Override
    public void saveHistories(List<History> histories) {
        em.getTransaction().begin();
        for (int i = 0; i < histories.size(); i++) {
            History history = histories.get(i);            
                if(history.getId() == null){
                    em.persist(history);                    
                }else{
                    em.merge(history);
                }                
            }            
        em.getTransaction().commit();
    }        
        
    @Override
    public List<History> loadHistories() { //вывод всех названий книг и авторов из БД
        return em.createQuery("SELECT h FROM Book h")
                .getResultList();
    }     
}
