package application.view;

public interface PriceAndEstimateTimeView {
    boolean isEstimateTimeAgreement();

    int getEstimateTimeDays();

    int getEstimateTimeHours();

    boolean isPriceAgreement();

    int getPrice();
}
