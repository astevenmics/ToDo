public class ItemFunctions {

    public void setCompleted(Item item) {
        item.setComplete(true);
    }

    public void updateItemDescription(Item item, String description) {
        item.setTitle(description);
    }

}