import java.time.LocalDateTime;

public class Item {

    private static int counter = 0;
    private final int ITEM_ID;
    private String title;
    private boolean complete;
    private final LocalDateTime CREATED_AT;

    public Item(String title) {
        this.ITEM_ID = ++counter;
        this.title = title;
        this.complete = false;
        this.CREATED_AT = LocalDateTime.now();
    }

    public int getId() {
        return ITEM_ID;
    }

    public LocalDateTime getCreatedAt() {
        return CREATED_AT;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

}