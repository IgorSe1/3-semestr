import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        var sc = new Scanner(System.in);
        var registry = new UserRegistry();

        System.out.print("Відновити базу користувачів з файлу? (так/ні): ");
        var answer = sc.nextLine().trim().toLowerCase();
        if (answer.equals("так")) {
            System.out.print("Шлях до файлу: ");
            var path = sc.nextLine().trim();
            registry.loadFromFile(path);
        }

        while (true) {
            System.out.println();
            System.out.println("1 - Зареєструвати користувача");
            System.out.println("2 - Увійти в систему");
            System.out.println("3 - Вийти з системи (за ID)");
            System.out.println("4 - Перевірити чи є користувач");
            System.out.println("5 - Видалити користувача (за ID)");
            System.out.println("6 - Кількість унікальних користувачів");
            System.out.println("7 - Показати всіх користувачів");
            System.out.println("8 - Отримати список (LinkedList)");
            System.out.println("9 - Сортувати (лямбда)");
            System.out.println("10 - Фільтр (лямбда)");
            System.out.println("11 - Зберегти у файл");
            System.out.println("0 - Вийти");
            System.out.print("Вибір: ");

            if (!sc.hasNextInt()) {
                sc.next();
                System.out.println("Введи число!");
                continue;
            }

            int c = sc.nextInt();
            sc.nextLine();

            if (c == 0) {
                System.out.print("Зберегти базу користувачів перед виходом? (так/ні): ");
                var save = sc.nextLine().trim().toLowerCase();
                if (save.equals("так")) {
                    System.out.print("Шлях до файлу: ");
                    var path = sc.nextLine().trim();
                    registry.saveToFile(path);
                }
                break;
            }

            if (c == 1) {
                System.out.print("Логін: ");
                var login = sc.nextLine().trim();
                System.out.print("Пароль: ");
                var password = sc.nextLine().trim();
                registry.registerUser(login, password);
            }

            else if (c == 2) {
                System.out.print("Логін: ");
                var login = sc.nextLine().trim();
                System.out.print("Пароль: ");
                var password = sc.nextLine().trim();
                registry.loginUser(login, password);
            }

            else if (c == 3) {
                System.out.print("ID користувача: ");
                if (!sc.hasNextInt()) { sc.next(); System.out.println("Введи число!"); continue; }
                registry.logoutUser(sc.nextInt());
                sc.nextLine();
            }

            else if (c == 4) {
                System.out.print("Логін: ");
                var login = sc.nextLine().trim();
                if (registry.isUserRegistered(login)) {
                    System.out.println("Користувач " + login + " зареєстрований");
                } else {
                    System.out.println("Такого користувача немає");
                }
            }

            else if (c == 5) {
                System.out.print("ID користувача: ");
                if (!sc.hasNextInt()) { sc.next(); System.out.println("Введи число!"); continue; }
                registry.removeUser(sc.nextInt());
                sc.nextLine();
            }

            else if (c == 6) {
                registry.printTotalUniqueUsers();
            }

            else if (c == 7) {
                registry.displayAllUsers();
            }

            else if (c == 8) {
                var list = registry.getUserList();
                for (var user : list) {
                    System.out.println("  " + user);
                }
            }

            else if (c == 9) {
                var sorted = registry.getInOrder(
                    (u1, u2) -> u1.getIdentifier().getName().compareTo(u2.getIdentifier().getName())
                );
                for (var user : sorted) {
                    System.out.println("  " + user);
                }
            }

            else if (c == 10) {
                var filtered = registry.getFiltered(user -> user.isLoggedIn());
                for (var user : filtered) {
                    System.out.println("  " + user);
                }
            }

            else if (c == 11) {
                System.out.print("Шлях до файлу: ");
                var path = sc.nextLine().trim();
                registry.saveToFile(path);
            }

            else {
                System.out.println("Такого пункту немає");
            }
        }

        sc.close();
    }
}
