package server.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import server.domain.Lesson;
import server.repo.LessonRepo;

import java.util.List;

@RestController
@RequestMapping("/lesson")
public class LessonController {

    private final LessonRepo lessonRepo;

    @Autowired
    public LessonController(LessonRepo lessonRepo) {
        this.lessonRepo = lessonRepo;
    }

    @GetMapping
    public List<Lesson> getAllLessons() {
        return lessonRepo.findAll();
    }

    @PostMapping
    public Lesson add(@RequestBody Lesson lesson) {
        return lessonRepo.save(lesson);
    }

    @PutMapping("{id}")
    public Lesson update(@PathVariable("id") Lesson lessonFromDb, @RequestBody Lesson lesson) {
        BeanUtils.copyProperties(lesson, lessonFromDb, "id");
        return lessonRepo.save(lesson);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Lesson lesson) {
        lessonRepo.delete(lesson);
    }


}
