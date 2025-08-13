import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Event_controller implements Function {
    private Event[] events;
    private int size;

    Scanner sc = new Scanner(System.in);
    String title;
    String type;
    LocalDate date;

    public Event_controller() {
        events = new Event[5]; //initial capability or slots
        size = 0;
    }

    private String EmptyInputValidation (String print) {
        while (true) {
            System.out.print(print);
            String input = sc.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("Input cannot be empty, please select a option!");
        }
    }

    private LocalDate DateValidation (String print) {
        while (true) {
            System.out.print(print);
            String input = sc.nextLine().trim();
            try {
                return LocalDate.parse(input);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format yyyy-mm-dd!");
            }
        }
    }

    private int indexValidation(int max) {
        while (true) {
            System.out.print("Enter a number (1 - " + max + "): ");
            if (sc.hasNextInt()) {
                int index = sc.nextInt();
                sc.nextLine(); // consume newline immediately here
                if (index >= 1 && index <= max) {
                    return index;
                }
                System.out.println("Number out of range, please enter between 1 and " + max);
            } else {
                System.out.println("Please enter a valid number!");
                sc.nextLine(); // discard invalid input
            }
        }
    }


    private boolean YesNoValidation (String print) {
        while (true) {
            System.out.print(print);
            String ans= sc.nextLine().trim().toLowerCase();
            if (ans.isEmpty()) {
                System.out.println("Please select Yes or No!");
            }
            if (ans.equals("yes") || ans.equals("y")) {
                return true;
            }
            if (ans.equals("no") || ans.equals("n")) {
                return false;
            }
            System.out.println("Please choose Yes or No!");
        }
    }

    @Override
    public void addEvent() {
//        System.out.println("Title: ");
        title = EmptyInputValidation("Title: "); //take the user input
//        System.out.println("Type: ");
        type = EmptyInputValidation("Type: ");
//        System.out.println("Date (yyyy-mm-dd): ");
        date = DateValidation("Date: ");

        for (int i = 0; i < size; i++) {
            if (events[i].getTitle().equalsIgnoreCase(title) && events[i].getDate().equals(date)) {
                System.out.println("Event is already in the list."); //if has no return the system may print 2 or more times
                return; //this make the system just print one time and return the menu
            }
        }
        //do I need to add checking format func for date :??? I will add it later

        if (size == events.length) resize(); //line has resize()
        events[size++] = new Event(title, type, date);
        System.out.println("Event added successfully.");
        //check if the new event is duplicated

    }
        private void resize() {
            Event[] tempEvent = new Event[events.length + (events.length/2)];
            for(int i = 0; i < events.length; i++) {
                tempEvent[i] = events[i];
            }
            events = tempEvent;
        }

        public void displayAllEvents() {

        if (size == 0) {
            System.out.println("There are no events, please add more events");
            return;
        }
        for(int j = 0; j<size; j++) {
            System.out.println((j+1)+". "+events[j].toString());
            }
        }

        public void sortEvents() {
            if (size == 0) {
                System.out.println("There are no events, please add more events");
                return;
            }

            int numsort;
            System.out.println("1. Sort by title.\n2. Sort by type.\n3. Sort by date.\n0. Cancel");
            while (true) {
                if (sc.hasNextInt()) {
                    numsort = sc.nextInt();
                    sc.nextLine();
                    if (numsort >= 0 && numsort <= 3) {
                        break;
                    }
                } else {
                    System.out.println("Please enter a number between 0 and 3.");
                    sc.nextLine();
                }
            }
                switch (numsort) {
                    case 1:
                        sortTitle();
                        System.out.println("All events sorted by title.");
                        break;
                    case 2:
                        sortType();
                        System.out.println("All events sorted by type.");
                        break;
                    case 3:
                        sortDate();
                        System.out.println(" All events sorted by date!");
                        break;
                    case 0:
                        System.out.println("Sort events cancelled!");
                        return;
                }
            displayAllEvents();
        }

        private void sortDate() {
            for(int i = 0; i<size-1; i++) {
                for(int j = 0; j< size-i-1; j++) {
                    if(events[j].getDate().isAfter(events[j+1].getDate())) {
                        Event temp = events[j]; //create temp to save
                        events[j] = events[j+1];
                        events[j+1] = temp;
                    }

                }
            }
        }

        private void sortType() {
            for(int i = 0; i<size-1; i++) {
                for(int j = 0; j<size-i-1; j++) {
                    if(events[j].getType().compareToIgnoreCase(events[j+1].getType()) > 0) {
                        Event temp = events[j];
                        events[j] = events[j+1];
                        events[j+1] = temp;
                    }
                }
            }
        }

        private void sortTitle() {
            for(int i = 0; i<size-1; i++) {
                for(int j = 0; j<size-i-1; j++) {
                    if(events[j].getTitle().compareToIgnoreCase(events[j+1].getTitle()) > 0) {
                        Event temp = events[j];
                        events[j] = events[j+1];
                        events[j+1] = temp;
                    }
                }
            }
        }

        public void searchEvent() {
            if (size == 0) {
                System.out.println("There are no events to search, please add more events");
                return;
            }

            System.out.println("Search by: \n1. Title\n2. Type\n3. Date");
            int numsearch = indexValidation(3);
            switch (numsearch) {
                case 1:
                    sortTitle();
                    String keyword = EmptyInputValidation("Enter title keyword: ");
                    int index = BinarySearchTitle(keyword);
                    if (index != 1) {
                        System.out.println("Event found: " + events[index]);
                    }
                    else {
                        System.out.println("Event not found!");
                    }
                    break;
                case 2:
                    sortType();
                    String typeword = EmptyInputValidation("Enter type: ");
                    boolean foundtype = false;
                    for (int i = 0; i < size; i++) {
                        if (events[i].getType().equalsIgnoreCase(typeword)) {
                            foundtype = true;
                            System.out.println("Event found: " + events[i]);
                        }
                    }

                    if (!foundtype) {
                        System.out.println("Event not found!");
                    }
                    break;
                case 3:
                    sortDate();
                    LocalDate dateword = DateValidation("Enter date: ");
                    boolean founddate = false;
                    for (int i = 0; i < size; i++) {
                        if (events[i].getDate().equals(dateword)) {
                            founddate = true;
                            System.out.println("Event found: " + events[i]);
                        }
                    }
                    if (!founddate) {
                        System.out.println("Event not found!");
                    }
                    break;
            }

        }

        private int BinarySearchTitle(String keyword) {
            int left = 0;
            int right = size-1;

            while(left <= right) {
                int mid = (left + right) / 2;
                int compare =  events[mid].getTitle().compareToIgnoreCase(keyword);
                if(compare == 0) {
                    return mid;
                }
                else if(compare > 0) {
                    right = mid - 1;
                }
                else {
                    left = mid + 1;
                }
            } return -1;
        }

        public void editEvent() {
            if (size == 0) {
                System.out.println("There are no events to edit, please add more events");
                return;
            }

            displayAllEvents();
            int inmum = indexValidation(size);
            Event selectedEvent = events[inmum -1]; //convert number to index
            int edit;
            do {
                System.out.println("1. Title" + "\n2. Type" + "\n3. Date" + "\n4. Save");
                edit = indexValidation(4);
                switch (edit) {
                    case 1:
                        String newTitle = EmptyInputValidation("Enter new title: ");
                        selectedEvent.setTitle(newTitle);
                        break;
                    case 2:
                        String newType = EmptyInputValidation("Enter new type: ");
                        selectedEvent.setType(newType);
                        break;
                    case 3:
                        selectedEvent.setDate(DateValidation("Enter new date: "));
                        break;
                    case 4:
                        System.out.println("Event saved successfully.");
                        return;
                }
            } while (edit != 4);
        }

    public void deleteEvent() {
        if (size == 0) {
            System.out.println("There are no events, please add more events");
            return;
        }
        displayAllEvents();
        int innum = indexValidation(size);

        Boolean answer = YesNoValidation("Are you sure you want to delete this event? (Yes/No)");
        if (answer) {
            for (int i = innum - 1; i < size - 1; i++) {
                events[i] = events[i + 1];
            }
            events[--size] = null; // clear last slot
            System.out.println("Event deleted successfully.");
        } else {
            System.out.println("Delete cancelled.");
        }
    }

    public void clearAllEvents() {
            System.out.println("Are you sure you want to clear all events? (Yes/No)");
            String answer = sc.nextLine().toLowerCase();
            if(answer.equalsIgnoreCase("yes")) {
                for(int i = 0; i<size; i++) {
                    events[i] = null;
                }
                size = 0;
            }
        }
}
