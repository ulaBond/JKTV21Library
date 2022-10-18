
package managers;

import entity.Author;
import entity.Book;
import java.util.Scanner;

public class BookManager {
    private Scanner scanner;

    public BookManager() {
        scanner = new Scanner(System.in);
    }
    
    public Book createBook(){
        Book book = new Book();
        System.out.println("Введите название книги: ");
        book.setTitle(scanner.nextLine());
        System.out.println("Сколько авторов: ");
        int countAuthorsInBook = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < countAuthorsInBook; i++) {
            book.addAuthor(createAuthor());
        }
        return book;
    }
    private Author createAuthor() {
        Author author = new Author();
        System.out.println("Введите имя автора: ");
        author.setFirstname(scanner.nextLine());
        System.out.println("Введите фамилию автора: ");
        author.setLastname(scanner.nextLine());
        return author;
    }
    public void printListBooks(Book[] books){
        for (int i = 0; i < books.length; i++) {
            Book book1 = books[i];
            System.out.println("*************************");
            System.out.printf("Название "+i+". %s. ", book1.getTitle());
            for (int j = 0; j < book1.getAuthors().length; j++) {
                System.out.printf("Автор "+j+": %s %s.%n",
                        book1.getAuthors()[j].getFirstname(),
                        book1.getAuthors()[j].getLastname());                            
            }                        
        }
    }
}
