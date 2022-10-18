/* */
package managers;

import entity.Reader;
import java.util.Scanner;

public class ReaderManager {
    private Scanner scanner;

    public ReaderManager() {
        scanner = new Scanner(System.in);
    }
    
    public Reader createReader(){
        Reader reader = new Reader();
        System.out.println("Введите имя: ");
        reader.setFirstname(scanner.nextLine());
        System.out.println("Введите фамилию: ");
        reader.setFirstname(scanner.nextLine());
        System.out.println("Введите телефон: ");
        reader.setFirstname(scanner.nextLine());
        return reader;
    }
    
    public void printListReaders(Reader[] readers){
        for (int i = 0; i < readers.length; i++){
            System.out.printf("%d. %s %s. Телефон: %s%n"
                ,i+1
                , readers[i].getFirstname()
                , readers[i].getLastname()
                , readers[i].getPhone()
            );
        }
    }
    
}
