import java.util.*;
import java.util.stream.Collectors;

public class MainFunctions {

    public void addTodoItems(Scanner scanner, ArrayList<Item> todo) {
        System.out.print("Please indicate the name for your To-Do item: ");
        String todoTitle = scanner.nextLine();

        Item newItem = new Item(todoTitle);
        todo.add(newItem);

        System.out.println("The new todo list has been added. Listed below are the information for the item.");
        System.out.println("Item #" + todo.size() +
                "\n - Title: " + newItem.getTitle() +
                "\n - Completed: " + newItem.isComplete() + "\n");

    }

    public void manageTodoItems(Scanner scanner, ArrayList<Item> todo) {

        ItemFunctions itemFunctions = new ItemFunctions();
        UserHandler userHandler = new UserHandler();

        Map<ItemPromptOptions, Runnable> prompts = new HashMap<>();
        prompts.put(ItemPromptOptions.MARK_AS_COMPLETED, () -> itemFunctions.markItemCompleted(this, userHandler, scanner, todo));
        prompts.put(ItemPromptOptions.UPDATE_TITLE, () -> itemFunctions.updateItemTitle(this, userHandler, scanner, todo));
        prompts.put(ItemPromptOptions.DELETE, () -> itemFunctions.deleteItem(this, userHandler, scanner, todo));

        Map<String, ItemPromptOptions> promptOptions = new HashMap<>();
        promptOptions.put("A", ItemPromptOptions.MARK_AS_COMPLETED);
        promptOptions.put("B", ItemPromptOptions.UPDATE_TITLE);
        promptOptions.put("C", ItemPromptOptions.DELETE);

        if (todo.isEmpty()) {
            System.out.println("There is currently no To-Do items in the list.");
            return;
        }

        while (true) {
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
            ItemPromptOptions optionSelected = promptOptions.get(command);
            if (command.equals("D")) { return; }
            if (optionSelected == null) { continue; }
            prompts.get(optionSelected).run();
        }

    }

    public void viewTodos(ArrayList<Item> todo, boolean viewOnlyCompletedTodos) {

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
                                x.getTitle() + "\nStatus: " + isCompleteCheck(x) + "\n"
                        )
                );
    }

    public String isCompleteCheck(Item item) {
        return item.isComplete() ? "Complete" : "Incomplete";
    }

}