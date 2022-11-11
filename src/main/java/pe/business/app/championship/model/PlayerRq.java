package pe.business.app.championship.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
//@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlayerRq {

 public String name;
 public String gender;
 public Long skill;
 public Long strength;
 public Long speed;
 public Long reaction;

 public PlayerRq() {
 }

}
