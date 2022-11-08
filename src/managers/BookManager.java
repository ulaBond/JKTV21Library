
package managers;

import entity.Author;
import entity.Book;
import java.util.List;
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
    public void printListBooks(List<Book> books){
        System.out.println("************ Список книг *************");
        for (int i = 0; i < books.size(); i++) {
            Book book1 = books.get(i);
            
            System.out.printf(i+1+". %s. ", book1.getTitle());
            System.out.print("Авторы: ");
            for (int j = 0; j < books.get(i).getAuthors().size(); j++) {
            System.out.printf("%s %s. ",
                    books.get(i).getAuthors().get(j).getFirstname(),
                    books.get(i).getAuthors().get(j).getLastname());
            }
            System.out.println("");                        
        }
    }
public List<Book> changeBook(List<Book> books) {
        System.out.println("Список книг: ");
        this.printListBooks(books);
        System.out.print("Выберите номер книги для редактирования: ");
        int numBookForEdit = scanner.nextInt();scanner.nextLine();
        System.out.println("Название книги: "+books.get(numBookForEdit - 1).getTitle());
        System.out.println("Изменить название книги? (y/n)");
        String edit = scanner.nextLine();
        if(edit.equals("y")){
            System.out.print("Введите новое название книги: ");
            books.get(numBookForEdit - 1).setTitle(scanner.nextLine());
        }
        System.out.println("Авторов у книги "+books.get(numBookForEdit - 1).getAuthors().size());
        System.out.println("Изменить количество авторов? (y/n)");
        edit = scanner.nextLine();
        if(edit.equals("n")){
            books.get(numBookForEdit - 1).setAuthors(changeAuthorBook(books.get(numBookForEdit-1).getAuthors()));
//            for (int i = 0; i < books[numBookForEdit - 1].getAuthors().length; i++) {
//            
//                        // изменяем существующих авторов книги
//                        System.out.println(i+1+"-й автор: "
//                            +books[numBookForEdit - 1].getAuthors()[i].getFirstname()+" "+
//                                   books[numBookForEdit - 1].getAuthors()[i].getLastname());
//                        System.out.print("Изменить имя автора? (y/n)");
//                        edit = scanner.nextLine();
//                        if(edit.equals("y")){
//                            System.out.print("Введите новое имя атора: ");
//                            books[numBookForEdit - 1].getAuthors()[i].setFirstname(scanner.nextLine());
//                        }    
//                        System.out.print("Изменить фамилию автора? (y/n)");
//                        edit = scanner.nextLine();
//                        if(edit.equals("y")){
//                            System.out.print("Введите новую фамилию атора: ");
//                            books[numBookForEdit - 1].getAuthors()[i].setLastname(scanner.nextLine());
//                        }  
//                }
        }else{// Меняем количество авторов
            System.out.print("Введите новое количество авторов: ");
            int newCountAuthorsInBook = scanner.nextInt();
            scanner.nextLine();
         // количество авторов может быть больше или меньше.
            if(newCountAuthorsInBook < books.get(numBookForEdit - 1).getAuthors().size()){
              //если меньше, выводим нумерованный список авторов и просим указать какого удалить
               // вычисляем на сколько меньше 
                int deltaAuthors = books.get(numBookForEdit - 1).getAuthors().size() - newCountAuthorsInBook;
                for (int n = 0; n < deltaAuthors; n++) {
                    //удаляем лишних (deltaAuthors) авторов из книги
                    int numberAuthorForDelete = deleteNumberAuthorBook(books.get(numBookForEdit-1).getAuthors());
                    books.get(numBookForEdit - 1).removeAuthor(numberAuthorForDelete);
                }
            }else{
                for (int i = 0; i < newCountAuthorsInBook; i++) {
                    //если счетчик больше количесвтва авторов
                    if(i >= books.get(numBookForEdit - 1).getAuthors().size()){
                        // добаляем нового автора в книгу
                        Author newAuthor = new Author();
                        System.out.print("Введите имя автора "+(i+1)+": ");
                        newAuthor.setFirstname(scanner.nextLine());
                        System.out.print("Введите фамилию атора "+(i+1)+": ");
                        newAuthor.setLastname(scanner.nextLine());
                        books.get(numBookForEdit - 1).addAuthor(newAuthor);
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

    private int deleteNumberAuthorBook(List<Author> authors) {
        for (int i = 0; i < authors.size(); i++) {
            System.out.println(
                    i+1+". "+ authors.get(i).getFirstname()+" "+
                              authors.get(i).getLastname());
        }
        System.out.println("Какого автора удалить? ");
        int numDeleteAuthor = scanner.nextInt();
        scanner.nextLine();
        return numDeleteAuthor;
    }
    private List<Author> changeAuthorBook(List<Author> authors){
        for (int i = 0; i < authors.size(); i++) {
            // изменяем существующих авторов книги
            System.out.println(i+1+"-й автор: "
                +authors.get(i).getFirstname()+" "+
                       authors.get(i).getLastname());
            System.out.print("Изменить имя автора? (y/n)");
            String edit = scanner.nextLine();
            if(edit.equals("y")){
                System.out.print("Введите новое имя атора: ");
                authors.get(i).setFirstname(scanner.nextLine());
            }    
            System.out.print("Изменить фамилию автора? (y/n)");
            edit = scanner.nextLine();
            if(edit.equals("y")){
                System.out.print("Введите новую фамилию атора: ");
                authors.get(i).setLastname(scanner.nextLine());
            }    
        }
        return authors;
    }

    public Book changeBook() {
        BasaManager basaManager = new BasaManager();
        List<Book> listBooks = basaManager.loadBooks();
        printListBooks(listBooks);
        System.out.print("Выберите номер книги для редактирования: ");
        int numBookForEdit = scanner.nextInt();scanner.nextLine();
        Book book = editBook(listBooks.get(numBookForEdit-1));
        return book;
    }
        
    public Book editBook(Book book) {
    System.out.println("Название книги: "+book.getTitle());
        System.out.println("Изменить название книги? (y/n)");
        String edit = scanner.nextLine();
        if(edit.equals("y")){
            System.out.print("Введите новое название книги: ");
            book.setTitle(scanner.nextLine());
        }
        System.out.println("Авторов у книги "+book.getAuthors().size());
        System.out.println("Изменить количество авторов? (y/n)");
        edit = scanner.nextLine();
        if(edit.equals("n")){
            book.setAuthors(changeAuthorBook(book.getAuthors()));
        }else{// Меняем количество авторов
            System.out.print("Введите новое количество авторов: ");
            int newCountAuthorsInBook = scanner.nextInt();
            scanner.nextLine();
         // количество авторов может быть больше или меньше.
            if(newCountAuthorsInBook < book.getAuthors().size()){
              //если меньше, выводим нумерованный список авторов и просим указать какого удалить
               // вычисляем на сколько меньше 
                int deltaAuthors = book.getAuthors().size() - newCountAuthorsInBook;
                for (int n = 0; n < deltaAuthors; n++) {
                    //удаляем лишних (deltaAuthors) авторов из книги
                    int numberAuthorForDelete = deleteNumberAuthorBook(book.getAuthors());
                    book.removeAuthor(numberAuthorForDelete);
                }
            }else{
                for (int i = 0; i < newCountAuthorsInBook; i++) {
                    //если счетчик больше количесвтва авторов
                    if(i >= book.getAuthors().size()){
                        //выводим список авторов из таблицы DB author
                        //выбираем из списка автора по id
                        //получаем объект из базы по id
                        //добавляем его к авторам книги
                        
                        
                        // добаляем нового автора в книгу
                        Author newAuthor = new Author();
                        System.out.print("Введите имя автора "+(i+1)+": ");
                        newAuthor.setFirstname(scanner.nextLine());
                        System.out.print("Введите фамилию атора "+(i+1)+": ");
                        newAuthor.setLastname(scanner.nextLine());
                        book.addAuthor(newAuthor);
                    }
                }
            }
        }
        System.out.println("Изменить существующих авторов? (y/n)");
        edit = scanner.nextLine();
        if(edit.equals("y")){// Меняем существующих авторов
            book.setAuthors(changeAuthorBook(book.getAuthors()));
        }
        return book;    
        }
}

