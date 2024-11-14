package jdbc.timezone;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeEntityRepository extends JpaRepository<TimeEntity, Long> {
}
