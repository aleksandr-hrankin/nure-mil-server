package server.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import server.domain.Classroom;

public interface ClassroomRepo extends JpaRepository<Classroom, Long> {
}
