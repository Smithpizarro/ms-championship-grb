package pe.business.app.championship.service;


import pe.business.app.championship.model.TournamentRq;
import pe.business.app.championship.model.TournamentRs;
import pe.business.app.championship.repository.entity.Tournament;

import java.util.Date;
import java.util.List;

public interface ChampionshipService {

    public TournamentRs createCustomer(TournamentRq customer);
    public List<TournamentRs> findTournamentAll();
    public List<TournamentRs> findTournamentByGender(String gender);
    public List<TournamentRs> findTournamentByDateTournamentBetween(Date startDate , Date endDate);


}
