package pe.business.app.championship.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.business.app.championship.model.PlayerRq;
import pe.business.app.championship.model.TournamentRq;
import pe.business.app.championship.model.TournamentRs;
import pe.business.app.championship.repository.DepartureRepository;
import pe.business.app.championship.repository.PlayerRepository;
import pe.business.app.championship.repository.TournamentRepository;
import pe.business.app.championship.repository.entity.Departure;
import pe.business.app.championship.repository.entity.Player;
import pe.business.app.championship.repository.entity.Tournament;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class ChampionshipServiceImpl implements ChampionshipService {

    @Autowired
    TournamentRepository tournamentRepository;

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    DepartureRepository departureRepository;

    @Override
    public List<TournamentRs> findTournamentAll() {

        List<TournamentRs> tournamentListRs = new ArrayList<>();
        List<Tournament> tournamentList = tournamentRepository.findAll();
        for (Tournament tr : tournamentList){
        Departure deap =  departureRepository.findByTournament(tr);
        TournamentRs trRs = new TournamentRs();
        trRs.setName(tr.getName());
        trRs.setDateTournament(tr.getDateTournament());
        trRs.setResult(deap.getResult());
        PlayerRq plRq = new PlayerRq();
        BeanUtils.copyProperties(deap.getPlayer(),plRq);
        trRs.setWinner(plRq);
        tournamentListRs.add(trRs);
        }
       return  tournamentListRs;
    }

    @Override
    public List<TournamentRs> findTournamentByGender(String gender) {

        List<TournamentRs> tournamentListRs = new ArrayList<>();
        List<Tournament> tournamentList = tournamentRepository.findByGenderType(gender);
        for (Tournament tr : tournamentList){
            Departure deap =  departureRepository.findByTournament(tr);
            TournamentRs trRs = new TournamentRs();
            trRs.setName(tr.getName());
            trRs.setDateTournament(tr.getDateTournament());
            trRs.setResult(deap.getResult());
            PlayerRq plRq = new PlayerRq();
            BeanUtils.copyProperties(deap.getPlayer(),plRq);
            trRs.setWinner(plRq);
            tournamentListRs.add(trRs);
        }
        return  tournamentListRs;
    }

    @Override
    public List<TournamentRs> findTournamentByDateTournamentBetween(Date startDate , Date endDate) {

        List<TournamentRs> tournamentListRs = new ArrayList<>();
        List<Tournament> tournamentList = tournamentRepository.findByDateTournamentBetween(startDate, endDate);
        for (Tournament tr : tournamentList){
            Departure deap =  departureRepository.findByTournament(tr);
            TournamentRs trRs = new TournamentRs();
            trRs.setName(tr.getName());
            trRs.setDateTournament(tr.getDateTournament());
            trRs.setResult(deap.getResult());
            PlayerRq plRq = new PlayerRq();
            BeanUtils.copyProperties(deap.getPlayer(),plRq);
            trRs.setWinner(plRq);
            tournamentListRs.add(trRs);
        }
        return  tournamentListRs;
    }

    @Override
    public TournamentRs createCustomer(TournamentRq tournamentRq) {

        TournamentRs tournamentRs = new TournamentRs();
        int countPlayers =   tournamentRq.getPlayers().size();
        PlayerRq[] array = new PlayerRq[tournamentRq.getPlayers().size()];
        tournamentRq.getPlayers().toArray(array);

        Tournament tournament = new Tournament();
        BeanUtils.copyProperties(tournamentRq,tournament);
        tournament = tournamentRepository.save (tournament);

        Player player  = new Player();
        PlayerRq winner = sendWinner(countPlayers, array);
        BeanUtils.copyProperties(winner,player);
        player.setSkill(winner.getSkill());
        player.setStrength(winner.getStrength());
        player.setSpeed(winner.getSpeed());
        player.setCreatedBy("admin");
        player = playerRepository.save (player);

        Departure departure = new Departure();
        departure.setPlayer(player);
        departure.setResult("final : " + (player.getSkill()+player.getSpeed()+ player.getStrength()));
        departure.setTournament(tournament);
        departure.setCreatedBy("admin");
        departureRepository.save(departure);

        tournamentRs.setName(tournamentRq.getName());
        tournamentRs.setDateTournament(tournament.getDateTournament());
        tournamentRs.setResult(departure.getResult());
        tournamentRs.setWinner(winner);

        return tournamentRs;
    }

    private PlayerRq sendWinner (int countPlayers , PlayerRq[] array ){
        PlayerRq winner = null;
        StringBuilder builder = new StringBuilder();
        if (countPlayers==2) {
            winner =array[0];
            Long scorejg1 = array[0].skill + array[0].speed + array[0].strength;
            Long scorejg2 = array[1].skill + array[1].speed + array[1].strength;

            if(scorejg2 > scorejg1){
              winner= array[1];
            }

        } else {
            PlayerRq[] myArray1 = Arrays.copyOfRange(array, 0, array.length / 2);
            PlayerRq winner1 = sendWinner(array.length / 2, myArray1);
            PlayerRq[] myArray2 = Arrays.copyOfRange(array, array.length / 2, array.length);
            PlayerRq winner2 = sendWinner(array.length / 2, myArray2);
            winner =winner1;
            Long scorejg11 = winner1.skill + winner1.speed+ winner1.strength;
            Long scorejg22 = winner2.skill + winner2.speed + winner2.strength;

            if(scorejg22 > scorejg11){
                winner= winner2;
            }
        }
       return  winner;
    }


}
