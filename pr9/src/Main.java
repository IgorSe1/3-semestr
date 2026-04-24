import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MyList<Integer> list = null;

        while (true) {
            try {
                System.out.print("Скільки елементів додати? ");

                if (!sc.hasNextInt()) {
                    sc.next();
                    System.out.println("Введи число!");
                    continue;
                }

                int n = sc.nextInt();

                if (n < 0) {
                    System.out.println("Число не може бути від'ємним!");
                    continue;
                }

                list = new MyList<Integer>(5);

                for (int i = 0; i < n; i++) {
                    System.out.print("Елемент " + (i + 1) + ": ");

                    if (!sc.hasNextInt()) {
                        sc.next();
                        System.out.println("Введи число!");
                        i--;
                        continue;
                    }

                    list.add(sc.nextInt());
                }

                break;

            } catch (MyException e) {
                System.out.println("Помилка: " + e.getMessage());
            }
        }

        while (true) {
            System.out.println();
            System.out.println("1 - Додати в кінець");
            System.out.println("2 - Додати на початок");
            System.out.println("3 - Додати за індексом");
            System.out.println("4 - Показати елемент за індексом");
            System.out.println("5 - Видалити за індексом");
            System.out.println("6 - Показати весь список");
            System.out.println("7 - Кількість елементів");
            System.out.println("8 - Місткість");
            System.out.println("9 - Очистити");
            System.out.println("10 - Сортувати від меншого до більшого");
            System.out.println("11 - Сортувати від більшого до меншого");
            System.out.println("12 - Порівняти з іншим списком");
            System.out.println("0 - Вийти");
            System.out.print("Вибір: ");

            try {
                if (!sc.hasNextInt()) {
                    sc.next();
                    System.out.println("Введи число!");
                    continue;
                }

                int c = sc.nextInt();

                if (c == 0) {
                    break;
                }

                if (c == 1) {
                    System.out.print("Значення: ");
                    if (!sc.hasNextInt()) { sc.next(); System.out.println("Введи число!"); continue; }
                    list.add(sc.nextInt());
                }

                else if (c == 2) {
                    System.out.print("Значення: ");
                    if (!sc.hasNextInt()) { sc.next(); System.out.println("Введи число!"); continue; }
                    list.addFirst(sc.nextInt());
                }

                else if (c == 3) {
                    System.out.print("Індекс: ");
                    if (!sc.hasNextInt()) { sc.next(); System.out.println("Введи число!"); continue; }
                    int idx = sc.nextInt();

                    System.out.print("Значення: ");
                    if (!sc.hasNextInt()) { sc.next(); System.out.println("Введи число!"); continue; }
                    int val = sc.nextInt();

                    list.add(idx, val);
                }

                else if (c == 4) {
                    System.out.print("Індекс: ");
                    if (!sc.hasNextInt()) { sc.next(); System.out.println("Введи число!"); continue; }
                    System.out.println("Елемент: " + list.get(sc.nextInt()));
                }

                else if (c == 5) {
                    System.out.print("Індекс: ");
                    if (!sc.hasNextInt()) { sc.next(); System.out.println("Введи число!"); continue; }
                    list.remove(sc.nextInt());
                }

                else if (c == 6) {
                    System.out.println("Список: " + list);
                }

                else if (c == 7) {
                    System.out.println("Кількість елементів: " + list.size());
                }

                else if (c == 8) {
                    System.out.println("Місткість: " + list.capacity());
                }

                else if (c == 9) {
                    list.clear();
                    System.out.println("Список очищено");
                }

                else if (c == 10) {
                    list.sort();
                    System.out.println("Список: " + list);
                }

                else if (c == 11) {
                    list.sort(new ReverseComparator());
                    System.out.println("Список: " + list);
                }

                else if (c == 12) {
                    System.out.print("Скільки елементів у другому списку? ");
                    if (!sc.hasNextInt()) { sc.next(); System.out.println("Введи число!"); continue; }
                    int n2 = sc.nextInt();

                    MyList<Integer> list2 = new MyList<Integer>(5);

                    for (int i = 0; i < n2; i++) {
                        System.out.print("Елемент " + (i + 1) + ": ");
                        if (!sc.hasNextInt()) { sc.next(); i--; continue; }
                        list2.add(sc.nextInt());
                    }

                    int result = list.compareTo(list2);

                    System.out.println("Список 1: " + list + " (розмір " + list.size() + ")");
                    System.out.println("Список 2: " + list2 + " (розмір " + list2.size() + ")");

                    if (result < 0) {
                        System.out.println("Перший список менший");
                    } else if (result > 0) {
                        System.out.println("Перший список більший");
                    } else {
                        System.out.println("Списки однакові за розміром");
                    }
                }

                else {
                    System.out.println("Такого пункту немає");
                }

            } catch (MyException e) {
                System.out.println("Помилка: " + e.getMessage());
            }
        }

        sc.close();
    }
}
