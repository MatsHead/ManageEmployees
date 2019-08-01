package pl.matshead.examapp.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.matshead.examapp.model.validator.PersonalIdConstraint;
import pl.matshead.examapp.static_values.Position;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull()
    @Size(min=3, max=30, message="Name not be empty, provide at least 3 characters")
    private String name;
    @NotNull()
    @Size(min=3, max=30, message="Surname not be empty, provide at least 3 characters")
    private String surname;
    @NotNull(message = "Provide personal identity, format number")
    // Custom Annotation to valid proper value for personal id
    @PersonalIdConstraint
    private String personalId;
    /**
     * Address is represents by other object but hold in one table together with Employee
     */
    @Embedded
    private Address address;
    private Position position;
    private double salary;
    private boolean children;
    @OneToOne
    private Department department;

    public Employee(String name, String surname, String personalId, Address address, Position position, double salary, boolean children, Department department) {
        this.name = name;
        this.surname = surname;
        this.personalId = personalId;
        this.address = address;
        this.position = position;
        this.salary = salary;
        this.children = children;
    }

    public Employee(String name, String surname, String personalId, Address address,  Position position, double salary, boolean children) {
        this.name = name;
        this.surname = surname;
        this.personalId = personalId;
        this.address = address;
        this.position = position;
        this.salary = salary;
        this.children = children;
    }

}
