package application.validator;

import application.view.TaskView;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
public class TaskViewValidator extends AbstractPriceAndEstimateTimeValidator {
    @Override
    public boolean supports(Class clazz) {
        return TaskView.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        checkboxType = "По договоренности";
        super.validate(target, errors);
    }
}
