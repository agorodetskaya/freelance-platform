package application.view;

import application.model.Task;
import application.model.TaskStatus;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Date;


public class TaskView implements PriceAndEstimateTimeView {
    @NotEmpty(message = "* заполните поле")
    @Length(min = 1, max = 1000, message = "* от 1 до 1000 символов")
    private String theme;
    @NotEmpty(message = "* заполните поле")
    @Length(min = 1, max = 10000, message = "* от 1 до 10000 символов")
    private String description;
    private boolean estimateTimeAgreement;
    private int estimateTimeDays;
    private int estimateTimeHours;
    private boolean priceAgreement;
    private int price;


    public Task toTask() {
        Task task = new Task();
        task.setTheme(theme);
        task.setDescription(description.replaceAll("\\n", "<br/>"));
        task.setAgreementEstimateTime(estimateTimeAgreement);
        task.setEstimateTimeDays(estimateTimeDays);
        task.setEstimateTimeHours(estimateTimeHours);
        task.setAgreementPrice(priceAgreement);
        task.setPrice(price);
        task.setTaskStatus(TaskStatus.OPEN);
        task.setCreateDate(new Date());
        return task;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean isEstimateTimeAgreement() {
        return estimateTimeAgreement;
    }

    public void setEstimateTimeAgreement(boolean estimateTimeAgreement) {
        this.estimateTimeAgreement = estimateTimeAgreement;
    }

    @Override
    public int getEstimateTimeDays() {
        return estimateTimeDays;
    }

    public void setEstimateTimeDays(int estimateTimeDays) {
        this.estimateTimeDays = estimateTimeDays;
    }

    @Override
    public int getEstimateTimeHours() {
        return estimateTimeHours;
    }

    public void setEstimateTimeHours(int estimateTimeHours) {
        this.estimateTimeHours = estimateTimeHours;
    }

    @Override
    public boolean isPriceAgreement() {
        return priceAgreement;
    }

    public void setPriceAgreement(boolean priceAgreement) {
        this.priceAgreement = priceAgreement;
    }

    @Override
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
