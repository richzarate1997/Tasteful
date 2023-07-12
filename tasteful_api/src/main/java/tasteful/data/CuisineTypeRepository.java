package tasteful.data;

import org.springframework.data.jpa.repository.JpaRepository;
import tasteful.models.CuisineType;

public interface CuisineTypeRepository extends JpaRepository<CuisineType, Long> {
}
