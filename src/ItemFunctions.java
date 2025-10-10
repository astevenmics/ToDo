import java.util.ArrayList;
import java.util.Scanner;

public class ItemFunctions {

    public void markItemCompleted(
            MainFunctions mainFunctions,
            UserHandler userHandler,
            Scanner scanner,
            ArrayList<Item> todo
    ) {
        String extensionMessage = "mark as completed";

        Item itemToMarkAsCompleted = userHandler.getItem(scanner, todo, extensionMessage);
        itemToMarkAsCompleted.setComplete(true);

        System.out.println(itemToMarkAsCompleted.getTitle() + "\nStatus: " + mainFunctions.isCompleteCheck(itemToMarkAsCompleted) + "\n\n");

        mainFunctions.viewTodos(todo, true);
    }

    public void updateItemTitle(
            MainFunctions mainFunctions,
            UserHandler userHandler,
            Scanner scanner,
            ArrayList<Item> todo
    ) {
        String extensionMessage = "update";

        Item itemToUpdate = userHandler.getItem(scanner, todo, extensionMessage);
        String oldTitle = itemToUpdate.getTitle();

        System.out.print("Please type the new title you want for [" + itemToUpdate.getTitle() + "]: ");

        String newTitle = scanner.nextLine();

        itemToUpdate.setTitle(newTitle);

        System.out.println(
                "ToDo Update Success\nOld title: " +
                        oldTitle + "\nNew title: " +
                        newTitle + "\n"
        );

        mainFunctions.viewTodos(todo, false);
    }

    public void deleteItem(
            MainFunctions mainFunctions,
            UserHandler userHandler,
            Scanner scanner,
            ArrayList<Item> todo
    ) {
        String extensionMessage = "delete";

        Item itemToDelete = userHandler.getItem(scanner, todo, extensionMessage);
        String deletedItemTitle = itemToDelete.getTitle();

        todo.remove(itemToDelete);

        System.out.println("Successfully deleted: " + deletedItemTitle + "\n");

        mainFunctions.viewTodos(todo, false);
    }

}