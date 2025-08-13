import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event {
    private String title;
    private String type;
    private LocalDate date;
    //you can add the description, detail, quantity people!, ...

    //create setter and getter
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public Event(String title, String type, LocalDate date) {
        super();
        this.title = title;
        this.type = type;
        this.date = date;
    }
    @Override
    public String toString() {
        return title + " - type: " + type + " - date: " + date + '.'; //this will return num.
        //not fixed yet
    }

    public void Main() {

    }
}
