// Main.java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MyList list = null;

        while (true) {
            try {
                System.out.println("Скільки елементів хочеш додати?");
                if (!sc.hasNextInt()) {
                    sc.next();
                    throw new WrongDataException("Потрібно вводити тільки числа");
                }

                int n = sc.nextInt();

                if (n < 0) {
                    throw new InvalidCapacityException("Невірна кількість елементів");
                }

                list = new MyList(5);

                for (int i = 0; i < n; i++) {
                    System.out.print("Введи елемент: ");
                    if (!sc.hasNextInt()) {
                        sc.next();
                        throw new WrongDataException("Потрібно вводити тільки числа");
                    }
                    int v = sc.nextInt();
                    list.add(v);
                }

                break;
            } catch (InvalidCapacityException e) {
                System.out.println(e.getMessage());
            } catch (WrongDataException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Помилка");
            }
        }

        while (true) {
            System.out.println();
            System.out.println("Меню:");
            System.out.println("1 - Додати в кінець");
            System.out.println("2 - Додати на початок");
            System.out.println("3 - Додати за індексом");
            System.out.println("4 - Отримати за індексом");
            System.out.println("5 - Видалити за індексом");
            System.out.println("6 - Показати всі елементи");
            System.out.println("7 - Розмір (size)");
            System.out.println("8 - Місткість (capacity)");
            System.out.println("9 - Очистити список");
            System.out.println("0 - Вихід");

            try {
                if (!sc.hasNextInt()) {
                    sc.next();
                    throw new WrongDataException("Потрібно вводити тільки числа");
                }

                int c = sc.nextInt();

                if (c < 0 || c > 9) {
                    throw new WrongMenuException("Невірний пункт меню");
                }

                if (c == 0) {
                    break;
                }

                if (c == 1) {
                    System.out.print("Введи значення: ");
                    if (!sc.hasNextInt()) {
                        sc.next();
                        throw new WrongDataException("Потрібно вводити тільки числа");
                    }
                    int v = sc.nextInt();
                    list.add(v);
                }

                if (c == 2) {
                    System.out.print("Введи значення: ");
                    if (!sc.hasNextInt()) {
                        sc.next();
                        throw new WrongDataException("Потрібно вводити тільки числа");
                    }
                    int v = sc.nextInt();
                    list.addFirst(v);
                }

                if (c == 3) {
                    System.out.print("Введи індекс: ");
                    if (!sc.hasNextInt()) {
                        sc.next();
                        throw new WrongDataException("Потрібно вводити тільки числа");
                    }
                    int i = sc.nextInt();

                    System.out.print("Введи значення: ");
                    if (!sc.hasNextInt()) {
                        sc.next();
                        throw new WrongDataException("Потрібно вводити тільки числа");
                    }
                    int v = sc.nextInt();

                    list.add(i, v);
                }

                if (c == 4) {
                    System.out.print("Введи індекс: ");
                    if (!sc.hasNextInt()) {
                        sc.next();
                        throw new WrongDataException("Потрібно вводити тільки числа");
                    }
                    int i = sc.nextInt();

                    System.out.println("Значення: " + list.get(i));
                }

                if (c == 5) {
                    System.out.print("Введи індекс: ");
                    if (!sc.hasNextInt()) {
                        sc.next();
                        throw new WrongDataException("Потрібно вводити тільки числа");
                    }
                    int i = sc.nextInt();

                    list.remove(i);
                }

                if (c == 6) {
                    if (list.size() == 0) {
                        System.out.println("Список порожній");
                    } else {
                        System.out.print("Список: ");
                        for (int i = 0; i < list.size(); i++) {
                            System.out.print(list.get(i) + " ");
                        }
                        System.out.println();
                    }
                }

                if (c == 7) {
                    System.out.println("Розмір: " + list.size());
                }

                if (c == 8) {
                    System.out.println("Місткість: " + list.capacity());
                }

                if (c == 9) {
                    list.clear();
                    System.out.println("Список очищено");
                }

            } catch (InvalidIndexException e) {
                System.out.println(e.getMessage());
            } catch (EmptyListException e) {
                System.out.println(e.getMessage());
            } catch (WrongMenuException e) {
                System.out.println(e.getMessage());
            } catch (WrongDataException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Помилка");
            }
        }
    }
}