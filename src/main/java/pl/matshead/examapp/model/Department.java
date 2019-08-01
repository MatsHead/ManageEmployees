package pl.matshead.examapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data

@Entity
@Table(name = "department")
public class Department {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;
//    @NotBlank(message="Name not be blank!")
    private String name;
    /**
     * Address is represents by other object but hold in one table together with Department
     */
//    @NotBlank(message="Address not be blank!")
    @Embedded
    private Address address;
    @OneToMany(mappedBy = "department", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private List<Employee> employee;


    public Department(Address address, String name) {
        this.address = address;
        this.name = name;
    }

}
