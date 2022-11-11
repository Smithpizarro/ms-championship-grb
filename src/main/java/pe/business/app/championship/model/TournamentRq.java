package pe.business.app.championship.model;


import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Setter
@Getter
//@Builder
public class TournamentRq {

 public String name;
 public String genderType;
 public Date dateTournament;

 public List<PlayerRq> players;

 public TournamentRq() {
 }

}
