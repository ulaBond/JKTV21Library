
package jktv21library;

import entity.Author;
import entity.Book;
import entity.History;
import entity.Reader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private final BasaManager basaManager;
    //private final DataManager dataManager;
    private Book[] books;
    private Reader[] readers;
    private History[] histories;

    public App() {
        scanner = new Scanner(System.in);
        bookManager = new BookManager();
        readerManager = new ReaderManager();
        historyManager= new HistoryManager();
        basaManager= new BasaManager();
        books = new Book[0];
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
            System.out.println("9 - редактирование книги.");
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
                    addBook(bookManager.createBook());  
                    basaManager.saveBooks(books);
                    //dataManager.saveBooksToFile(books);
                    break;
                case 2:
                    System.out.println("2 - добавить читателя.");
                    addReader(readerManager.createReader());
                    //dataManager.saveReadersToFile(readers);
                    break;
                case 3:
                    System.out.println("3 - добавить запись о взятии книги.");
                    addHistories(historyManager.takeOnBook(readers,books));
                    //dataManager.saveHistoriesToFile(histories);
                    break;
                case 4:
                    System.out.println("4 - добавить запись о возврате книги.");
                    histories = historyManager.returnBook(histories);
                    //dataManager.saveHistoriesToFile(histories);
                    break;
                case 5:
                    System.out.println("5 - список книг.");
                    bookManager.printListBooks(books);
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
                    System.out.println("9 - редактирование книги.");
                    books = bookManager.changeBook(books); 
                    //dataManager.saveBooksToFile(books);
                    break;
                default:
                    System.out.println("Выберите номер функции из списка!");
            }
        }while(repeat);
        System.out.println("Chao!");
    }

    private void addBook(Book book){
        books = Arrays.copyOf(books, books.length+1);
        books[books.length-1] = book;
    }
    
    private void addReader(Reader reader){
        readers = Arrays.copyOf(readers, readers.length+1);
        readers[readers.length-1] = reader;
    }
    
    private void addHistories(History history){
        histories = Arrays.copyOf(histories, histories.length+1);
        histories[histories.length-1] = history;
    }
    
    private void testAddBook(){
        Book book = new Book();
        book.setTitle("Voina i mir");
        Author author = new Author("Lev", "Tolstoy");
        book.addAuthor(author);
        this.books = Arrays.copyOf(this.books, this.books.length + 1);
        this.books[this.books.length - 1] = book;
    }
    private void testAddReader(){
        Reader reader = new Reader("Yuliia", "Bond", "1254789");
        readers = Arrays.copyOf(this.readers, this.readers.length + 1);
        readers[readers.length - 1] = reader;
    }

    
}