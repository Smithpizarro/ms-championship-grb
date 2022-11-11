package pe.business.app.championship.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.business.app.championship.repository.entity.Departure;
import pe.business.app.championship.repository.entity.Tournament;

@Repository
public interface DepartureRepository extends JpaRepository<Departure,Long> {

    public Departure findByTournament(Tournament tournament);
}
