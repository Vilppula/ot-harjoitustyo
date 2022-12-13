# Changelog
### Viikko 3
- Käyttäjä voi käynnistää sovelluksen ja se avaa päänäkymän
- Käyttäjä voi "Uusi pelaaja" painikkeella siirtyä kirjautumisnäkymään
- Kirjautumisnäkymästä voi palata x-painikkeella
- Uudelle käyttäjälle voi antaa käyttäjänimen ja salasanan (eivät tallennu vielä mihinkään)
  
### Viikko 4
 - Ohjelma luo tietokannan jossa on kaksi taulua käyttäjille ja pelituloksille
 - Käyttäjä voi luoda pelaajanimen ja salasanan, jotka tallennetaan tietokantaan
 - Ohjelma ei anna luoda kahta saman nimistä käyttäjää
 - Hahmotelma pelinäkymälle

### Viikko 5
- Alkunäkymä sisältää kirjautumismahdollisuuden kahdelle pelaajalle
- Pelaajille on asetettu oletusarvoinen profiilikuva
- Yksin- tai kaksinpelipainikkeilla pääsee pelinäkymään
- Pelinäkymä sisältää inforuudun sekä pelialueen
- Inforuudussa näytetään kirjautuneen/kirjautuneiden pelaajien tiedot
- Pelialueelle ladataan layout-instanssin määrittelemä pelinäkymä, joka koostuu erilaisista image-olioista
- Pelaaja-oliota voi liikutella ja ne voivat työntää laatikko-olioita

### Viikko 6
- Algoritmi laskee lyhyimmän reitin (ainakin yhden pelaajan pelialueella).
- Pelin inforuutu päivittää käytetyt askeleet ja lyhyimmän reitin kustakin pelitilanteesta.
- DAO (ei I/O:ta) tarjoaa listan pelialueista, joista pelaaja voi valita.
- Peli päättyy tappioon jos laatikkoa ei voi enää saada maaliin. Tästä tulee ilmoitus.
- Peli päättyy voittoon jos laatikko saadaan maaliin. Tästä tulee ilmoitus.
- Pelin voi aloittaa alusta.
- Ohjelman ulkoasua on päivitetty.
