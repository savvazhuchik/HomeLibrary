import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;

public class Facade {
    //      МЕТОДЫ ДЛЯ ПОЛЬЗОВАТЕЛЯ

    // Вывод всех книг библиотеки в консоль
    public static void browseBooks() {
        Library library = new Library();
        library.importBooks();
        ArrayList<Book> books = library.getBooks();

        System.out.println("Все книги в библиотеке:");
        System.out.println("--------------------------------------------------------");
        for (int x = 0; x < books.size(); x++) {
            System.out.println(x+1 + ". " + books.get(x).toString());
        }
        System.out.println("--------------------------------------------------------");
    }
    // Поиск книги по названию или автору
    public static void searchBooks() {
        Library library = new Library();
        library.importBooks();
        ArrayList<Book> books = library.getBooks();

        System.out.println("Введите название книги или автора");
        String stringToSearch = null;

        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            stringToSearch = bufferedReader.readLine();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("--------------------------------------------------------");
        for (int x = 0; x < books.size(); x++) {
            Book book = books.get(x);
            if (book.getTitle().equalsIgnoreCase(stringToSearch) || book.getAuthor().equalsIgnoreCase(stringToSearch)) {
                System.out.println(book.toString());
            }
        }
        System.out.println("--------------------------------------------------------");
    }
    //          МЕТОДЫ ДЛЯ АДМИНИСТРАТОРА
    //  Метод для добавления книги
    public static void addBook() {
        Library library = new Library();
        library.importBooks();

        Book book = null;
        String title = null, author, genre, publicationDate, ISBN, type;
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Введите название книги:");
            title = bufferedReader.readLine();
            System.out.println("Введите автора книги:");
            author = bufferedReader.readLine();
            System.out.println("Введите жанр книги:");
            genre = bufferedReader.readLine();
            System.out.println("Введите дату публикации книги:");
            publicationDate = bufferedReader.readLine();
            System.out.println("Введите ISBN книги:");
            ISBN = bufferedReader.readLine();
            System.out.println("Введите тип книги: (Electronic или Paper)");
            type = bufferedReader.readLine();

            if (type.equalsIgnoreCase("Electronic"))
                book = new ElectronicBook(title, author, genre, publicationDate, ISBN);
            else if (type.equalsIgnoreCase("Paper"))
                book = new PaperBook(title, author, genre, publicationDate, ISBN);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        library.addBook(book);
        library.saveBooks(); // Обновляем файл с книгами (с добавленной книгой)
        System.out.println("Книга " + title + " добавлена в библиотеку");
    }

    // Метод для удаления книги по ISBN
    public static void deleteBook() {
        Library library = new Library();
        library.importBooks();
        ArrayList<Book> books = library.getBooks();

        String searchISBN = null; // ISBN который будем искать

        System.out.println("Введите ISBN книги, которую нужно удалить");

        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            searchISBN = bufferedReader.readLine();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        for (int x = 0; x < books.size(); x++) {
            if (books.get(x).getISBN().equalsIgnoreCase(searchISBN))
                books.remove(x);
        }

        library.setBooks(books);
        library.saveBooks(); // сохраняем файл после удаления книги
    }

    // Регистрация нового аккаунта
    public static void registration() {
        Library library = new Library();
        library.importAccounts();
        ArrayList<Account> accounts = library.getAccounts();

        String name = null;
        String password = null;

        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Введите имя нового аккаунта:");
            name = bufferedReader.readLine();
            System.out.println("Введите пароль:");
            password = bufferedReader.readLine();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        // проверяем, существует ли такой аккаунт уже
        // если есть, шлем нафиг
        boolean isAlready = false;
        for (Account x : accounts) {
           if (x.getUsername().equalsIgnoreCase(name)) isAlready = true;
        }

        if (!isAlready) {
            Account account = new User(name, password);
            library.addAccount(account);
            library.saveAccounts();
            System.out.println("Новый пользователь зарегистрирован");
        } else {
            System.out.println("Пользователь с таким именем уже существует");
        }
    }

    // Авторизация
    public static String authorization() {
        Library library = new Library();
        library.importAccounts();
        ArrayList<Account> accounts = library.getAccounts();

        String name = null;
        String password = null;

        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Введите своё имя:");
            name = bufferedReader.readLine();
            System.out.println("Введите свой пароль:");
            password = bufferedReader.readLine();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        //захэшируем введенный пароль, что бы потом сверить хэш суммы
        String hash = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] digest = md.digest();
            hash = Base64.getEncoder().encodeToString(digest);
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
        }

        boolean isTrue = false;
        boolean isAdmin = false;
        Account account = null;

        String result = "false";

        // Находим юзера с таким именем
        for (Account x : accounts) {
            if (x.getUsername().equalsIgnoreCase(name)) account = x;
        }
            // проверяею нашло ли вообще аккаунт. Если ссылка хранит null, значит аккаунта нет
        if(account == null) {
            result = "false";
        }

        else if (account.getPassword().equals(hash)){    // Сверяем ХЭШ суммы. Если верно, присваеваем
            result = "user";                                 // result-у информацию о том, какой это аккаунт
            if(account.getType().equalsIgnoreCase("Admin")) {
                result = "admin";
            }
        }

        return result;
    }
}











