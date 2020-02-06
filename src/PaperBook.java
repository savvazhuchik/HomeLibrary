/*
*
*   Класс описывает бумажную книгу.
*
* */

public class PaperBook extends Book {

        public PaperBook(String title, String author, String genre, String publicationDate, String ISBN) {
            super(title, author, genre, publicationDate, ISBN, "Paper");
        }

    @Override
    public String toString() {
        return super.toString() + ", Paper";
    }
}
