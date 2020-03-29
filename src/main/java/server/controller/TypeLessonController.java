package server.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import server.domain.TypeLesson;
import server.repo.TypeLessonRepo;

import java.util.List;

@RestController
@RequestMapping("/type-lesson")
public class TypeLessonController {

    private final TypeLessonRepo typeLessonRepo;

    @Autowired
    public TypeLessonController(TypeLessonRepo typeLessonRepo) {
        this.typeLessonRepo = typeLessonRepo;
    }

    @GetMapping
    public List<TypeLesson> getAllTypeLesson() {
        return  typeLessonRepo.findAll();
    }

    @PostMapping
    public TypeLesson add(@RequestBody TypeLesson typeLesson) {
        return typeLessonRepo.save(typeLesson);
    }

    @PutMapping("{id}")
    public TypeLesson update(
            @PathVariable("id") TypeLesson typeLessonFromDb,
            @RequestBody TypeLesson typeLesson
    ) {
        BeanUtils.copyProperties(typeLesson, typeLessonFromDb, "id");
        return typeLessonRepo.save(typeLesson);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") TypeLesson typeLesson) {
        typeLessonRepo.delete(typeLesson);
    }
}
