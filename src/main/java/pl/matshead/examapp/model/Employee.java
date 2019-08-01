package pl.matshead.examapp.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import pl.matshead.examapp.static_values.Position;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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
//    @NotBlank(message="Name not be blank!")
//    @Size(min=2, max=30)
    private String name;
//    @NotBlank(message="Surname not be blank!")
//    @Size(min=2, max=30)
    private String surname;
    private Long personalId;
    /**
     * Address is represents by other object but hold in one table together with Employee
     */
    @Embedded
//    @NotNull(message="Address not be blank!")
    private Address address;
    private Position position;
    private double salary;
    private boolean children;
    @OneToOne
    private Department department;

    public Employee(String name, String surname, Long personalId, Address address, Position position, double salary, boolean children, Department department) {
        this.name = name;
        this.surname = surname;
        this.personalId = personalId;
        this.address = address;
        this.position = position;
        this.salary = salary;
        this.children = children;
    }

    public Employee(String name, String surname, Long personalId, Address address,  Position position, double salary, boolean children) {
        this.name = name;
        this.surname = surname;
        this.personalId = personalId;
        this.address = address;
        this.position = position;
        this.salary = salary;
        this.children = children;
    }

}
