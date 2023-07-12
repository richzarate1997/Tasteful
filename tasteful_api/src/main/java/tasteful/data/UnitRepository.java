package tasteful.data;

import org.springframework.data.jpa.repository.JpaRepository;
import tasteful.models.Unit;

public interface UnitRepository extends JpaRepository<Unit, Long>{
}
