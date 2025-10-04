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

        Map<PromptOptions, Runnable> prompts = new HashMap<>();
        prompts.put(PromptOptions.CREATE, () -> mainFunctions.addTodoItems(scanner, todoList));
        prompts.put(PromptOptions.VIEW, () -> mainFunctions.manageTodoItems(scanner, todoList));
        prompts.put(PromptOptions.COMPLETED, () -> mainFunctions.viewTodos(todoList, true));
        prompts.put(PromptOptions.EXIT, () -> System.exit(0));

        Map<String, PromptOptions> promptOptions = new HashMap<>();
        promptOptions.put("A", PromptOptions.CREATE);
        promptOptions.put("B", PromptOptions.VIEW);
        promptOptions.put("C", PromptOptions.COMPLETED);
        promptOptions.put("D", PromptOptions.EXIT);

        System.out.println("Welcome to the Mist To-Do List\n");

        //noinspection InfiniteLoopStatement
        while (true) {
            System.out.print(MAIN_PROMPT + " ");
            String command = scanner.nextLine();
            PromptOptions optionSelected = promptOptions.get(command);
            if (optionSelected == null) { continue; }
            prompts.get(optionSelected).run();
        }
    }
}