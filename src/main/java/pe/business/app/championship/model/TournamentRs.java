package pe.business.app.championship.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
//@Builder
public class TournamentRs {

 public String name;
 public Date dateTournament;
 public String result;
 public PlayerRq winner;
 public TournamentRs() {

 }
}
