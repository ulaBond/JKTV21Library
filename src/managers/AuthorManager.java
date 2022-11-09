
package managers;

import entity.Author;
import java.util.List;

public class AuthorManager {
    private BasaManager basaManager;
    
    public AuthorManager(){
        basaManager = new BasaManager();
    }

    void printListAuthors() {
        List<Author> listAuthors = basaManager.loadAuthors();
        for (int i = 0; i < listAuthors.size(); i++) {
            Author author = listAuthors.get(i);
            System.out.printf("%d. %s %s.%n",
            author.getId(),
            author.getFirstname(),
            author.getLastname()
            );
            
        }
    }
    
}
