package edu.isgb.School.entities;




import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
@Getter                // génère tous les getters
@Setter                // génère tous les setters
@NoArgsConstructor     // constructeur vide — requis par JPA/Hibernate
@AllArgsConstructor    // constructeur avec (idSchool, name, phone, departments, students, instructors)
@Entity
@Table(name = "t_school")
public class School implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PK_school")
    private Integer idSchool;

    @Column(name = "cl_name_school")
    private String name;

    @Column(name = "cl_phone")
    private Integer phone;

    @OneToMany(mappedBy = "school",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            fetch = FetchType.LAZY)
    @JsonIgnoreProperties("school")   // remplace @JsonManagedReference

    private List<Departement> departements;

    @OneToMany(mappedBy = "school",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            fetch = FetchType.LAZY)
    @JsonIgnoreProperties("school")   // affiche tous les students

    private List<Student> students;

    @OneToMany(mappedBy = "school",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            fetch = FetchType.LAZY)
    @JsonIgnoreProperties("school")

    private List<Instructor> instructors;





}
