package edu.isgb.School.entities;



import jakarta.persistence.*;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "t_address")
public class Address implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PK_ADDRESS")
    private Integer idAddress;

    @Column(name = "cl_street")
    private String street;

    @Column(name = "cl_city")
    private String city;

    @Column(name = "cl_postal_Code")
    private String postalCode;


}
