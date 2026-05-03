package edu.isgb.School.controllers;

import edu.isgb.School.entities.*;
import edu.isgb.School.services.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/school")
public class TestSchoolController {

    @Autowired
    private SchoolService schoolService;

    // ── a) POST /school/add ─────────────────────────────────────────
    @PostMapping("/add")
    public ResponseEntity<?> addSchool(@RequestBody School school) {
        try {
            School saved = schoolService.createSchool(school);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erreur création School : " + e.getMessage());
        }
    }

    // ── b) GET /school/{id} ─────────────────────────────────────────
    @GetMapping("/{id}")
    public ResponseEntity<?> getSchool(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(schoolService.getSchoolById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    // ── c) POST /school/student/add/{schoolId} ──────────────────────
    @PostMapping("/student/add/{schoolId}")
    public ResponseEntity<?> addStudent(
            @RequestBody Student student,
            @PathVariable Integer schoolId) {
        try {
            Student saved = schoolService.createStudent(student, schoolId);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    // ── d) GET /school/students ─────────────────────────────────────
    @GetMapping("/students")
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(schoolService.getAllStudents());
    }

    // ── e) POST /school/instructor/add ──────────────────────────────
    @PostMapping("/instructor/add")
    public ResponseEntity<?> addInstructor(@RequestBody Instructor instructor) {
        try {
            Instructor saved = schoolService.createInstructor(instructor);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erreur création Instructor : " + e.getMessage());
        }
    }

    // ── f) GET /school/instructor/search?name= ──────────────────────
    @GetMapping("/instructor/search")
    public ResponseEntity<List<Instructor>> searchInstructors(
            @RequestParam String name) {
        return ResponseEntity.ok(schoolService.getInstructorsByName(name));
    }

    // ── g) GET /school/instructor/{id} ──────────────────────────────
    @GetMapping("/instructor/{id}")
    public ResponseEntity<?> getInstructor(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(schoolService.getInstructorById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    // ── h) GET /school/course/{id} ──────────────────────────────────
    @GetMapping("/course/{id}")
    public ResponseEntity<?> getCourse(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(schoolService.getCourseById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    // ── i) GET /school/instructor/{id}/courses ──────────────────────
    @GetMapping("/instructor/{id}/courses")
    public ResponseEntity<?> getInstructorCourses(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(schoolService.getCoursesByInstructor(id));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    // ── j) POST /school/instructor/{instructorId}/course/add ────────
    // Crée un nouveau Course et le lie à l'Instructor existant
    // Retourne l'Instructor avec la liste complète de ses courses
    // Body JSON : { "name": "Nom du cours" }
    @PostMapping("/instructor/{instructorId}/course/add")
    public ResponseEntity<?> addNewCourseToInstructor(
            @PathVariable Integer instructorId,
            @RequestBody Course course) {
        try {
            Instructor updated = schoolService.addNewCourseToInstructor(instructorId, course);
            return ResponseEntity.status(HttpStatus.CREATED).body(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }
}