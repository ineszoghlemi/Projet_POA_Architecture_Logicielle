package edu.isgb.School.services;

import edu.isgb.School.entities.*;
import edu.isgb.School.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class SchoolService {

    @Autowired private SchoolRepository     schoolRepo;
    @Autowired private StudentRepository    studentRepo;
    @Autowired private InstructorRepository instructorRepo;
    @Autowired private CourseRepository     courseRepo;

    // ── a) Créer une School ─────────────────────────────────────────
    public School createSchool(School school) {
        if (school.getDepartements() != null) {
            for (Departement d : school.getDepartements()) {
                d.setSchool(school);
            }
        }
        return schoolRepo.save(school);
    }

    // ── b) Récupérer une School par id ──────────────────────────────
    public School getSchoolById(Integer id) {
        School school = schoolRepo.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("School non trouvée : " + id));
        school.getStudents().size();
        school.getDepartements().size();
        school.getInstructors().size();
        return school;
    }

    // ── c) Créer un Student avec Address et School ──────────────────
    public Student createStudent(Student student, Integer schoolId) {
        School school = schoolRepo.findById(schoolId)
                .orElseThrow(() ->
                        new RuntimeException("School non trouvée : " + schoolId));
        student.setSchool(school);
        return studentRepo.save(student);
    }

    // ── d) Lister tous les Students ─────────────────────────────────
    public List<Student> getAllStudents() {
        List<Student> students = studentRepo.findAll();
        students.forEach(s -> {
            if (s.getAddress() != null) s.getAddress().getCity();
        });
        return students;
    }

    // ── e) Créer un Instructor (avec ses cours) ──────────────────────
    public Instructor createInstructor(Instructor instructor) {
        if (instructor.getCourses() == null) {
            instructor.setCourses(new ArrayList<>());
        }
        return instructorRepo.save(instructor);
    }

    // ── f) Lister Instructors par nom ────────────────────────────────
    public List<Instructor> getInstructorsByName(String name) {
        return instructorRepo.findByName(name);
    }

    // ── g) Récupérer un Instructor par id ───────────────────────────
    public Instructor getInstructorById(Integer id) {
        Instructor instructor = instructorRepo.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Instructor non trouvé : " + id));
        instructor.getCourses().size(); // forcer chargement LAZY
        return instructor;
    }

    // ── h) Récupérer un Course par id ───────────────────────────────
    public Course getCourseById(Integer id) {
        return courseRepo.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Course non trouvé : " + id));
    }

    // ── i) Lister les Courses d'un Instructor ───────────────────────
    public List<Course> getCoursesByInstructor(Integer instructorId) {
        Instructor instructor = getInstructorById(instructorId);
        return new ArrayList<>(instructor.getCourses());
    }

    // ── j) Créer un nouveau Course et le lier à un Instructor existant
    // Retourne l'Instructor avec la liste complète de ses courses
    public Instructor addNewCourseToInstructor(Integer instructorId, Course course) {
        Instructor instructor = getInstructorById(instructorId);

        // 1. Persister le nouveau Course en base
        Course savedCourse = courseRepo.save(course);

        // 2. Lier le Course à l'Instructor
        instructor.getCourses().add(savedCourse);
        Instructor saved = instructorRepo.save(instructor);

        // 3. Forcer le rechargement de la liste courses dans la transaction
        saved.getCourses().size();

        return saved;
    }
}