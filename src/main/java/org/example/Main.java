package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.example.task2.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.example.task1.PhoneValidator.validatePhoneNumber;
import static org.example.task3.WordFrequencyCounter.countWordFrequency;

public class Main {
    private static String makeBold(String text) {
        return "\u001B[1m" + text + "\u001B[0m";
    }

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("src/main/java/org/example/task1/file.txt");
        System.out.println(makeBold("Завдання 1:"));
        validatePhoneNumber(file);

        System.out.println("-------------------------------");

        List<User> users = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("src/main/java/org/example/task2/file.txt"))) {
            String line;
            boolean firstLine = true;

            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue;
                }
                String[] parts = line.split(" ");
                if (parts.length >= 2) {
                    String name = parts[0];
                    int age = Integer.parseInt(parts[1]);
                    users.add(new User(name, age));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(users);
        System.out.println(makeBold("Завдання 2:"));
        System.out.println(json);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/main/java/org/example/task2/user.json"))) {
            bw.write(json);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        System.out.println("-------------------------------");
        System.out.println(makeBold("Завдання 3:"));
        countWordFrequency("src/main/java/org/example/task3/words.txt");
    }
}