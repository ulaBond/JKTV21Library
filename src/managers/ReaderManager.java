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
        System.out.println("*************** Список читателей *************");
        for (int i = 0; i < readers.length; i++){
            System.out.printf("%d. %s %s. Телефон: %s%n"
                ,i+1
                , readers[i].getFirstname()
                , readers[i].getLastname()
                , readers[i].getPhone()
            );
        }
    }

    public Reader[] changeReader(Reader[] readers) {
        System.out.println("Список читателей");
        this.printListReaders(readers);
        System.out.print("Выберите номер читателя из списка");
        int numberReader = scanner.nextInt(); scanner.nextLine();
        System.out.println("Имя: "+readers[numberReader - 1].getFirstname());
        System.out.print("Заменить? (y/n)");
        String task = scanner.nextLine();
        if("y".equals(task)){
            System.out.println("Введите новое имя читателя: ");
            readers[numberReader - 1].setFirstname(scanner.nextLine());
        }
        System.out.println("Фамилия: "+readers[numberReader - 1].getLastname());
        System.out.print("Заменить? (y/n)");
        task = scanner.nextLine();
        if("y".equals(task)){
            System.out.println("Введите новую фамилию читателя: ");
            readers[numberReader - 1].setLastname(scanner.nextLine());
        }
        System.out.println("Телефон: "+readers[numberReader - 1].getPhone());
        System.out.print("Заменить? (y/n)");
        task = scanner.nextLine();
        if("y".equals(task)){
            System.out.println("Введите новый телефон читателя: ");
            readers[numberReader - 1].setPhone(scanner.nextLine());
        }
        System.out.println("Новые данные читателя: "+readers[numberReader - 1].toString());
        
        return readers;        
    }    
}
