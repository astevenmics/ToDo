public enum ItemPromptOptions {
    MARK_AS_COMPLETED("A"),
    UPDATE_TITLE("B"),
    DELETE("C"),
    EXIT("D");

    private final String OPTION_VALUE;

    ItemPromptOptions(String optionValue) {
        this.OPTION_VALUE = optionValue;
    }

    public String getOPTION_VALUE() {
        return OPTION_VALUE;
    }
}