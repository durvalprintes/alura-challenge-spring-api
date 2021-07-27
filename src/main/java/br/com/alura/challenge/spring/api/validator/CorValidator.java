package br.com.alura.challenge.spring.api.validator;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CorValidator implements ConstraintValidator<Cor, String> {

    @Override
    public void initialize(Cor cor) {
        cor.message();
    }

    @Override
    public boolean isValid(String cor, ConstraintValidatorContext context) {
        Boolean isHEX = true;
        if (cor != null && !cor.trim().isEmpty())
            isHEX = Pattern.compile("^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$").matcher(cor).matches();
        return isHEX;
    }

}
