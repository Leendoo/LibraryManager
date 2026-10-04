package com.Leonardo.java26.assignment;

import java.util.Scanner;

public class LibaryManager {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library(100, 50); // Plats för 100 böcker och 50 medlemmar


        library.addBook(new Book("123", "Harry Potter", "J.K. Rowling"));
        library.addBook(new Book("456", "The Hobbit", "J.R.R. Tolkien"));
        library.registerMember(new Member("M1", "Leonardo"));

        boolean running = true;
        while (running) {
            System.out.println("\nBibliotekshanteraren");
            System.out.println("===================");
            System.out.println("1. Lägg till bok");
            System.out.println("2. Registrera medlem");
            System.out.println("3. Låna bok");
            System.out.println("4. Lämna tillbaka bok");
            System.out.println("5. Sök bok (titel eller författare)");
            System.out.println("6. Visa alla böcker och status");
            System.out.println("e. Avsluta");
            System.out.print("Välj ett alternativ: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> {
                    System.out.print("Ange ISBN: ");
                    String isbn = scanner.nextLine();
                    System.out.print("Ange titel: ");
                    String title = scanner.nextLine();
                    System.out.print("Ange författare: ");
                    String author = scanner.nextLine();
                    if (library.addBook(new Book(isbn, title, author))) {
                        System.out.println("Boken lades till!");
                    } else {
                        System.out.println("Biblioteket är fullt.");
                    }
                }
                case "2" -> {
                    System.out.print("Ange medlems-ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Ange namn: ");
                    String name = scanner.nextLine();
                    if (library.registerMember(new Member(id, name))) {
                        System.out.println("Medlem registrerad!");
                    } else {
                        System.out.println("Medlemsregistret är fullt.");
                    }
                }
                case "3" -> {
                    System.out.print("Ange bokens ISBN: ");
                    String isbn = scanner.nextLine();
                    System.out.print("Ange medlems-ID: ");
                    String memberId = scanner.nextLine();
                    if (library.borrowBook(isbn, memberId)) {
                        System.out.println("Lånet genomfört!");
                    } else {
                        System.out.println("Kunde inte låna (boken är redan utlånad, felaktigt ISBN, eller medlemmen har nått max lån).");
                    }
                }
                case "4" -> {
                    System.out.print("Ange bokens ISBN: ");
                    String isbn = scanner.nextLine();
                    if (library.returnBook(isbn)) {
                        System.out.println("Boken är återlämnad!");
                    } else {
                        System.out.println("Kunde inte återlämna boken (felaktigt ISBN eller ej utlånad).");
                    }
                }
                case "5" -> {
                    System.out.print("Sökterm: ");
                    String query = scanner.nextLine();
                    library.searchBooks(query);
                }
                case "6" -> library.listAllBooks();
                case "e", "E" -> {
                    System.out.println("Avslutar programmet. Hej då!");
                    running = false;
                }
                default -> System.out.println("Ogiltigt val, försök igen.");
            }
        }
        scanner.close();
    }
}