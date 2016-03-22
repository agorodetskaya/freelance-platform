package application.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue
    @Column(name = "task_id")
    private long id;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "task_status", nullable = false)
    private TaskStatus taskStatus;
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private User customer;
    @ManyToOne
    @JoinColumn(name = "executor_id")
    private User executor;
    @Column(name = "theme", nullable = false)
    private String theme;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "agreement_estimate_time")
    private boolean agreementEstimateTime;
    @Column(name = "estimate_time_days")
    private int estimateTimeDays;
    @Column(name = "estimate_time_hours")
    private int estimateTimeHours;
    @Column(name = "agreement_price")
    private boolean agreementPrice;
    @Column(name = "price")
    private int price;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date", nullable = false)
    private Date createDate;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "task_subcategory", joinColumns = {@JoinColumn(name = "task_id")},
               inverseJoinColumns = {@JoinColumn(name = "subcategory_id")})
    private List<Subcategory> subcategories;
    @Column(name = "visit_count", nullable = false)
    private Long visitCount = 0L;
    @OneToMany(mappedBy = "task", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<TaskRequest> taskRequests;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public User getExecutor() {
        return executor;
    }

    public void setExecutor(User executor) {
        this.executor = executor;
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

    public boolean isAgreementEstimateTime() {
        return agreementEstimateTime;
    }

    public void setAgreementEstimateTime(boolean fixedEstimateTime) {
        this.agreementEstimateTime = fixedEstimateTime;
    }

    public int getEstimateTimeHours() {
        return estimateTimeHours;
    }

    public void setEstimateTimeHours(int estimateTime) {
        this.estimateTimeHours = estimateTime;
    }

    public boolean isAgreementPrice() {
        return agreementPrice;
    }

    public void setAgreementPrice(boolean fixedPrice) {
        this.agreementPrice = fixedPrice;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public List<Subcategory> getSubcategories() {
        return subcategories;
    }

    public void setSubcategories(List<Subcategory> subcategories) {
        this.subcategories = subcategories;
    }

    public Long getVisitCount() {
        return visitCount;
    }

    public void setVisitCount(Long visitCount) {
        this.visitCount = visitCount;
    }

    public int getEstimateTimeDays() {
        return estimateTimeDays;
    }

    public void setEstimateTimeDays(int estimateTimeDays) {
        this.estimateTimeDays = estimateTimeDays;
    }

    public List<TaskRequest> getTaskRequests() {
        return taskRequests;
    }

    public void setTaskRequests(List<TaskRequest> taskRequests) {
        this.taskRequests = taskRequests;
    }

}
