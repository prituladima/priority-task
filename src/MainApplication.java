import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainApplication {

    private static int amountOfComparing = 0;

    public static void main(String[] args) throws IOException {
        System.out.println("Начало работы приложения");

        Scanner scanner = new Scanner(System.in);

        List<String> tasks;

        try (Stream<String> stream = Files.lines(Paths.get("input.txt"))) {
            tasks = stream.map(String::trim).collect(Collectors.toList());
        }

        System.out.println("У нас на сегодня " + tasks.size() + " актуальных сверхзадач");
        tasks.sort((x, y) -> {
            int compare;
            while (true) {
                amountOfComparing++;
                System.out.println("--------------------------");
                System.out.println("Что нужно сделать быстрее?");
                System.out.println("Или что важнее?");
                System.out.println("1. " + x);
                System.out.println("2. " + y);
                compare = scanner.nextInt();
                if (compare == 1 || compare == 2) {
                    break;
                } else {
                    System.out.println("Не правильный агрумент. Допустимые {1, 2}.");
                }
            }
            if (compare == 0) {
                throw new RuntimeException("Unreachable statement");
            }
            return compare == 1 ? -1 : 1;
        });
        System.out.println("В этот раз у нас " + amountOfComparing + " сравнений!");

        try (PrintWriter writer = new PrintWriter("output.txt")) {
            tasks.forEach(writer::println);
        }

        System.out.println("Конец работы приложения");

        //47 -> 178
    }
}