package application.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;


@Entity
@Table(name = "task_request")
public class TaskRequest {
    @Id
    @Column(name = "task_request_id")
    @GeneratedValue
    private long id;
    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;
    @ManyToOne
    @JoinColumn(name = "executor_id", nullable = false)
    private User executor;
    @Column(name = "estimate_time_agreement")
    private boolean estimateTimeAgreement;
    @Column(name = "estimate_time_days")
    private int estimateTimeDays;
    @Column(name = "estimate_time_hours")
    private int estimateTimeHours;
    @Column(name = "price_agreement")
    private boolean priceAgreement;
    @Column(name = "price")
    private int price;
    @Column(name = "comment")
    private String comment;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date", nullable = false)
    private Date createDate;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "task_request_status")
    private TaskRequestStatus taskRequestStatus;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public User getExecutor() {
        return executor;
    }

    public void setExecutor(User executor) {
        this.executor = executor;
    }

    public boolean isEstimateTimeAgreement() {
        return estimateTimeAgreement;
    }

    public void setEstimateTimeAgreement(boolean estimateTimeAgreement) {
        this.estimateTimeAgreement = estimateTimeAgreement;
    }

    public boolean isPriceAgreement() {
        return priceAgreement;
    }

    public void setPriceAgreement(boolean priceAgreement) {
        this.priceAgreement = priceAgreement;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public TaskRequestStatus getTaskRequestStatus() {
        return taskRequestStatus;
    }

    public void setTaskRequestStatus(TaskRequestStatus taskRequestStatus) {
        this.taskRequestStatus = taskRequestStatus;
    }
}
