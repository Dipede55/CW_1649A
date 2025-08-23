//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
//this calendar will auto sort the event, users just need to add or delete event
import java.util.Scanner;
public class Main {
    public static void menu() {
        System.out.println("Campus Event Calendar" +
                "\n1. All events" +
                "\n2. Sort events" +
                "\n3. Search events" +
                "\n4. Add event" +
                "\n5. Edit event" +
                "\n6. Delete event" +
                "\n7. Clear all events" +
                "\n8. Back to main menu"); //Exit
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Event_controller function = new Event_controller(); //set up slots at the beginning
        while (true) {
            menu();//line5: need to throw this here - in this void to run
            System.out.println("Enter your choice: ");

            if(!sc.hasNextInt()) {
                System.out.println("Please enter a valid number.");
                sc.nextLine();
                continue;
            }

            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    System.out.println("Here is the list of all events");
                    function.displayAllEvents();
                    break;
                case 2:
                    System.out.println("Select a number: ");
                    function.sortEvents();
                    break;
                case 3:
                    System.out.println("Enter keyword to search!");
                    function.searchEvent();
                    break;
                case 4:
                    System.out.println("Please fill all information to add event");
                    function.addEvent();
                    break;
                case 5:
                    System.out.println("Choose an event to edit");
                    function.editEvent();
                    break;
                case 6:
                    System.out.println("Choose the number of event that you want to delete");
                    function.deleteEvent();
                    break;
                case 7:
                    System.out.println("Clear all events");
                    function.clearAllEvents();
                    break;
                case 8:
                    System.out.println("Existing Campus Event Calendar...");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please choose between 1 and 8.");
            }

        }

    }
}
