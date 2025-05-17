package errorhack.mainservice.repository;

import errorhack.mainservice.entity.Version;
import errorhack.mainservice.entity.VersionStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VersionRepository extends JpaRepository<Version, Long> {
    Optional<Version> getVersionByUrl(String url);
}
