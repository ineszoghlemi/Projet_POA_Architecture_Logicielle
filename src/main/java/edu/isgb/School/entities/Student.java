package edu.isgb.School.entities;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;
import lombok.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_student")
public class Student implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PK_Student")
    private Integer idStudent;

    @Column(name = "cl_name_student")
    private String name;

    @Column(name = "cl_birthdate")
    @Temporal(TemporalType.DATE)
    private Date birthDate;

    // OneToOne : pas de cycle → Address s'affiche normalement
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_PK_ADDRESS")
    private Address address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_PK_school")
    @JsonIgnoreProperties({"students", "departements", "instructors"})

    private School school;




}
