package tasteful.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tasteful.models.Unit;
@Repository
public interface UnitRepository extends JpaRepository<Unit, Long>{
}
