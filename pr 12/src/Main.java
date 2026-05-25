import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LogFilter filter = new SevereLogFilter();

        while (true) {
            System.out.println();
            System.out.println("1 - Згенерувати тестовий лог-файл");
            System.out.println("2 - Відфільтрувати лог-файл");
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
                break;
            }

            if (c == 1) {
                System.out.print("Шлях до файлу: ");
                String path = sc.nextLine().trim();
                System.out.print("Кількість рядків: ");
                if (!sc.hasNextInt()) { sc.next(); System.out.println("Введи число!"); continue; }
                int n = sc.nextInt();
                sc.nextLine();
                generateLog(path, n);
            }

            else if (c == 2) {
                System.out.print("Файл-джерело: ");
                String source = sc.nextLine().trim();
                System.out.print("Файл-результат: ");
                String target = sc.nextLine().trim();

                System.out.println("Доступні рівні: CRITICAL, ERROR, WARNING, INFO, DEBUG");
                System.out.print("Рівень: ");
                String levelStr = sc.nextLine().trim().toUpperCase();

                try {
                    LogLevel level = LogLevel.valueOf(levelStr);
                    filter.filter(source, target, level);
                } catch (IllegalArgumentException e) {
                    System.out.println("Невірний рівень логування");
                }
            }

            else {
                System.out.println("Такого пункту немає");
            }
        }

        sc.close();
    }

    public static void generateLog(String path, int n) {
        LogLevel[] levels = LogLevel.values();
        String[] modules = {"AUTH", "DB", "NETWORK", "UI", "CORE"};
        String[] messages = {
                "User logged in",
                "Connection timeout",
                "File not found",
                "Database query executed",
                "Memory usage high",
                "Request processed"
        };
        Random rand = new Random();

        try (
                FileWriter fw = new FileWriter(path);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter pw = new PrintWriter(bw)
        ) {
            for (int i = 1; i <= n; i++) {
                LogLevel level = levels[rand.nextInt(levels.length)];
                String module = modules[rand.nextInt(modules.length)];
                String message = messages[rand.nextInt(messages.length)];
                LocalDateTime time = LocalDateTime.now();
                pw.println(i + " " + time + " " + level.getKeyword() + " " + module + " " + message);
            }
            System.out.println("Згенеровано " + n + " рядків у файл " + path);
        } catch (IOException e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }
}
