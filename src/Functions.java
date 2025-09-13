import java.util.ArrayList;
import java.util.Scanner;

public class Functions {

    public ArrayList<Item> todoFunctionA(Scanner scanner, ArrayList<Item> todo) {
        System.out.print("Please indicate the name for your To-Do item: ");
        String todoTitle = scanner.nextLine();
        return createNewToDoList(todo, todoTitle);
    }

    public void todoFunctionB(ArrayList<Item> todo) {
        System.out.println("=== Remaining To-Do items ===");
        todo.stream()
                .filter(x -> !x.isComplete())
                .forEach(
                        x -> System.out.println(x.getTitle() + "\nStatus: " + isComplete(x) + "\n")
                );
    }

    public void todoFunctionC(ArrayList<Item> todo) {
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