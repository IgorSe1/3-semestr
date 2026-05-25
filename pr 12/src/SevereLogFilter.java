import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class SevereLogFilter implements LogFilter {
    @Override
    public void filter(String source_file, String target_file, LogLevel level) {
        try (
                FileReader fr = new FileReader(source_file);
                BufferedReader br = new BufferedReader(fr);
                Scanner sc = new Scanner(br);
                FileWriter fw = new FileWriter(target_file);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter pw = new PrintWriter(bw)
        ) {
            int count = 0;
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (line.contains(level.getKeyword())) {
                    pw.println(line);
                    count++;
                }
            }
            System.out.println("Відфільтровано рядків: " + count);
        } catch (IOException e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }
}
