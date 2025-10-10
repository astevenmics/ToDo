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
        ArrayList<Item> todoList = new ArrayList<>();
        MainFunctions mainFunctions = new MainFunctions();

        Map<InitialPromptOptions, Runnable> prompts = new HashMap<>();
        prompts.put(InitialPromptOptions.CREATE, () -> mainFunctions.addTodoItems(scanner, todoList));
        prompts.put(InitialPromptOptions.VIEW, () -> mainFunctions.manageTodoItems(scanner, todoList));
        prompts.put(InitialPromptOptions.COMPLETED, () -> mainFunctions.viewTodos(todoList, true));
        prompts.put(InitialPromptOptions.EXIT, () -> System.exit(0));

        Map<String, InitialPromptOptions> promptOptions = new HashMap<>();
        promptOptions.put("A", InitialPromptOptions.CREATE);
        promptOptions.put("B", InitialPromptOptions.VIEW);
        promptOptions.put("C", InitialPromptOptions.COMPLETED);
        promptOptions.put("D", InitialPromptOptions.EXIT);

        System.out.println("Welcome to the Mist To-Do List\n");

        //noinspection InfiniteLoopStatement
        while (true) {
            System.out.print(MAIN_PROMPT + " ");
            String command = scanner.nextLine();
            InitialPromptOptions optionSelected = promptOptions.get(command);
            if (optionSelected == null) { continue; }
            prompts.get(optionSelected).run();
        }
    }
}