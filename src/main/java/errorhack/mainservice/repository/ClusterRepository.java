package errorhack.mainservice.repository;

import errorhack.mainservice.entity.Cluster;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClusterRepository extends JpaRepository<Cluster, Long> {
}
