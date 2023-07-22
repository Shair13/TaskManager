package pl.coderslab.taskmanager;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

public class TaskManager {

    public static void main(String[] args) {

        loadData();
        displayOptions();
    }

    public class ConsoleColors {
        public static final String BLUE = "\033[0;34m";
        public static final String RESET = "\033[0m";
    }

    public static void displayOptions() {

        System.out.println(ConsoleColors.BLUE + "Please select an option:" + ConsoleColors.RESET);
        String[] managerOptions = {"add", "remove", "list", "exit"};
        for (int i = 0; i < managerOptions.length; i++) {
            System.out.println(managerOptions[i]);
        }
    }


    public static void loadData() {
        Path path = Paths.get("tasks.csv");
        int rowCounter = 0;
        try {
            for (String line : Files.readAllLines(path)) {
                rowCounter++;
            }
        } catch (FileNotFoundException e) {
            System.err.println("Cannot find file");
        } catch (IOException e) {
            System.err.println("Cannot read file");
        }
        String[][] tasksArray = new String[rowCounter][3];
        try (Scanner scan = new Scanner(path);) {
            for (int i = 0; i < rowCounter; i++) {
                String[] tempStringArr = scan.nextLine().split(", ");
                for (int j = 0; j < 3; j++) {
                    tasksArray[i][j] = tempStringArr[j];
                }
            }
        } catch (IOException e) {
            System.err.println("File not found");
        }
    }

    public static void getOption() {
//        switch (input) {
//            case "add":
//                addTask();
//                break;
//            case "remove":
//                removeTask();
//                break;
//            case "list":
//                showList();
//                break;
//            case "exit":
//                exitApp();
//                break;
//            default:
//                System.out.println("Please select a correct option.");
//    }
        }
    }



