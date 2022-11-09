
package jktv21library;

import entity.Author;
import entity.Book;
import entity.History;
import entity.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import managers.AuthorManager;
import managers.BasaManager;
import managers.BookManager;
import managers.DataManager;
import managers.HistoryManager;
import managers.ReaderManager;

public class App {
    private final Scanner scanner;
    private final BookManager bookManager;
    private final ReaderManager readerManager;
    private final HistoryManager historyManager;//final - неизменяемые методы
    private final AuthorManager authorManager;
    private final BasaManager basaManager;
    //private final DataManager dataManager;
    //private List<Book> books;
    private Reader[] readers;
    private History[] histories;

    public App() {
        //books = new ArrayList<>();
        scanner = new Scanner(System.in);
        bookManager = new BookManager();
        readerManager = new ReaderManager();
        historyManager= new HistoryManager();
        authorManager = new AuthorManager();
        basaManager= new BasaManager();
        //books = basaManager.loadBooks();
        //dataManager = new DataManager();
        //books = dataManager.loadBooksFromFile();  
        //readers = dataManager.loadReadersFromFile();
        //histories = dataManager.loadHistoriesFromFile();
        //testAddBook();
        //testAddReader();
    }   
    
    public void run(){
        boolean repeat = true;
        do {
            System.out.println("функции приложения: ");
            System.out.println("0 - закрыть приложение.");
            System.out.println("1 - добавить книгу.");
            System.out.println("2 - добавить читателя.");
            System.out.println("3 - добавить запись о взятии книги.");
            System.out.println("4 - добавить запись о возврате книги.");
            System.out.println("5 - список книг.");
            System.out.println("6 - список читателей.");
            System.out.println("7 - список histories");
            System.out.println("8 - изменить данные читателя.");
            System.out.println("9 - редактирование названия книги.");
            System.out.println("10 - редактирование авторов.");
            System.out.println("11 - добавление новых записей об авторах.");
            System.out.println("Выберите номер функции: ");
            int task = scanner.nextInt();
            scanner.nextLine();
            switch (task){
                case 0:
                    System.out.println("0 - закрыть приложение.");
                    repeat = false;
                    break;
                case 1:
                    System.out.println("1 - добавить книгу.");
                    Book book = bookManager.createBook();
                    basaManager.saveBook(book);
                    //books.add(bookManager.createBook());
                    //basaManager.saveBooks(books);
                    //dataManager.saveBooksToFile(books);
                    break;
                case 2:
                    System.out.println("2 - добавить читателя.");
                    addReader(readerManager.createReader());
                    //dataManager.saveReadersToFile(readers);
                    break;
                case 3:
                    System.out.println("3 - добавить запись о взятии книги.");
                    addHistories(historyManager.takeOnBook(readers,basaManager.loadBooks()));
                    //dataManager.saveHistoriesToFile(histories);
                    break;
                case 4:
                    System.out.println("4 - добавить запись о возврате книги.");
                    histories = historyManager.returnBook(histories);
                    //dataManager.saveHistoriesToFile(histories);
                    break;
                case 5:
                    System.out.println("5 - список книг.");
                    bookManager.printListBooks(basaManager.loadBooks());
                    break;
                case 6:
                    System.out.println("6 - список читателей.");
                    readerManager.printListReaders(readers);
                    break;
                case 7:
                    System.out.println("7 - список histories.");
                    historyManager.printListReadingBooks(histories);               
                    break;
                case 8:
                    System.out.println("8 - изменить данные читателя.");
                    readers = readerManager.changeReader(readers); 
                    //dataManager.saveReadersToFile(readers);
                    break;
                case 9:
                    System.out.println("9 - редактирование названия книги.");
                    basaManager.saveBook(bookManager.changeBookTitle());
                    //books = bookManager.changeBook(books); 
                    //book = bookManager.changeBook();                    
                    //dataManager.saveBooksToFile(books);
                    break;
                case 10:
                    System.out.println("10 - редактирование авторов.");
                    bookManager.changeBookAuthors());
                    break;
                case 11:
                    System.out.println("11 - добавление новых записей об авторах.");
                    authorManager.saveBook();
                    break;
                default:
                    System.out.println("Выберите номер функции из списка!");
            }
        }while(repeat);
        System.out.println("Chao!");
    }
    
    private void addReader(Reader reader){
        readers = Arrays.copyOf(readers, readers.length+1);
        readers[readers.length-1] = reader;
    }
    
    private void addHistories(History history){
        histories = Arrays.copyOf(histories, histories.length+1);
        histories[histories.length-1] = history;
    }
    
}