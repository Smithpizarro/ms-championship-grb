package pe.business.app.championship.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pe.business.app.championship.model.*;
import pe.business.app.championship.repository.entity.Tournament;
import pe.business.app.championship.service.ChampionshipService;


import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/championship")
public class ChampionshipController {

    @Autowired
    ChampionshipService championshipService;


    @PostMapping(path = "/tournaments")
    public ResponseEntity<TransactionRs<TournamentRs>> createTournament(@Valid @RequestBody TournamentRq tournamentRq, BindingResult result) {

        TransactionRs<TournamentRs> response = new TransactionRs<TournamentRs>();
        if (result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }
        if (!potenciaDos(tournamentRq.getPlayers().size())){
            response.setDescripcion("Error: tiene que ser potencia de 2");
            return  ResponseEntity.status( HttpStatus.CONFLICT).body(response);
        }else if (!validarGeneroTorneo(tournamentRq.getPlayers(), tournamentRq.getGenderType()) ) {
            response.setDescripcion("Error: Los jugadores deben ser mismo genero");
            return  ResponseEntity.status( HttpStatus.CONFLICT).body(response);
        }
        TournamentRs tournamentRs = championshipService.createCustomer (tournamentRq);
        response.setRespuesta(tournamentRs);
        response.isSuccess();

        return  ResponseEntity.status( HttpStatus.OK).body(response);
    }

    @GetMapping(path = "/tournaments")
    public ResponseEntity<TransactionRs<List<TournamentRs>>> listAllTournament(@RequestParam(name = "gender" , required = false) String gender,
                                                                               @RequestParam(name = "startDate" , required = false) String startDate,
                                                                               @RequestParam(name = "endDate" , required = false) String endDate) throws ParseException {

        TransactionRs<List<TournamentRs>> response = new TransactionRs<List<TournamentRs>>();
        List<TournamentRs> tournaments =  new ArrayList<>();
         if (null ==  gender && startDate== null && endDate==null) {
             tournaments = championshipService.findTournamentAll();

        }else{
              if(gender!=null) {
                 tournaments = championshipService.findTournamentByGender(gender);

             }else{
                  Date start = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
                  Date end = new SimpleDateFormat("yyyy-MM-dd").parse(endDate);
                 tournaments = championshipService.findTournamentByDateTournamentBetween(start, end);
             }
        }
        if (tournaments.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        response.setRespuesta(tournaments);
        response.isSuccess();

        return  ResponseEntity.status( HttpStatus.OK).body(response);
    }


    private String formatMessage( BindingResult result){
        List<Map<String,String>> errors = result.getFieldErrors().stream()
                .map(err ->{
                    Map<String,String>  error =  new HashMap<>();
                    error.put(err.getField(), err.getDefaultMessage());
                    return error;

                }).collect(Collectors.toList());
        ErrorMessage errorMessage = ErrorMessage.builder()
                .code("01")
                .messages(errors).build();
        ObjectMapper mapper = new ObjectMapper();
        String jsonString="";
        try {
            jsonString = mapper.writeValueAsString(errorMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }

    boolean potenciaDos (int n)
    {
        int potencia = 2;
        while(potencia <= n) {
            if (n == potencia)
                return true;
            potencia *= 2;
        }
        return false;
    }

    boolean validarGeneroTorneo (List<PlayerRq> playerRqs, String gender)
    {
      for (PlayerRq playerRq : playerRqs) {
            if (!playerRq.getGender().equals(gender))
                return false;
      }
        return true;
    }

}
