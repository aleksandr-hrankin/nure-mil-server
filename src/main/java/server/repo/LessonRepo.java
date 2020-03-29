package server.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import server.domain.Lesson;

public interface LessonRepo extends JpaRepository<Lesson, Long> {
}
