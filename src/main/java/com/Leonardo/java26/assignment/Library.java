package com.Leonardo.java26.assignment;

public class Library {
    private Book[] books;
    private int bookCount;

    private Member[] members;
    private int memberCount;

    // Keeps track of who borrowed each book. null means the book is available.
    private String[] borrowedByMemberId;

    public Library(int maxBooks, int maxMembers) {
        this.books = new Book[maxBooks];
        this.members = new Member[maxMembers];
        this.borrowedByMemberId = new String[maxBooks];
        this.bookCount = 0;
        this.memberCount = 0;
    }

    // 1. Add Book
    public boolean addBook(Book book) {
        if (bookCount >= books.length) {
            return false; // Arrayen är full
        }
        books[bookCount] = book;
        borrowedByMemberId[bookCount] = null; // Ej utlånad från start
        bookCount++;
        return true;
    }

    // 2. Registered member
    public boolean registerMember(Member member) {
        if (memberCount >= members.length) {
            return false; // Fullt
        }
        members[memberCount] = member;
        memberCount++;
        return true;
    }

    // 3. Borrow book
    public boolean borrowBook(String isbn, String memberId) {
        // Find member
        Member member = findMember(memberId);
        if (member == null || !member.canBorrow()) {
            return false;
        }

        // Find book and look if possible to borrow
        for (int i = 0; i < bookCount; i++) {
            if (books[i].isbn().equalsIgnoreCase(isbn) && borrowedByMemberId[i] == null) {
                borrowedByMemberId[i] = memberId;
                member.incrementLoans();
                return true;
            }
        }
        return false;
    }

    // 4. Return back book
    public boolean returnBook(String isbn) {
        for (int i = 0; i < bookCount; i++) {
            if (books[i].isbn().equalsIgnoreCase(isbn) && borrowedByMemberId[i] != null) {
                Member member = findMember(borrowedByMemberId[i]);
                if (member != null) {
                    member.decrementLoans();
                }
                borrowedByMemberId[i] = null;
                return true;
            }
        }
        return false;
    }

    // 5. Search after book
    public void searchBooks(String query) {
        query = query.toLowerCase();
        boolean found = false;
        for (int i = 0; i < bookCount; i++) {
            if (books[i].title().toLowerCase().contains(query) || books[i].author().toLowerCase().contains(query)) {
                String status = (borrowedByMemberId[i] == null) ? "Tillgänglig" : "Utlånad till " + borrowedByMemberId[i];
                System.out.println(books[i].title() + " av " + books[i].author() + " [ISBN: " + books[i].isbn() + "] - " + status);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Inga böcker matchade sökningen.");
        }
    }

    // 6. Show status and all books
    public void listAllBooks() {
        if (bookCount == 0) {
            System.out.println("Inga böcker i biblioteket.");
            return;
        }
        for (int i = 0; i < bookCount; i++) {
            String status = (borrowedByMemberId[i] == null) ? "Tillgänglig" : "Utlånad till medlem " + borrowedByMemberId[i];
            System.out.println((i + 1) + ". " + books[i].title() + " (" + books[i].author() + ") - " + status);
        }
    }

    private Member findMember(String memberId) {
        for (int i = 0; i < memberCount; i++) {
            if (members[i].getId().equalsIgnoreCase(memberId)) {
                return members[i];
            }
        }
        return null;
    }
}



