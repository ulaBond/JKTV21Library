
package managers;

import entity.Book;
import entity.History;
import entity.Reader;
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
        int 
    }
}
