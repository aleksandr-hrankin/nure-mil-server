package server.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import server.domain.Schedule;
import server.repo.ScheduleRepo;

import java.util.List;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    private final ScheduleRepo scheduleRepo;

    @Autowired
    public ScheduleController(ScheduleRepo scheduleRepo) {
        this.scheduleRepo = scheduleRepo;
    }

    @GetMapping
    public List<Schedule> getAllSchedule() {
        return scheduleRepo.findAll();
    }

    @PostMapping
    public Schedule add(@RequestBody Schedule schedule) {
        return scheduleRepo.save(schedule);
    }

    @PutMapping("{id}")
    public Schedule update(
            @PathVariable("id") Schedule scheduleFromDb,
            @RequestBody Schedule schedule
    ) {
        BeanUtils.copyProperties(schedule, scheduleFromDb, "id");
        return scheduleRepo.save(schedule);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Schedule schedule) {
        scheduleRepo.delete(schedule);
    }
}
