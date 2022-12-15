
package jktv21library;

import entity.Author;
import entity.Book;
import entity.History;
import entity.Reader;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import managers.BasaManager;
import managers.BookManager;
import managers.DataManager;
import managers.HistoryManager;
import managers.ReaderManager;
import managers.SaveManagerInterface;

public class App {
    public static boolean saveToBase;//переменная имеет 2 значения: true - сохранение в базу,
    //false - сохранение в файл
    private final Scanner scanner;
    private final BookManager bookManager;
    private final ReaderManager readerManager;
    private final HistoryManager historyManager;//final - неизменяемые методы
    private final SaveManagerInterface saveManager;
    private List<Book> books;
    private List<Reader> readers;
    private List<History> histories;

    public App() {
        scanner = new Scanner(System.in);
        bookManager = new BookManager();
        readerManager = new ReaderManager();
        historyManager= new HistoryManager();
        if(App.saveToBase){
            saveManager = new DataManager();
        }else{
            saveManager = new BasaManager();
        }
        books = saveManager.loadBooks();
        readers = saveManager.loadReaders();
        histories = saveManager.loadHistories();
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
                    books.add(bookManager.createBook());
                    saveManager.saveBooks(books);
                    break;
                case 2:
                    System.out.println("2 - добавить читателя.");
                    readers.add(readerManager.createReader());
                    saveManager.saveReaders(readers);
                    break;
                case 3:
                    System.out.println("3 - добавить запись о взятии книги.");
                    histories.add(historyManager.takeOnBook(readers,books));
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
                    System.out.println("9 - редактирование названия книги.");
                    books = bookManager.changeBook(books); 
                    saveManager.saveBooks(books);
                    break;
                
                default:
                    System.out.println("Выберите номер функции из списка!");
            }
        }while(repeat);
        System.out.println("Chao!");
    }
    
}