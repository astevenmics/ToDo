public class ItemFunctions {

    public void setCompleted(Item item) {
        item.setComplete(true);
    }

    public void updateItemTitle(Item item, String title) {
        item.setTitle(title);
    }

}