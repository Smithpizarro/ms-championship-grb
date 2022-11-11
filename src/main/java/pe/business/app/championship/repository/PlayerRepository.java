package pe.business.app.championship.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.business.app.championship.repository.entity.Player;


import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player,Long> {

}
