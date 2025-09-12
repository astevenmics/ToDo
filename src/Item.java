public class Item {

    private String title;
    private boolean complete;

    public Item(String title) {
        this.title = title;
        this.complete = false;
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