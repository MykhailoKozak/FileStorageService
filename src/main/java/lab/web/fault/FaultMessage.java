package lab.web.fault;


public enum FaultMessage {
    NO_FILE_WITH_NAME("There is no file with  name [%s]"),
    SUCH_FILE_ALREADY_EXIST("[%s] file already exist");

    private String messageExpression;

    private FaultMessage(String message) {
        this.messageExpression = message;
    }

    public String getMessageExpression() {
        return this.messageExpression;
    }
}
