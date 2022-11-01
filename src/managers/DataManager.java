/* */
package managers;

import entity.Book;
import entity.Reader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import jktv21library.App;

public class DataManager {
    private final String FILENAME_BOOKS = "books/MyBooks";
    private final String FILENAME_READERS = "books/MyReaders";
    private File file;
    public DataManager() {
        File file = new File("books");        
        file.mkdir();
    } 
    
    public void saveBooksToFile(Book[] books){
        try {
            
            FileOutputStream fileOutputStream = new FileOutputStream(FILENAME_BOOKS);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(books);
            objectOutputStream.flush();//происходит запись на жесткий диск, даже если не закрывать весь проект
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DataManager.class.getName()).log(Level.SEVERE, "Нет такого файла", ex);
        } catch (IOException ex) {           
            Logger.getLogger(DataManager.class.getName()).log(Level.SEVERE, "Ошибка ввода-вывода", ex);
        }  
    }
    public Book[] loadBooksFromFile() {
        Book[] books = new Book[0];
        try {
            FileInputStream fileInputStream = new FileInputStream(FILENAME_BOOKS);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            books = (Book[]) objectInputStream.readObject();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DataManager.class.getName()).log(Level.SEVERE, "Нет такого файла", ex);
        } catch (IOException ex) {
            Logger.getLogger(DataManager.class.getName()).log(Level.SEVERE, "Ошибка ввода-вывода", ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DataManager.class.getName()).log(Level.SEVERE, "Нет такого класса", ex);
        }        
        return books;
    }

    public void saveReadersToFile(Reader[] readers) {
        try {
            
            FileOutputStream fileOutputStream = new FileOutputStream(FILENAME_READERS);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(readers);
            objectOutputStream.flush();//происходит запись на жесткий диск, даже если не закрывать весь проект
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DataManager.class.getName()).log(Level.SEVERE, "Нет такого файла", ex);
        } catch (IOException ex) {           
            Logger.getLogger(DataManager.class.getName()).log(Level.SEVERE, "Ошибка ввода-вывода", ex);
        } 
    }
    public Reader[] loadReadersFromFile() {
        Reader[] readers = new Reader[0];
        try {
            FileInputStream fileInputStream = new FileInputStream(FILENAME_READERS);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            readers = (Reader[]) objectInputStream.readObject();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DataManager.class.getName()).log(Level.SEVERE, "Нет такого файла", ex);
        } catch (IOException ex) {
            Logger.getLogger(DataManager.class.getName()).log(Level.SEVERE, "Ошибка ввода-вывода", ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DataManager.class.getName()).log(Level.SEVERE, "Нет такого класса", ex);
        }        
        return readers;
    }
}
