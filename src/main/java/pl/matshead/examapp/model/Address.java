package pl.matshead.examapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.validation.constraints.*;

/**
 * Class holding some address values
 * No need to be separate enity sa its
 * marked as Embeddable and used in Department and Employee
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Address {
    @NotNull
    @Size(min = 3, max = 15)
    private String country;
    @NotNull
    @Size(min = 3, max = 15)
    private String city;
    @NotNull
    @Size(min = 3, max = 15)
    private String street;
    private String flat;
    private String house;
    @NotNull
    @Size(min = 3, max = 15)
    private String zipCode;
    @Email
    private String email;
}
