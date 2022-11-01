
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
        System.out.println("************ Список книг *************");
        for (int i = 0; i < books.length; i++) {
            Book book1 = books[i];
            
            System.out.printf("Название "+i+1+". %s. ", book1.getTitle());
            for (int j = 0; j < book1.getAuthors().length; j++) {
                System.out.printf("Автор "+j+1+": %s %s.",
                        book1.getAuthors()[j].getFirstname(),
                        book1.getAuthors()[j].getLastname());
                System.out.println("");
            }                        
        }
    }
public Book[] changeBook(Book[] books) {
        System.out.println("Список книг: ");
        this.printListBooks(books);
        System.out.print("Выберите номер книги для редактирования: ");
        int numBookForEdit = scanner.nextInt();scanner.nextLine();
        System.out.println("Название книги: "+books[numBookForEdit - 1].getTitle());
        System.out.println("Изменить название книги? (y/n)");
        String edit = scanner.nextLine();
        if(edit.equals("y")){
            System.out.print("Введите новое название книги: ");
            books[numBookForEdit - 1].setTitle(scanner.nextLine());
        }
        System.out.println("Авторов у книги "+books[numBookForEdit - 1].getAuthors().length);
        System.out.println("Изменить количество авторов? (y/n)");
        edit = scanner.nextLine();
        if(edit.equals("y")){// Меняем количество авторов
            System.out.print("Введите новое количество авторов: ");
            int newCountAuthorsInBook = scanner.nextInt();
            scanner.nextLine();
         // количество авторов может быть больше или меньше.
            if(newCountAuthorsInBook < books[numBookForEdit - 1].getAuthors().length){
              //если меньше, выводим нумерованный список авторов и просим указать какого удалить
               // вычисляем на сколько меньше 
                int deltaAuthors = books[numBookForEdit - 1].getAuthors().length - newCountAuthorsInBook;
                for (int n = 0; n < deltaAuthors; n++) {
                    //удаляем лишних (deltaAuthors) авторов из книги
                    books[numBookForEdit - 1] = deleteAuthorBook(books[numBookForEdit - 1]);
                }
            }else{
                for (int i = 0; i < newCountAuthorsInBook; i++) {
                    //если счетчик больше количесвтва авторов
                    if(i >= books[numBookForEdit - 1].getAuthors().length){
                        // добаляем нового автора в книгу
                        Author newAuthor = new Author();
                        System.out.print("Введите имя автора "+(i+1)+": ");
                        newAuthor.setFirstname(scanner.nextLine());
                        System.out.print("Введите фамилию атора "+(i+1)+": ");
                        newAuthor.setLastname(scanner.nextLine());
                        books[numBookForEdit - 1].addAuthor(newAuthor);
                    }else {
                        // изменяем существующих авторов книги
                        System.out.println(i+1+"-й автор: "
                            +books[numBookForEdit - 1].getAuthors()[i].getFirstname()+" "+
                                   books[numBookForEdit - 1].getAuthors()[i].getLastname());
                        System.out.print("Изменить имя автора? (y/n)");
                        edit = scanner.nextLine();
                        if(edit.equals("y")){
                            System.out.print("Введите новое имя атора: ");
                            books[numBookForEdit - 1].getAuthors()[i].setFirstname(scanner.nextLine());
                        }    
                        System.out.print("Изменить фамилию автора? (y/n)");
                        edit = scanner.nextLine();
                        if(edit.equals("y")){
                            System.out.print("Введите новую фамилию атора: ");
                            books[numBookForEdit - 1].getAuthors()[i].setLastname(scanner.nextLine());
                        }    
                    }
                }
            }
        }
        return books;
        
    }
    private Book changeBookName(Book book) {
        System.out.print("Название книги: ");
        System.out.println(book.getTitle());
        System.out.print("Изменить название книги? (y/n)");
        String edit = scanner.nextLine();
        System.out.println();
        if(edit.equals("y")){
            System.out.print("Введите новое название книги: ");
            book.setTitle(scanner.nextLine());
        }
        return book;
    }

    private Book deleteAuthorBook(Book book) {
        for (int i = 0; i < book.getAuthors().length; i++) {
            System.out.println(
                    i+1+". "+book.getAuthors()[i].getFirstname()+" "+
                            book.getAuthors()[i].getLastname());
        }
        System.out.println("Какого автора удалить? ");
        int numDeleteAuthor = scanner.nextInt();
        scanner.nextLine();
        book.removeAuthor(numDeleteAuthor);
        return book;
    }
}
