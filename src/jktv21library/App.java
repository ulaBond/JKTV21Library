
package jktv21library;


import entity.Author;
import entity.Book;
import entity.History;
import entity.Reader;
import java.util.Arrays;
import java.util.Scanner;









public class App {
    private Scanner scanner = new Scanner(System.in);
    Book[] books = new Book[0];
    
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
            System.out.println("6 - список авторов.");
            System.out.println("7 - редактировать книгу.");
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
                    Book book = new Book();
                    System.out.println("Введите название книги: ");
                    book.setTitle(scanner.nextLine());
                    System.out.println("Сколько авторов: ");
                    int countAuthorsInBook = scanner.nextInt();
                    scanner.nextLine();
                    for (int i = 0; i < countAuthorsInBook; i++) {
                        book.addAuthor(createAuthor());
                    }
                    Book[] newBook = Arrays.copyOf(books, books.length+1);
                    newBook[newBook.length-1] = book;
                    books = newBook;
                    break;
                case 2:
                    System.out.println("2 - добавить читателя.");
                    Reader reader = new Reader();
                    break;
                case 3:
                    System.out.println("3 - добавить запись о взятии книги.");
                    History history = new History();
                    break;
                case 4:
                    System.out.println("4 - добавить запись о возврате книги.");
                    break;
                case 5:
                    System.out.println("5 - список книг.");
                    for (int i = 0; i < books.length; i++) {
                        Book book1 = books[i];
                        System.out.println("*************************");
                        System.out.printf("Название "+i+1+". %s. ", book1.getTitle());
                        for (int j = 0; j < book1.getAuthors().length; j++) {
                            System.out.printf("Авторы: "+"%s %s.%n",
                                    book1.getAuthors()[j].getFirstname(),
                                    book1.getAuthors()[j].getLastname());                            
                        }                        
                    }
                    break;
                case 6:
                    System.out.println("6 - список авторов.");
                    break;
                case 7:
                    System.out.println("7 - редактировать книгу.");
                    System.out.println("Введите номер книги, которую нужно редактировать: ");
                    int numBook = scanner.nextInt();
                    scanner.nextLine();
                    for (int i = 0; i < books.length; i++) {
                        if(i == numBook-1){
                            Book book1 = books[i];
                            System.out.println("*************************");
                            System.out.printf(i+". %s. ", book1.getTitle());
                            for (int j = 0; j < book1.getAuthors().length; j++) {
                                System.out.printf("%s %s.%n", book1.getAuthors()[j].getFirstname(), book1.getAuthors()[j].getLastname()); 
                            }
                            System.out.println("Вы будете менять название книги? (yes - 1/no - 0)");
                            int changeTitle = scanner.nextInt();
                            scanner.nextLine();
                            if(changeTitle == 1){                                    
                                System.out.println("Введите новое название книги: ");
                                books[i].setTitle(scanner.nextLine()); 
                            }
                            System.out.println("Вы будете менять авторов книги? (yes - 1/no - 0)");
                            int changeAuthors = scanner.nextInt();
                            scanner.nextLine();
                            if(changeAuthors == 1){
                                System.out.println("Сколько авторов: ");
                                int countAuthorsInBook2 = scanner.nextInt();
                                scanner.nextLine();
                                for (int j = 0; j < countAuthorsInBook2; j++) {
                                    System.out.printf("%s %s.%n",
                                    book1.getAuthors()[j].getFirstname(),
                                    book1.getAuthors()[j].getLastname());
                                    System.out.println("Введите новые данные автора: "); 
                                    book1.addAuthor(createAuthor());
                                } 
                            }      
                        }
                    }     
                
                    break;                    
                default:
                    System.out.println("Выберите номер функции из списка!");
            }
        }while(repeat);
        System.out.println("Chao!");
    }

    private Author createAuthor() {
        Author author = new Author();
        System.out.println("Введите имя автора: ");
        author.setFirstname(scanner.nextLine());
        System.out.println("Введите фамилию автора: ");
        author.setLastname(scanner.nextLine());
        return author;
    }
}
