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

        if (todo.isEmpty()) {
            System.out.println("There is currently no To-Do items in the list.");
            return;
        }

        ItemFunctions itemFunctions = new ItemFunctions();
        UserHandler userHandler = new UserHandler();

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
        String extensionMessage;

        switch (command) {
            case "A":
                extensionMessage = "mark as completed";

                Item itemToMarkAsCompleted = userHandler.getItem(scanner, todo, extensionMessage);
                itemFunctions.setCompleted(itemToMarkAsCompleted);

                System.out.println(itemToMarkAsCompleted.getTitle() + "\nStatus: " + isCompleteCheck(itemToMarkAsCompleted) + "\n\n");

                viewTodos(todo, true);
                break;

            case "B":
                extensionMessage = "update";
                Item itemToUpdate = userHandler.getItem(scanner, todo, extensionMessage);

                String oldTitle = itemToUpdate.getTitle();
                System.out.print("Please type the new title you want for [" + itemToUpdate.getTitle() + "]: ");
                String newTitle = scanner.nextLine();
                itemFunctions.updateItemTitle(itemToUpdate, newTitle);
                System.out.println(
                        "ToDo Update Success\nOld title: " +
                                oldTitle + "\nNew title: " +
                                newTitle + "\n"
                );
                viewTodos(todo, false);
                break;

            case "C":
                extensionMessage = "delete";
                Item itemToDelete = userHandler.getItem(scanner, todo, extensionMessage);
                String deletedItemTitle = itemToDelete.getTitle();
                todo.remove(itemToDelete);
                System.out.println("Successfully deleted: " + deletedItemTitle + "\n");
                viewTodos(todo, false);
                break;

            case "D":
                break;
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