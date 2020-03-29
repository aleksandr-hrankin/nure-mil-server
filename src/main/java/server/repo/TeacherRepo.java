package server.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import server.domain.Teacher;

public interface TeacherRepo extends JpaRepository<Teacher, Long> {
}
