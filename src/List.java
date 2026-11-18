import java.util.ArrayList;
import java.util.Scanner;

public class List {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> lineList = new ArrayList<>();
        boolean done = false;

        System.out.println("=== Welcome to the Simple Line Editor ===");

        do {
            printMenu();
            String command = SafeInput.getRegExString(scanner, "Choose an option", "[AaDdIiPpQq]").toUpperCase();

            switch (command) {
                case "A":
                    addItem(scanner, lineList);
                    break;
                case "D":
                    deleteItem(scanner, lineList);
                    break;
                case "I":
                    insertItem(scanner, lineList);
                    break;
                case "P":
                    printList(lineList);
                    break;
                case "Q":
                    if (SafeInput.getYNConfirm(scanner, "Are you sure you want to quit?")) {
                        done = true;
                        System.out.println("Exiting program.");
                    }
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (!done);

        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n----------------------------");
        System.out.println("A – Add an item to the list");
        System.out.println("D – Delete an item from the list");
        System.out.println("I – Insert an item into the list");
        System.out.println("P – Print (display) the list");
        System.out.println("Q – Quit the program");
        System.out.println("----------------------------");
    }

    private static void addItem(Scanner scanner, ArrayList<String> list) {
        String item = SafeInput.getNonZeroLenString(scanner, "Enter the text to add");
        list.add(item);
        System.out.println("Item added successfully.");
    }

    private static void deleteItem(Scanner scanner, ArrayList<String> list) {
        if (list.isEmpty()) {
            System.out.println("The list is currently empty. Nothing to delete.");
            return;
        }

        printList(list);
        int itemNum = SafeInput.getRangedInt(scanner, "Enter the item number to delete", 1, list.size());

        String removed = list.remove(itemNum - 1);
        System.out.println("Deleted item: \"" + removed + "\"");
    }

    private static void insertItem(Scanner scanner, ArrayList<String> list) {
        if (list.isEmpty()) {
            System.out.println("The list is empty. Inserting at position 1.");
            String item = SafeInput.getNonZeroLenString(scanner, "Enter the text to insert");
            list.add(item);
            return;
        }

        printList(list);
        int position = SafeInput.getRangedInt(scanner, "Enter the position number where you want to insert", 1, list.size() + 1);
        String item = SafeInput.getNonZeroLenString(scanner, "Enter the text to insert");

        list.add(position - 1, item);
        System.out.println("Item inserted successfully at position " + position + ".");
    }

    private static void printList(ArrayList<String> list) {
        System.out.println("\n--- Current List ---");
        if (list.isEmpty()) {
            System.out.println("(List is empty)");
        } else {
            for (int i = 0; i < list.size(); i++) {
                System.out.println((i + 1) + ". " + list.get(i));
            }
        }
        System.out.println("--------------------");
    }

}
