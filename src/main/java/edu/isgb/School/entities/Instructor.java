package edu.isgb.School.entities;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_instructor")
public class Instructor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_Instructor")
    private Integer idInstructor;

    @Column(name = "name_Instructor")
    private String name;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            fetch = FetchType.LAZY)
    @JoinTable(name = "t_instructor_t_course",
            joinColumns        = @JoinColumn(name = "t_instructor_pk_Instructor"),
            inverseJoinColumns = @JoinColumn(name = "courses_pk_course"))
    @JsonIgnoreProperties("instructors")   // évite boucle Course→Instructor

    private List<Course> courses = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_PK_school")
    @JsonIgnoreProperties({"students", "departements", "instructors"})

    private School school;



}
