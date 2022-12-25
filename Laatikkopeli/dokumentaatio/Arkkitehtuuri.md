# Ohjelman arkkitehtuurikuvaus

## Pakkauskaavio
<img src="https://github.com/Vilppula/ot-harjoitustyo/blob/master/Laatikkopeli/dokumentaatio/img/pakkauskaavio.png" width="800">  
Pakkauskaaviosta selviää ohjelman kerrosrakenne. Pysyväistallennus on toteutettu DAO-suunnittelumallia hyödyntämällä niin, että käyttäjätiedot
ja pelitulokset tallennetaan SQLite tietokantaan. Pelialueiden lataaminen tapahtuu tiedostoista lukemalla (GameAreaDao).  

## Luokkakuvaus
<img src="https://github.com/Vilppula/ot-harjoitustyo/blob/master/Laatikkopeli/dokumentaatio/img/ProgramStructure.png" width="800">    
Ohjelma on toteutettu JAVAFX FXML-projektina. Kaaviossa esitellyt controller-luokkien yhteys tapahtuu FXMLLoader-instanssin välityksenä niin,
että FXML-hierarkiassa ylempänä oleva controller-olio huolehtii yhteyden muodostamisesta alempiin contoller-olioihin (tai niiden välille).

## Logiikka

### Sisään kirjautuminen
<img src="https://github.com/Vilppula/ot-harjoitustyo/blob/master/Laatikkopeli/dokumentaatio/img/LoginScheme.jpg" width="700">  

### Pelitulosten hakeminen ja tallennus
<img src="https://github.com/Vilppula/ot-harjoitustyo/blob/master/Laatikkopeli/dokumentaatio/img/ScoreDao.png" width="700"> 
Pelitulosten pysyväistallennukseen tarkoitettu systeemi on olemassa, mutta sitä ei ole vielä otettu käyttöön.

### Pelialueiden hakeminen
<img src="https://github.com/Vilppula/ot-harjoitustyo/blob/master/Laatikkopeli/dokumentaatio/img/GameAreaDao.png" width="600">
