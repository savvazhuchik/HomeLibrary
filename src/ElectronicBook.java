/*
*
*   Класс описывает электронную книгу.
*
* */

public class ElectronicBook extends Book {

    public ElectronicBook(String title, String author, String genre, String publicationDate, String ISBN) {
        super(title, author, genre, publicationDate, ISBN, "Electronic");
    }

    @Override
    public String toString() {
        return super.toString() + ", Electronic";
    }
}
