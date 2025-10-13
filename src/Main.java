import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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
        Map<String, Runnable> prompts = getStringRunnableMap(scanner);

        System.out.println("Welcome to the Mist To-Do List\n");

        //noinspection InfiniteLoopStatement
        while (true) {
            System.out.print(MAIN_PROMPT + " ");
            String command = scanner.nextLine();
            Runnable optionSelected = prompts.get(command.toUpperCase());
            if (optionSelected == null) { continue; }
            optionSelected.run();
        }
    }

    private static Map<String, Runnable> getStringRunnableMap(Scanner scanner) {
        ArrayList<Item> todoList = new ArrayList<>();
        MainFunctions mainFunctions = new MainFunctions();

        Map<String, Runnable> prompts = new HashMap<>();
        prompts.put(InitialPromptOptions.CREATE.getOPTION_VALUE(), () -> mainFunctions.addTodoItems(scanner, todoList));
        prompts.put(InitialPromptOptions.VIEW.getOPTION_VALUE(), () -> mainFunctions.manageTodoItems(scanner, todoList));
        prompts.put(InitialPromptOptions.COMPLETED.getOPTION_VALUE(), () -> mainFunctions.viewTodos(todoList, true));
        prompts.put(InitialPromptOptions.EXIT.getOPTION_VALUE(), () -> System.exit(0));
        return prompts;
    }
}