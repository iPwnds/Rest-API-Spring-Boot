package be.ucll.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "my_ponies")
public class Pony {

    @NotBlank(message = "name may not be empty")
    private String name;

    @Positive(message = "age must be positive")
    private int age;

    @Min(value = 40, message = "pony must be larger than 40 cm")
    @Max(value = 147, message = "pony must be smaller than 147 cm")
    private int size;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;

    protected Pony() {}

    public Pony(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void updateNameAndAge(String name, int age) {
        this.name = name;
        this.age = age;
    }
}