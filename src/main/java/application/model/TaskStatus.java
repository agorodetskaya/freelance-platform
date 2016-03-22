package application.model;

public enum TaskStatus {
    OPEN("открыт"), TENDER("есть предложения"), DEVELOPMENT("в разработке"), CLOSE("закрыт");
    private String displayName;

    TaskStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
