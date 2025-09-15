import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static final String MAIN_PROMPT = """
        Commands:
        A) Create a to-do list item.
        B) View your current to-do list.
        C) View all completed to-do list.
        D) Exit.
        
        Command:""";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Item> todoList = new ArrayList<>();
        ArrayList<Item> completedToDoList = new ArrayList<>();

        System.out.println("Welcome to the Mist To-Do List\n");

        MainFunctions functions = new MainFunctions();

        while (true) {
            System.out.print(MAIN_PROMPT + " ");
            String command = scanner.nextLine();

            switch (command) {
                case "A":
                    todoList = functions.todoFunctionA(scanner, todoList);
                    break;
                case "B":
                    functions.todoFunctionB(scanner, todoList, completedToDoList);
                    break;
                case "C":
                    functions.todoFunctionC(completedToDoList);
                    break;
                case "D":
                    return;
            }
        }
    }
}