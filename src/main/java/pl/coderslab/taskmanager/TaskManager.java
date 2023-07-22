package pl.coderslab.taskmanager;

import java.util.Arrays;

public class TaskManager {

    public static void main(String[] args) {
        displayOptions();
    }

    public class ConsoleColors {
        public static final String BLUE = "\033[0;34m";
        public static final String RESET = "\033[0m";
    }

    public static void displayOptions() {

        System.out.println(ConsoleColors.BLUE + "Please select an option:" + ConsoleColors.RESET);
        String[] managerOptions = {"add", "remove", "list", "exit"};
        for(int i = 0; i < managerOptions.length; i++){
            System.out.println(managerOptions[i]);
        }
    }


}
