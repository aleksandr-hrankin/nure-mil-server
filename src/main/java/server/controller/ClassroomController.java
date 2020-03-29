package server.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import server.domain.Classroom;
import server.repo.ClassroomRepo;


import java.util.List;

@RestController
@RequestMapping("/classroom")
public class ClassroomController {

    private final ClassroomRepo classroomRepo;

    @Autowired
    public ClassroomController(ClassroomRepo classroomRepo) {
        this.classroomRepo = classroomRepo;
    }

    @GetMapping
    public List<Classroom> getAllClassroom() {
        return classroomRepo.findAll();
    }

    @PostMapping
    public Classroom add(@RequestBody Classroom classroom) {
        return classroomRepo.save(classroom);
    }

    @PutMapping("{id}")
    public Classroom update(
            @PathVariable("id") Classroom classroomFromDb,
            @RequestBody Classroom classroom
    ) {
        BeanUtils.copyProperties(classroom, classroomFromDb, "id");
        return classroomRepo.save(classroom);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Classroom classroom) {
        classroomRepo.delete(classroom);
    }
}
