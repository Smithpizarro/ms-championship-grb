# ms-championship-grb
championship of tennis

Request example
POST: http://localhost:8092/championship/tournaments
{
 "name": "tenis" ,
 "genderType": "masculino",
 "dateTournament" : "2022-11-11",
 "players" : [
  {
   "name": "mario",
   "gender": "masculino",
   "skill":  80,
   "strength": 15,
   "speed": 12
  },
  {
   "name": "john",
   "gender": "masculino",
   "skill":  80,
   "strength": 15,
   "speed": 13   
  }
  ]
}

GET: http://localhost:8092/championship/tournaments?gender=femenino
GET: http://localhost:8092/championship/tournaments?startDate=2022-11-10&endDate=2022-11-11
