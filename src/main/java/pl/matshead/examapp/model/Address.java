package pl.matshead.examapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

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
    private String country;
    private String city;
    private String street;
    private String flat;
    private String house;
    private String zipCode;
    private String email;
}
