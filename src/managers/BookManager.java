
package managers;

import entity.Author;
import entity.Book;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookManager {
    private Scanner scanner;
    private BasaManager basaManager;
    private AuthorManager authorManager;

    public BookManager() {
        scanner = new Scanner(System.in);
        basaManager = new BasaManager();
        authorManager = new AuthorManager();
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
        System.out.println("Список авторов: ");
        authorManager.printListAuthors();
        System.out.println("Есть ли автор в списке (y/n)");
        String isAuthor = scanner.nextLine();
        if("y".equals(isAuthor)){
            System.out.println("Выберите номер из списка авторов: ");
            int numberAuthor = scanner.nextInt(); scanner.nextLine();
            Author author = basaManager.getAuthor(numberAuthor);//выбираем из списка автора по id
        //получаем объект из базы по id
            return author;
        }else{
            Author newAuthor = new Author();
            System.out.println("Введите имя автора: ");
            newAuthor.setFirstname(scanner.nextLine());
            System.out.println("Введите фамилию автора: ");
            newAuthor.setLastname(scanner.nextLine());
            basaManager.saveAuthor(newAuthor);
            return newAuthor;
        }
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
                System.out.print("Введите новое имя автора: ");
                authors.get(i).setFirstname(scanner.nextLine());
            }    
            System.out.print("Изменить фамилию автора? (y/n)");
            edit = scanner.nextLine();
            if(edit.equals("y")){
                System.out.print("Введите новую фамилию атора: ");
                authors.get(i).setLastname(scanner.nextLine());
            } 
            basaManager.saveAuthor(authors.get(i));
        }
        return authors;
    }

    public void changeBookTitle() {        
        List<Book> listBooks = basaManager.loadBooks();
        printListBooks(listBooks);
        System.out.print("Выберите номер книги для редактирования: ");
        int numBookForEdit = scanner.nextInt();scanner.nextLine();
        Book book = listBooks.get(numBookForEdit-1);
        System.out.println("Название книги: "+book.getTitle());
        System.out.println("Изменить название книги? (y/n)");
        String edit = scanner.nextLine();
        if(edit.equals("y")){
            System.out.print("Введите новое название книги: ");
            book.setTitle(scanner.nextLine());
        }
        basaManager.saveBook(book);
    }
    
//        System.out.println("Авторов у книги "+book.getAuthors().size());
//        System.out.println("Изменить количество авторов? (y/n)");
//        edit = scanner.nextLine();
//        if(edit.equals("y")){
//        // Меняем количество авторов
//            System.out.print("Введите новое количество авторов: ");
//            int newCountAuthorsInBook = scanner.nextInt();
//            scanner.nextLine();
//         // количество авторов может быть больше или меньше.
//            if(newCountAuthorsInBook < book.getAuthors().size()){
//              //если меньше, выводим нумерованный список авторов и просим указать какого удалить
//               // вычисляем на сколько меньше 
//                int deltaAuthors = book.getAuthors().size() - newCountAuthorsInBook;
//                for (int n = 0; n < deltaAuthors; n++) {
//                    //удаляем лишних (deltaAuthors) авторов из книги
//                    int numberAuthorForDelete = deleteNumberAuthorBook(book.getAuthors());
//                    book.removeAuthor(numberAuthorForDelete);
//                }
//            }else{
//                for (int i = 0; i < newCountAuthorsInBook; i++) {
//                    //если счетчик больше количесвтва авторов
//                    if(i >= book.getAuthors().size()){
//                        //выводим список авторов из таблицы DB author
//                        System.out.println("Список авторов: ");
//                        authorManager.printListAuthors();
//                        System.out.println("Есть ли автор в списке (y/n)");
//                        String isAuthor = scanner.nextLine();
//                        if("y".equals(isAuthor)){
//                            System.out.println("Выберите номер из списка авторов: ");
//                            int numberAuthor = scanner.nextInt(); scanner.nextLine();
//                            Author author = basaManager.getAuthor(numberAuthor);//выбираем из списка автора по id
//                        //получаем объект из базы по id
//                        book.addAuthor(author);//добавляем его к авторам книги
//                        }else{
//                            //иначе:
//                            // добаляем нового автора в книгу
//                            Author newAuthor = new Author();
//                            System.out.print("Введите имя автора "+(i+1)+": ");
//                            newAuthor.setFirstname(scanner.nextLine());
//                            System.out.print("Введите фамилию атора "+(i+1)+": ");
//                            newAuthor.setLastname(scanner.nextLine());
//                            basaManager.saveAuthor(newAuthor);                            
//                            book.addAuthor(newAuthor);
//                        }
//                    }else{
//                        book.setAuthors(changeAuthorBook(book.getAuthors()));
//                    }
//                }
//            }
//        }
//        System.out.println("Изменить существующих авторов? (y/n)");
//        edit = scanner.nextLine();
//        if(edit.equals("y")){// Меняем существующих авторов
//            book.setAuthors(changeAuthorBook(book.getAuthors()));
//        }
//        return book;    
//    }

    public void changeBookAuthors() {  
        /*
        void - отсутствует return statement
        выводим список книг, выбираем книгу,
        выводим список авторов книги
        Сколько авторов книги будет? введите новое количество - вводим
            выводим список всех авторов
                есть ли автор в списке? да/нет
                    да: выбираем номера авторов из списка
                    нет: переходим на новый кейс
                                    
        */
        this.printListBooks(basaManager.loadBooks());
        System.out.print("Выберите номер книги для редактирования: ");
        int numberBook = scanner.nextInt();scanner.nextLine();
        Book book = basaManager.getBook(numberBook);
        book.setAuthors(new ArrayList<>());
        System.out.println("Сколько авторов у книги: ");
        int countAuthorInBook = scanner.nextInt();scanner.nextLine();
        System.out.println("Список всех авторов: ");
        authorManager.printListAuthors();
        System.out.println("Есть ли авторы в списке? (y/n): ");
        String isAuthors = scanner.nextLine();
        if("y".equals(isAuthors)){
            for (int i = 0; i < countAuthorInBook; i++) {
                System.out.println("Выберите автора "+(i+1)+": ");
                int numberAuthor = scanner.nextInt();scanner.nextLine();
                book.addAuthor(basaManager.getAuthor(numberAuthor));
                basaManager.saveBook(book);
            }
        }
    }
}

