package server.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import server.domain.Teacher;
import server.repo.TeacherRepo;

import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    private final TeacherRepo teacherRepo;

    @Autowired
    public TeacherController(TeacherRepo teacherRepo) {
        this.teacherRepo = teacherRepo;
    }

    @GetMapping
    public List<Teacher> getAllTeachers() {
        return teacherRepo.findAll();
    }

    @PostMapping
    public Teacher add(@RequestBody Teacher teacher) {
        return teacherRepo.save(teacher);
    }

    @PutMapping("{id}")
    public Teacher update(
            @PathVariable("id") Teacher teacherFromDb,
            @RequestBody Teacher teacher) {
        BeanUtils.copyProperties(teacher, teacherFromDb, "id");
        return teacherRepo.save(teacher);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Teacher teacher) {
        teacherRepo.delete(teacher);
    }
}
