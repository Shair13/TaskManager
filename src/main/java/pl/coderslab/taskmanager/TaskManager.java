package pl.coderslab.taskmanager;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.Scanner;

public class TaskManager {

    public static void main(String[] args) {
        loadData();
        displayOptions();
        getOption();
    }

    static final String FILE_NAME = "tasks.csv";
    static final String[] OPTIONS = {"add", "remove", "list", "exit"};
    static String[][] tasks;
    
    public class ConsoleColors {
        public static final String BLUE = "\033[0;34m";
        public static final String RESET = "\033[0m";
    }

    public static void displayOptions() {

        System.out.println(ConsoleColors.BLUE + "Please select an option:" + ConsoleColors.RESET);
        for (int i = 0; i < OPTIONS.length; i++) {
            System.out.println(OPTIONS[i]);
        }
    }

    public static int checkSavedArrSize() {
        Path path = Paths.get(FILE_NAME);
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
        return rowCounter;
    }

    public static void loadData() {
        Path path = Paths.get(FILE_NAME);
        int loadedArrSize = checkSavedArrSize();
        tasks = new String[loadedArrSize][3];
        try (Scanner scan = new Scanner(path);) {
            for (int i = 0; i < loadedArrSize; i++) {
                String[] tempStringArr = scan.nextLine().split(", ");
                for (int j = 0; j < 3; j++) {
                    tasks[i][j] = tempStringArr[j];
                }
            }
        } catch (IOException e) {
            System.err.println("File not found");
        }
    }

    public static void getOption() {
        Scanner scan = new Scanner(System.in);
        switch (scan.nextLine()) {
            case "add":
                addTask();
                break;
            case "remove":
                removeTask(tasks, numberToRemove());
                break;
            case "list":
                showList();
                break;
            case "exit":
                exitApp();
                break;
            default:
                System.out.println("Please select a correct option.");
                getOption();
        }
    }

    public static void addTask() {
        String[][] tempArr = new String[tasks.length + 1][3];
        for (int i = 0; i < tasks.length; i++) {
            for (int j = 0; j < 3; j++) {
                tempArr[i][j] = tasks[i][j];
            }
        }
        tasks = tempArr;
        System.out.println("Please add task description.");
        Scanner scan = new Scanner(System.in);
        tasks[tasks.length - 1][0] = scan.nextLine();
        System.out.println("Please add task due date.");
        tasks[tasks.length - 1][1] = scan.nextLine();
        System.out.println("Is your task is important: true/false.");
        while (!scan.hasNextBoolean()) {
            System.out.println("Incorrect input. Please add true or false.");
            scan.nextLine();
        }
        tasks[tasks.length - 1][2] = String.valueOf(scan.nextBoolean());
        displayOptions();
        getOption();
    }

    public static void showList() {
        for (int i = 0; i < tasks.length; i++) {
            System.out.println(i + " : " + String.join(", ", tasks[i]));
        }
        displayOptions();
        getOption();
    }

    public static boolean isNumberGreaterEqualZero(String input) {
        if (NumberUtils.isParsable(input)) {
            return Integer.parseInt(input) >= 0;
        }
        return false;
    }

    public static int numberToRemove() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please select number of task to remove.");
        String indexToRemove = scan.nextLine();
        while (!isNumberGreaterEqualZero(indexToRemove)) {
            System.err.println("Invalid value. Please enter a number greater or equal 0.");
            indexToRemove = scan.nextLine();
        }
        return Integer.parseInt(indexToRemove);
    }

    public static void removeTask(String[][] setArr, int index) {
        try {
            if (index < setArr.length) {
                tasks = ArrayUtils.remove(setArr, index); // argument from method does not work - setArr = ArrayUtils.remove(setArr, index);
                System.out.println("Task number: " + index + " has been deleted.");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("The element does not exist in array.");
        }
        displayOptions();
        getOption();
    }

    public static void saveArrToFile() {
        Path path = Paths.get(FILE_NAME);
        String[] concatArrLines = new String[tasks.length];
        for (int i = 0; i < tasks.length; i++) {
            concatArrLines[i] = String.join(", ", tasks[i]);
        }
        try {
            Files.write(path, Arrays.asList(concatArrLines));
        } catch (IOException e) {
            System.err.println("File not found.");
        }
    }

    public static void exitApp() {
        saveArrToFile();
        System.err.println("Bye Bye");
        System.exit(0);
    }
}


