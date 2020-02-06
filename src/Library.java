/*
*
*   Класс описывает обьект библиотеки, которая хранит
*   в себе листы книжек и аккаунтов.
*
* */

import java.io.*;
import java.util.ArrayList;

public class Library {
    private ArrayList<Book> books = new ArrayList<>();
    private ArrayList<Account> accounts = new ArrayList<>();

//    Метод добавления пользователя
    public void addAccount(Account account) {
        accounts.add(account);
    }
//    Геттер аккаунтов
    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    //    Метод добавления книжки
    public void addBook(Book book) {
        books.add(book);
    }
//    Геттер книжек
    public ArrayList<Book> getBooks() {
        return books;
    }
    // Сеттер книжек
    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }

    //    Метод загрузки книжек из файла
    public void importBooks() {
        String line;

        try {
            FileReader fileReader = new FileReader("books.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
                String[] str = line.split(", ");

                Book book = null;

                // Узнаем тип книжки (электронная или бумажная)
                String bookType = str[5];
                bookType.trim();

                // Создаем обьект определённого класса согласно типу книги (Electronic или Paper book's)
                if (bookType.equalsIgnoreCase("Electronic"))
                    book = new ElectronicBook(str[0], str[1], str[2], str[3], str[4]);
                else if (bookType.equalsIgnoreCase("Paper"))
                    book = new PaperBook(str[0], str[1], str[2], str[3], str[4]);

                books.add(book);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    //    Метод загрузки аккаунтов из файла
    public void importAccounts() {
        String line;

        try {
            FileReader fileReader = new FileReader("accounts.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
                String[] str = line.split(", ");

                Account account = null;

                // Узнаем тип аккаунта
                String accountType = str[2];

                // Создаем обьект определённого класса согласно типу аккаунта (Admin или User)
                if (accountType.equalsIgnoreCase("Admin")) {
                    account = new Admin(str[0]);
                    account.setPassword(str[1]); // присваиваем пароль мимо конструктора, в котором проходит MD5 хэширование
                } else {                          // т.к. пароль уже захэширован
                    account = new User(str[0]);
                    account.setPassword(str[1]);
                }
                accounts.add(account);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    // Метод записи аккаунтов в файл
    public void saveAccounts() {
        clearFile("accounts.txt"); // чистим файл
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("accounts.txt"));
            for(int x = 0; x < accounts.size(); x++) {
                Account account = accounts.get(x);
                bufferedWriter.write(account.toString());
                bufferedWriter.newLine();
            }
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    // Метод записи книг в файл
    public void saveBooks() {
        clearFile("books.txt"); // чистим файл
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("books.txt"));

            for(int x = 0; x < books.size(); x++) {
                Book book = books.get(x);
                bufferedWriter.write(book.toString());
                bufferedWriter.newLine();
            }
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    // Метод очистки файла
    private void clearFile(String fileName) {
        try {
            FileOutputStream writer = new FileOutputStream(fileName);
            writer.write(("").getBytes());                              // StackOverFlow
            writer.close();

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
