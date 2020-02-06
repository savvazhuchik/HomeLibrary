import java.util.Scanner;

public class UserMenu {
    private final Scanner scanner;

    public UserMenu(Scanner scanner) {
        this.scanner = scanner;
    }

    private void printMenu() {
        System.out.println("1. Посмотреть все книги");
        System.out.println("2. Поиск книги");
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
                    case 0:
                        break;
                    default:
                        System.out.println("Неверный ввод");
                }
            } while (key != 0);
        }
    }
}
