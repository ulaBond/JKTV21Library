
package managers;

import entity.Book;
import entity.History;
import entity.Reader;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class HistoryManager {
    private final Scanner scanner;
    private final ReaderManager readerManager;
    private final BookManager bookManager;

    public HistoryManager(){
       scanner = new Scanner(System.in);
       readerManager = new ReaderManager();
       bookManager = new BookManager();
    }
    public History takeOnBook(Reader[] readers, Book[] books){
         //из списка читателей выбрать читателя
        //их списка книг выбрать книгу
        //инициировать поля History
        //добавить дату выдачи книги
        System.out.println("Список читателей: ");
        readerManager.printListReaders(readers);
        System.out.println("Выберите номер читателя из списка: ");
        int numberReader = scanner.nextInt();
        scanner.nextLine();
        
        System.out.println("Список книг:");
        bookManager.printListBooks(books);
        System.out.println("Выберите номер книги из списка: ");
        int numberBook = scanner.nextInt();
        scanner.nextLine();
        History history = new History();
        history.setBook(books[numberBook - 1]);
        history.setReader(readers[numberReader - 1]);
        history.setTakeOnBook(new GregorianCalendar().getTime());
        return history;
    }
    public void printListReadingBooks(History[] histories) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        for (int i = 0; i < histories.length; i++) {
            History history = histories[i];
            if(history.getReturnBook() == null){
                System.out.printf("%d. %s. Vydana: %s  Chitaet: %s %s. Telefon: %s%n"
                        ,i+1
                        ,history.getBook().getTitle()
                        ,sdf.format(history.getTakeOnBook())
                        ,history.getReader().getFirstname()
                        ,history.getReader().getLastname()
                        ,history.getReader().getPhone()
                );
            }
           
        }        
    }
    public History[] returnBook(History[] histories){
        // vybrat nomer istorii c vydannoi knigoi iz spiska
        // propisat datu vozvrata v istoriju
        System.out.println("Список выданных книг: ");
        this.printListReadingBooks(histories);
        System.out.println("Выберите номер возвращаемой книги из списка: ");
        int numberHistory = scanner.nextInt(); scanner.nextLine();
        histories[numberHistory - 1].setReturnBook(new GregorianCalendar().getTime());
        return histories;
    }    
}
