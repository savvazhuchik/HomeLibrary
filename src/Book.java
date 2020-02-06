/*
*
*   Абстрактный класс Book. Описывает общие характеристики книг, такие как:
*   название, год издания, автор и т.п.
*
* */

public abstract class Book {

    private String title, author, genre, publicationDate, ISBN, type;

    public Book(String title, String author, String genre, String publicationDate, String ISBN, String type) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.publicationDate = publicationDate;
        this.ISBN = ISBN;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getISBN() {
        return ISBN;
    }
    @Override
    public String toString() {
        return this.title + ", " + this.author + ", " + this.genre + ", "
                + this.publicationDate + ", " + this.ISBN;
    }
}
