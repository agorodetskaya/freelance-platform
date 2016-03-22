package application.model;

public enum TaskRequestStatus {
    WAIT("ожидает ответа"), CHOOSE("выбран"), REJECTED("отклонен");

    private String displayName;

    TaskRequestStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
