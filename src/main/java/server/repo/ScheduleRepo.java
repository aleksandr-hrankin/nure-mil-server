package server.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import server.domain.Schedule;

public interface ScheduleRepo extends JpaRepository<Schedule, Long> {
}
