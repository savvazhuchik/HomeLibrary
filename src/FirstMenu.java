/*
*  Класс, который реализует первое меню при запуске программы
* */

import java.util.Scanner;

public class FirstMenu {
    private final Scanner scanner;

    public FirstMenu(Scanner scanner) {
        this.scanner = scanner;
    }

    private void printMenu() {
        System.out.println("1. Авторизация");
        System.out.println("2. Регистрация");
        System.out.println("0. Выход");
    }

    public void start() {
        if(this.scanner != null) {
            int key;
            do {
                printMenu();
                key = this.scanner.nextInt();

                switch (key) {
                    case 1:

                        String result = Facade.authorization();
                        switch (result) {
                            case "user":
                                new UserMenu(new Scanner(System.in)).start();
                                break;
                            case "admin":
                                new AdminMenu(new Scanner(System.in)).start();
                                break;
                            case "false":
                                System.out.println("Неверное имя или пароль");
                                break;
                        }

                        break;
                    case 2:
                        Facade.registration();
                        break;
                    case 0:
                        System.out.println("Выход");
                        break;
                    default:
                        System.out.println("Неверный ввод");
                }
            } while (key != 0);
        }
    }
}
