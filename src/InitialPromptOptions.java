public enum InitialPromptOptions {
    CREATE("A"),
    VIEW("B"),
    COMPLETED("C"),
    EXIT("D");

    private final String OPTION_VALUE;

    InitialPromptOptions(String optionValue) {
        this.OPTION_VALUE = optionValue;
    }

    public String getOPTION_VALUE() {
        return OPTION_VALUE;
    }

}