import java.util.Scanner;

public class AdminMenu {
    private final Scanner scanner;

    public AdminMenu(Scanner scanner) {
        this.scanner = scanner;
    }

    private void printMenu() {
        System.out.println("1. Посмотреть все книги");
        System.out.println("2. Поиск книги");
        System.out.println("3. Добавить книгу");
        System.out.println("4. Удалить книгу");
        System.out.println("0. Назад");
    }

    public void start() {
        if(this.scanner != null) {
            int key;
            do {
                printMenu();
                key = this.scanner.nextInt();

                switch (key) {
                    case 1:
                        Facade.browseBooks();
                        break;
                    case 2:
                        Facade.searchBooks();
                        break;
                    case 3:
                        Facade.addBook();
                        break;
                    case 4:
                        Facade.deleteBook();
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Неверный ввод");
                }
            } while (key != 0);
        }
    }
}
