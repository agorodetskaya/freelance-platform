package application.validator;

import application.view.TaskRequestView;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
public class TaskRequestViewValidator extends AbstractPriceAndEstimateTimeValidator {
    @Override
    public boolean supports(Class<?> clazz) {
        return TaskRequestView.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        checkboxType = "Согласен(а) с условиями";
        super.validate(target, errors);
    }
}
