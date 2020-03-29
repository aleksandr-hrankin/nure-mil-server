package server.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import server.domain.TypeLesson;

public interface TypeLessonRepo extends JpaRepository<TypeLesson, Long> {
}
