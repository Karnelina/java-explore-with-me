package ru.practicum.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;

public class DateValidator implements ConstraintValidator<EventDateValidator, LocalDateTime> {

    long hour = 2L;

    @Override
    public void initialize(EventDateValidator constraintAnnotation) {
    }

    @Override
    public boolean isValid(LocalDateTime eventDate, ConstraintValidatorContext constraintValidatorContext) {
        LocalDateTime now = LocalDateTime.now();
        if (eventDate != null) {
            return eventDate.isAfter(now.plusHours(hour));
        } else {
            return true;
        }
    }
}
