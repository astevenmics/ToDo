import java.util.ArrayList;
import java.util.Scanner;

public class MainFunctions {

    public ArrayList<Item> addTodoItems(Scanner scanner, ArrayList<Item> todo) {
        System.out.print("Please indicate the name for your To-Do item: ");
        String todoTitle = scanner.nextLine();

        Item newItem = new Item(todoTitle);
        todo.add(newItem);

        System.out.println("The new todo list has been added. Listed below are the information for the item.");
        System.out.println("Item #" + todo.size() +
                "\n - Title: " + newItem.getTitle() +
                "\n - Completed: " + newItem.isComplete() + "\n");

        return todo;
    }

    public void manageTodoItems(Scanner scanner, ArrayList<Item> todo, ArrayList<Item> completedToDo) {

        if (todo.isEmpty()) {
            System.out.println("There is currently no To-Do items in the list.");
            return;
        }

        ItemFunctions itemFunctions = new ItemFunctions();

        System.out.println("=== Remaining To-Do items ===");
        viewTodos(completedToDo, false);

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

                Item itemToMarkAsCompleted = itemFunctions.getItem(scanner, todo, extensionMessage);
                itemFunctions.setCompleted(itemToMarkAsCompleted);

                System.out.println(itemToMarkAsCompleted.getTitle() + "\nStatus: " + isCompleteCheck(itemToMarkAsCompleted) + "\n\n");

                completedToDo.add(itemToMarkAsCompleted);

                viewTodos(completedToDo, true);
                break;

            case "B":
                extensionMessage = "update";
                Item itemToUpdate = itemFunctions.getItem(scanner, todo, extensionMessage);

                String oldTitle = itemToUpdate.getTitle();
                System.out.print("Please type the new title you want for [" + itemToUpdate.getTitle() + "]: ");
                String newTitle = scanner.nextLine();
                itemFunctions.updateItemTitle(itemToUpdate, newTitle);
                System.out.println(
                        "ToDo Update Success\nOld title: " +
                                oldTitle + "\nNew title: " +
                                newTitle + "\n"
                );
                viewTodos(completedToDo, false);
                break;

            case "C":
                extensionMessage = "delete";
                Item itemToDelete = itemFunctions.getItem(scanner, todo, extensionMessage);
                String deletedItemTitle = itemToDelete.getTitle();
                todo.remove(itemToDelete);
                System.out.println("Successfully deleted: " + deletedItemTitle + "\n");
                viewTodos(completedToDo, false);
                break;

            case "D":
                break;
        }
    }

    public void viewTodos(ArrayList<Item> todo, boolean viewOnlyCompletedTodos) {

        if (todo.isEmpty()) {
            String isViewOnlyCompletedItemsOn = viewOnlyCompletedTodos ?
                    "There is currently no completed To-Do items in the list." :
                    "There is currently no To-Do items in the list.";
            System.out.println(isViewOnlyCompletedItemsOn);
            return;
        }

        System.out.println("=== Completed To-Do items ===");
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