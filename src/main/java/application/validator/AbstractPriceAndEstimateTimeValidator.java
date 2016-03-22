package application.validator;

import application.view.PriceAndEstimateTimeView;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public abstract class AbstractPriceAndEstimateTimeValidator implements Validator {

    protected String checkboxType;

    @Override
    public void validate(Object target, Errors errors) {
        PriceAndEstimateTimeView view = (PriceAndEstimateTimeView) target;
        //by agreement time
        if (view.isEstimateTimeAgreement()) {
            if (view.getEstimateTimeDays() != 0) {
                errors.rejectValue("estimateTimeDays", "notmatch.estimateTimeDays",
                        "* вы выбрали \"" + checkboxType + "\"");
            }
            if (view.getEstimateTimeHours() != 0) {
                errors.rejectValue("estimateTimeHours", "notmatch.estimateTimeHours",
                        "* вы выбрали \"" + checkboxType + "\"");
            }
        }

        //no agreement time
        if (!view.isEstimateTimeAgreement()) {
            if (view.getEstimateTimeDays() < 0) {
                errors.rejectValue("estimateTimeDays", "notmatch.estimateTimeDays", "* укажите корректный срок");
            }
            if (view.getEstimateTimeHours() < 0) {
                errors.rejectValue("estimateTimeHours", "notmatch.estimateTimeHours", "* укажите корректный скрок");
            }
            if (view.getEstimateTimeDays() <= 0 && view.getEstimateTimeHours() <= 0) {
                errors.rejectValue("estimateTimeHours", "notmatch.estimateTimeHours", "* укажите корректный скрок");
            }
        }

        //by agreement price
        if (view.isPriceAgreement() && view.getPrice() != 0) {
            errors.rejectValue("price", "notmatch.price", "* вы выбрали \"" + checkboxType + "\"");
        }

        //no agreement price
        if ((!view.isPriceAgreement()) && view.getPrice() <= 0) {
            errors.rejectValue("price", "notmatch.price", "* укажите корректную цену");
        }
    }
}
