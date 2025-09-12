import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        ArrayList<Item> todos = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

        System.out.print("Type details for your to do: ");
        String itemTitle = scanner.nextLine();

        while (true) {
            System.out.print("A");
            String a = scanner.nextLine();
            System.out.println(a);
            if (a.equals("A")) {
                System.out.print("Type the name of the item you want to add: ");
                String name = scanner.nextLine();
            } else {
                return;
            }
        }

    }
}