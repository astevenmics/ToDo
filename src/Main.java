import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static final String PROMPT = """
        Commands:
        A) Create a to-do list item.
        B) View your current to-do list.
        C) View all completed to-do list.
        D) Exit.
        
        Command:""";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Item> todoList = new ArrayList<>();

        System.out.println("Welcome to the Mist To-Do List\n");

        Functions functions = new Functions();

        while (true) {
            System.out.print(PROMPT + " ");
            String command = scanner.nextLine();

            switch (command) {
                case "A":
                    todoList = functions.todoFunctionA(scanner, todoList);
                    break;
                case "B":
                    functions.todoFunctionB(todoList);
                    break;
                case "C":
                    functions.todoFunctionC(todoList);
                    break;
                case "D":
                    return;
            }
        }
    }
}