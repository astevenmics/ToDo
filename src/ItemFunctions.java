import java.util.ArrayList;
import java.util.Scanner;

public class ItemFunctions {

    public void setCompleted(Item item) {
        item.setComplete(true);
    }

    public void updateItemTitle(Item item, String title) {
        item.setTitle(title);
    }

    public Item getItem(Scanner scanner, ArrayList<Item> todo, String extensionMessage) {
        while (true) {
            try {
                System.out.print("Type the number of the item you want to " + extensionMessage + ": ");
                int itemNumber = Integer.parseInt(scanner.nextLine());

                return todo.get(itemNumber - 1);
            } catch (NumberFormatException numberFormatException) {
                System.out.println("Please enter a whole number!");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Item does not exist in the list!");
            }
        }
    }

}