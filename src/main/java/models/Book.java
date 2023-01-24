package models;

import org.bson.types.ObjectId;

import java.util.List;

public class Book {
    private ObjectId id;
    private String isbn;
    private String title;
    private int stock;
    private Author author;
    private String edition;
    private Publisher publisher;
    private Category category;


    public Book(ObjectId id, String isbn, String title, int stock, Author author, String edition, Publisher publisher, Category category) {
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.stock = stock;
        this.author = author;
        this.edition = edition;
        this.publisher = publisher;
        this.category = category;
    }
    public Book(String isbn, String title, int stock, Author author, String edition, Publisher publisher, Category category) {
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.stock = stock;
        this.author = author;
        this.edition = edition;
        this.publisher = publisher;
        this.category = category;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }


    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }


    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }



    @Override
    public String toString() {

        return "{"
                + "  \"id\":\"" + id
                + "\", \"isbn\":\"" + isbn + "\""
                + ", \"title\":\"" + title + "\""
                + ",  \"stock\":\"" + stock + "\""
                + ",  \"author\":" + author
                + ", \"edition\":\"" + edition + "\""
                + ", \"publisher\":" + publisher
                + ", \"category\":" + category
                + "}";
    }
}
