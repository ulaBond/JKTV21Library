/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jktv21library;


import entity.Book;
import entity.History;
import entity.Reader;
import java.util.Scanner;

class App {
    public void run(){
        Scanner scanner = new Scanner(System.in);
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
            System.out.println("Выберите номер функции: ");
            int task = scanner.nextInt();
            scanner.nextLine();
            switch (task){
                case 0:
                    repeat = false;
                    break;
                case 1:
                    System.out.println("1 - добавить книгу.");
                    Book book = new Book();
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
                    break;
                case 6:
                    System.out.println("6 - список авторов.");
                    break;                    
                default:
                    System.out.println("Выберите номер функции из списка!");
            }
        }while(repeat);
        System.out.println("Chao!");
    }
}
