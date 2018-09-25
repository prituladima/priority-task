import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

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
            amountOfComparing++;
            System.out.println("--------------------------");
            System.out.println("Что нужно сделать быстрее?");
            System.out.println("Или что важнее?");
            System.out.println("1. " + x);
            System.out.println("2. " + y);
            return scanner.nextInt() == 1 ? -1 : 1;
        });
        System.out.println("В этот раз у нас " + amountOfComparing + " сравнений!");

        try (PrintWriter writer = new PrintWriter(new File("output.txt"))) {
            tasks.forEach(writer::println);
        }

        System.out.println("Конец работы приложения");

        //47 -> 178
    }
}