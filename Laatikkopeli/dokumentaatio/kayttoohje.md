# Käyttöohje
### Ohjelman asentaminen
Katso README. Suoritettavan jar-tiedoston voi joko luoda kansiossa Laatikkopeli komennolla "mvn package" tai lataamalla viimeisin release (kts. julkaisut)
Jos ohjelma menee 'jumiin' ensimmäisellä käyttökerralla, kokeile avata ohjelma uudelleen.  
### Pelaajan luominen
Pelaaja luodaan "Uusi pelaaja" -painikkeella. Tästä painikkeesta aukeaa näkymä, jossa käyttäjä keksii käyttäjänimem ja salasanan.  
- Käyttäjänimi ei saa olla sama kuin jollain toisella pelaajalla
- Käyttäjänimen ja salasanan pituudessa ei ole rajoituksia.  

Kun nimi ja salasana on keksitty, painetaan 'luo uusi' -painiketta. Tämän jälkeen käyttäjän luoma pelaaja pitäisi löytyä tietokannasta ja käyttäjä voi kirjautua kyseisellä käyttäjänimellä ohjelmaan.
### Kirjautuminen
Kirjautuminen tapahtuu klikkaamalla toimivaa 'kirjaudu'-painiketta jommassakummassa yläkulmassa. Tästä aukeaa kirjautumisnäkymä, jossa käyttäjä syöttää käyttäjänimensä ja salasanan.
Jos kirjautuminen ei onnistu, ohjelma ilmoittaa tähän syyn. Onnistunut kirjautuminen sijoittaa pelaajan nimen ja avatar-kuvan jompaankumpaan yläkulmaan. Pelaaja on valmis liittymään peliin.
### Yksinpeli
Painamalla yksinpeli-painiketta, avautuu pelin valinta näkymä, jossa pelaaja voi valita jonkin yhdelle pelaajalle suunnitelluista pelialueista.
### Pelaaminen
Pelaajan hahmo on ruudukolla. Painamalla nuolipainikkeita, pelaaja voi siirtää pelihahmoaan. Tehtävänä on työntää pelaajan laatikko onnistuneesti portista sisään.
#### Askelten laskeminen
Peli laskee pelaajan ottamat askeleet. Pienimmällä askelmäärällä läpäisty pelialue tuottaa tiedon ennätyksestä. (Tullaan myöhemmin tallentamaan tietokantaan)
