package pe.business.app.championship.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.business.app.championship.repository.entity.Tournament;

import java.util.Date;
import java.util.List;

@Repository
public interface TournamentRepository extends JpaRepository<Tournament,Long> {

    public List<Tournament> findByGenderType(String gender);
    public List<Tournament> findByDateTournamentBetween(Date start, Date end);
}
