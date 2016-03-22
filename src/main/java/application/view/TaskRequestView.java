package application.view;

import application.model.TaskRequest;
import application.model.TaskRequestStatus;

import java.util.Date;

public class TaskRequestView implements PriceAndEstimateTimeView {
    private boolean estimateTimeAgreement;
    private int estimateTimeDays;
    private int estimateTimeHours;
    private boolean priceAgreement;
    private int price;
    private String comment;

    public TaskRequestView() {
    }

    public TaskRequest toTaskRequest() {
        TaskRequest taskRequest = new TaskRequest();
        taskRequest.setEstimateTimeAgreement(estimateTimeAgreement);
        taskRequest.setEstimateTimeDays(estimateTimeDays);
        taskRequest.setEstimateTimeHours(estimateTimeHours);
        taskRequest.setPriceAgreement(priceAgreement);
        taskRequest.setPrice(price);
        taskRequest.setComment(comment);
        taskRequest.setCreateDate(new Date());
        taskRequest.setTaskRequestStatus(TaskRequestStatus.WAIT);
        return taskRequest;
    }

    public boolean isEstimateTimeAgreement() {
        return estimateTimeAgreement;
    }

    public void setEstimateTimeAgreement(boolean estimateTimeAgreement) {
        this.estimateTimeAgreement = estimateTimeAgreement;
    }

    public int getEstimateTimeDays() {
        return estimateTimeDays;
    }

    public void setEstimateTimeDays(int estimateTimeDays) {
        this.estimateTimeDays = estimateTimeDays;
    }

    public int getEstimateTimeHours() {
        return estimateTimeHours;
    }

    public void setEstimateTimeHours(int estimateTimeHours) {
        this.estimateTimeHours = estimateTimeHours;
    }

    public boolean isPriceAgreement() {
        return priceAgreement;
    }

    public void setPriceAgreement(boolean priceAgreement) {
        this.priceAgreement = priceAgreement;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
