// src/LibraryApp.java
import java.io.*;
import java.util.*;

public class LibraryApp {
    static final String FILE = "books.csv";
    static List<Book> books = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        load();
        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.println("\n1:Add Book  2:List Books  3:Issue Book  4:Return Book  5:Exit");
            String ch = sc.nextLine();
            if(ch.equals("1")) addBook(sc);
            else if(ch.equals("2")) listBooks();
            else if(ch.equals("3")) changeIssue(sc, true);
            else if(ch.equals("4")) changeIssue(sc, false);
            else { save(); System.out.println("Saved & exiting."); break; }
        }
        sc.close();
    }

    static void addBook(Scanner sc) {
        System.out.print("ID: "); String id = sc.nextLine();
        System.out.print("Title: "); String title = sc.nextLine();
        System.out.print("Author: "); String author = sc.nextLine();
        books.add(new Book(id, title, author, false));
        System.out.println("Book added.");
    }

    static void listBooks() {
        if (books.isEmpty()) {
            System.out.println("No books in library.");
            return;
        }
        for(Book b: books) System.out.println(b);
    }

    static void changeIssue(Scanner sc, boolean issue) {
        System.out.print("Enter book ID: ");
        String id = sc.nextLine();
        for(Book b: books) {
            if(b.id.equals(id)) {
                if(issue && !b.issued) { b.issued = true; System.out.println("Issued."); return; }
                if(!issue && b.issued) { b.issued = false; System.out.println("Returned."); return; }
                System.out.println(issue ? "Already issued." : "Book not issued.");
                return;
            }
        }
        System.out.println("Book not found.");
    }

    static void load() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
            String line;
            while((line = br.readLine()) != null) {
                Book b = Book.fromCSV(line);
                if(b != null) books.add(b);
            }
        } catch(IOException e) { /* file may not exist */ }
    }

    static void save() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE))) {
            for(Book b: books) pw.println(b.toCSV());
        } catch(IOException e) { e.printStackTrace(); }
    }
}
