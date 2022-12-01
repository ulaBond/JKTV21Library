
package managers;

import entity.Author;
import java.util.List;
import java.util.Scanner;

public class AuthorManager {
    private final Scanner scanner;
    private BasaManager basaManager;
    
    public AuthorManager(){
        scanner = new Scanner(System.in);
        basaManager = new BasaManager();
    }

    public void printListAuthors() {
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
  public void createAuthor() {
        Author newAuthor = new Author();
        System.out.print("Введите имя автора: ");
        newAuthor.setFirstname(scanner.nextLine());
        System.out.print("Введите фамилию атора: ");
        newAuthor.setLastname(scanner.nextLine());
        basaManager.saveAuthor(newAuthor);
    }

    public void changeAuthor() {
        System.out.println("Список авторов:");
        printListAuthors();
        System.out.print("Выберите автора для изменения: ");
        int authorId = scanner.nextInt();scanner.nextLine();
        Author changeAuthor = basaManager.getAuthor(authorId);
        System.out.println("Имя автора: "+changeAuthor.getFirstname());
        System.out.print("Изменить имя автора? (y/n)");
        String edit = scanner.nextLine();
        if(edit.equals("y")){
            System.out.print("Введите новое имя атора: ");
            changeAuthor.setFirstname(scanner.nextLine());
        }   
        System.out.println("Фамилия автора: "+changeAuthor.getLastname());
        System.out.print("Изменить фамилию автора? (y/n)");
        edit = scanner.nextLine();
        if(edit.equals("y")){
            System.out.print("Введите новую фамилию атора: ");
            changeAuthor.setLastname(scanner.nextLine());
        } 
        basaManager.saveAuthor(changeAuthor);
    }

       
}
