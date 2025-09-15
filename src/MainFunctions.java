import java.util.ArrayList;
import java.util.Scanner;

public class MainFunctions {

    public ArrayList<Item> todoFunctionA(Scanner scanner, ArrayList<Item> todo) {
        System.out.print("Please indicate the name for your To-Do item: ");
        String todoTitle = scanner.nextLine();
        return createNewToDoList(todo, todoTitle);
    }

    public void todoFunctionB(Scanner scanner, ArrayList<Item> todo, ArrayList<Item> completedToDo) {

        if (todo.isEmpty()) {
            System.out.println("There is currently no To-Do items in the list.");
            return;
        }

        ItemFunctions itemFunctions = new ItemFunctions();

        System.out.println("=== Remaining To-Do items ===");
        todo.stream()
                .filter(x -> !x.isComplete())
                .forEach(
                        x -> System.out.println((todo.indexOf(x) + 1) + ". " + x.getTitle() + "\nStatus: " + isComplete(x) + "\n")
                );

        String itemPrompt = """
                Commands:
                A) Mark as completed.
                B) Edit description.
                C) Delete.
                D) Return to main menu.
                """;
        System.out.print(itemPrompt + "\nCommand: ");

        String command = scanner.nextLine();

        switch (command) {
            case "A":
                while (true) {
                    try {
                        System.out.print("Type the number of the item you want to mark as completed: ");
                        int itemNumber = scanner.nextInt();
                        Item itemToUpdate = todo.get(itemNumber - 1);
                        if (itemToUpdate == null) {
                            System.out.println("Item does not exist.");
                        } else {
                            itemFunctions.setCompleted(itemToUpdate);
                            System.out.println(itemToUpdate.getTitle() + "\nStatus: " + isComplete(itemToUpdate) + "\n\n");
                            todo.remove(itemToUpdate);
                            completedToDo.add(itemToUpdate);
                            return;
                        }
                    } catch (Exception e) {
                        System.out.println("Invalid input!");
                    }
                }
            case "B":
                while (true) {
                    try {
                        System.out.print("Type the number of the item you want to update: ");
                        int itemNumber = Integer.parseInt(scanner.nextLine());
                        Item itemToUpdate = todo.get(itemNumber - 1);
                        if (itemToUpdate == null) {
                            System.out.println("Item does not exist.");
                        } else {
                            String oldTitle = itemToUpdate.getTitle();
                            System.out.print("Please type the new description you want for [" + itemToUpdate.getTitle() + "]: ");
                            String newDescription = scanner.nextLine();
                            itemFunctions.updateItemDescription(itemToUpdate, newDescription);
                            System.out.println(
                                    "ToDo Update Success\nOld title: " +
                                    oldTitle + "\nNew title: " +
                                    newDescription + "\n"
                            );
                            return;
                        }
                    } catch (Exception e) {
                        System.out.println("Invalid input!");
                    }
                }
            case "C":
                while (true) {
                    try {
                        System.out.print("Type the number of the item you want to delete: ");
                        int itemNumber = Integer.parseInt(scanner.nextLine());
                        Item itemToDelete = todo.get(itemNumber - 1);
                        if (itemToDelete == null) {
                            System.out.println("Item does not exist.");
                        } else {
                            String deletedItemTitle = itemToDelete.getTitle();
                            todo.remove(itemToDelete);
                            System.out.println("Successfully deleted: " + deletedItemTitle + "\n");
                            return;
                        }
                    } catch (Exception e) {
                        System.out.println("Invalid input!");
                    }
                }
            case "D":
                break;
        }
    }

    public void todoFunctionC(ArrayList<Item> todo) {

        if (todo.isEmpty()) {
            System.out.println("There is currently no completed To-Do items in the list.");
            return;
        }

        System.out.println("=== Completed To-Do items ===");
        todo.stream()
                .filter(Item::isComplete)
                .forEach(
                        x -> System.out.println(
                                x.getTitle() + "\nStatus: " + isComplete(x) + "\n"
                        )
                );
    }

    public ArrayList<Item> createNewToDoList(ArrayList<Item> todo, String todoTitle) {
        Item newItem = new Item(todoTitle);
        todo.add(newItem);
        System.out.println("The new todo list has been added. Listed below are the information for the item.");
        System.out.println("Item #" + todo.size() +
                "\n - Title: " + newItem.getTitle() +
                "\n - Completed: " + newItem.isComplete() + "\n");
        return todo;
    }

    public String isComplete(Item item) {
        return item.isComplete() ? "Complete" : "Incomplete";
    }

}