package be.ucll;

import be.ucll.model.Pony;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PonyTest {

    private static ValidatorFactory validatorFactory ;

    private static Validator validator ;

    @BeforeAll
    public static void createValidator() {
        validatorFactory= Validation.buildDefaultValidatorFactory ();
        validator= validatorFactory .getValidator();
    }

    @Test
    void givenNegativeAge_whenCreatingPony_thenAgeViolationIsThrown() {
        Pony tornado = new Pony("Tornado",
                -5);
        Set<ConstraintViolation<Pony>> violations = validator .validate(tornado);
        assertEquals (1, violations.size());
        ConstraintViolation<Pony> violation = violations.iterator().next();
        assertEquals ("age must be positive"
                , violation.getMessage());
    }

    @AfterAll
    public static void close() {
        validatorFactory .close();
    }

}
