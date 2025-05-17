package errorhack.mainservice.repository;

import errorhack.mainservice.entity.PackageError;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PackageRepository extends JpaRepository<PackageError, Long> {
}
