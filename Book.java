// src/Book.java
public class Book {
    String id, title, author;
    boolean issued;

    public Book(String id, String title, String author, boolean issued) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.issued = issued;
    }

    public String toCSV() {
        return id + "," + title.replace(",", " ") + "," + author.replace(",", " ") + "," + (issued ? "1" : "0");
    }

    public static Book fromCSV(String line) {
        String[] parts = line.split(",", 4);
        if (parts.length < 4) return null;
        return new Book(parts[0], parts[1], parts[2], parts[3].equals("1"));
    }

    public String toString() {
        return String.format("%s | %s | %s | %s", id, title, author, issued ? "Issued" : "Available");
    }
    System.out.println("hello world");
}

