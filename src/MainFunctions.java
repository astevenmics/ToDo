import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

public class MainFunctions {

    public void addTodoItems(Scanner scanner, ArrayList<Item> todo) {

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");

        System.out.print("Please indicate the name for your To-Do item: ");
        String todoTitle = scanner.nextLine();

        Item newItem = new Item(todoTitle);
        todo.add(newItem);

        System.out.println("The new todo list has been added. Listed below are the information for the item.");
        System.out.println("Item #" + newItem.getId() +
                "\n - Title: " + newItem.getTitle() +
                "\n - Created on: " + dateTimeFormatter.format(newItem.getCreatedAt()) +
                "\n - Completed: " + newItem.isComplete() + "\n");
    }

    public void manageTodoItems(Scanner scanner, ArrayList<Item> todo) {

        ItemFunctions itemFunctions = new ItemFunctions();
        UserHandler userHandler = new UserHandler();

        AtomicBoolean running = new AtomicBoolean(true);
        Map<String, Runnable> prompts = new HashMap<>();
        prompts.put(ItemPromptOptions.MARK_AS_COMPLETED.getOPTION_VALUE(), () -> itemFunctions.markItemCompleted(this, userHandler, scanner, todo));
        prompts.put(ItemPromptOptions.UPDATE_TITLE.getOPTION_VALUE(), () -> itemFunctions.updateItemTitle(this, userHandler, scanner, todo));
        prompts.put(ItemPromptOptions.DELETE.getOPTION_VALUE(), () -> itemFunctions.deleteItem(this, userHandler, scanner, todo));
        prompts.put(ItemPromptOptions.EXIT.getOPTION_VALUE(), () -> running.set(false));

        if (todo.isEmpty()) {
            System.out.println("There is currently no To-Do items in the list.");
            return;
        }

        while (running.get()) {
            System.out.println("=== To-Do items ===");
            viewTodos(todo, false);

            String itemPrompt = """
                    Commands:
                    A) Mark as completed.
                    B) Edit title.
                    C) Delete.
                    D) Return to main menu.
                    """;
            System.out.print(itemPrompt + "\nCommand: ");

            String command = scanner.nextLine();
            Runnable optionSelected = prompts.get(command.toUpperCase());
            if (optionSelected == null) { continue; }
            optionSelected.run();
        }

    }

    public void viewTodos(ArrayList<Item> todo, boolean viewOnlyCompletedTodos) {

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM-dd-yyyy | HH:mm:ss");

        ArrayList<Item> completedToDos = todo.stream()
                .filter(Item::isComplete)
                .collect(Collectors.toCollection(ArrayList::new));

        if(viewOnlyCompletedTodos && completedToDos.isEmpty()) {
            System.out.println("There are currently no completed To-Do items in the list.");
            return;
        } else if (todo.isEmpty()) {
            System.out.println("There are currently no To-Do items in the list.");
            return;
        }

        if (viewOnlyCompletedTodos) {
            System.out.println("=== Completed To-Do items ===");
        }
        todo.stream()
                .filter(x -> !viewOnlyCompletedTodos || x.isComplete())
                .forEach(
                        x -> System.out.println(
                                x.getId() + ". " + x.getTitle() +
                                "\nCreated on: " + dateTimeFormatter.format(x.getCreatedAt()) +
                                "\nStatus: " + isCompleteCheck(x) +
                                "\n"
                        )
                );
    }

    public String isCompleteCheck(Item item) {
        return item.isComplete() ? "Complete" : "Incomplete";
    }

}