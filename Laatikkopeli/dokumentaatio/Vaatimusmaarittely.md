# Vaatimusmäärittely
## Sovelluksen tarkoitus
Sovellus on peli, jonka tarkoitus on Tira2 kurssilla tehdyn laatikonsiirtelyalgoritmin tapaan testata pelaajan
kykyä löytää lyhyimpiä reittejä joita kulkemalla laatikko saadaan työnnettyä maaliin.
## Käyttäjät
Luomalla käyttäjänimi on pelaajien mahdollista kilpailla toisiaan vastaan. Käyttäjällä on jokin käyttäjänimi ja
sitä vastaava salasana. Tämä voidaan toteuttaa hyvin kevyenä ratkaisuna, sillä käyttätileihin ei liity mitään
arkaluontoista tietoa.
### Kirjautuminen uutena käyttäjänä
- Käyttäjän tulee keksiä jokin pelaajanimi sekä sitä vastaava salasana.
- Uusi käyttäjä lisätään paikalliseen tietokantaan.
## Pelin pelaaminen
### Pelimoodit
Peliin on mahdollista kehittää lukuisia, tavoitteiltaan ja haastavuustasoltaan eroavia pelimoodeja. Näistä
kehityksen alkuvaiheessa tärkeimpinä pidetään: 
- Pelitila jossa etsitään lyhyintä reittiä yhden pelaajan pelissä. 
- Pelitila kahdelle pelaajalle jossa minimoidaan omaa reittiä ja maksimoidaan kilpailijan reittiä.
#### Yksinpeli
Yksinpelin ideana on testata pelaajan kykyä selvittää kuinka laatikko saadaan työnnettyä maaliin mahdollisimman
vähillä askelilla. Reitin valintaan vaikuttavat kiinteät esteet sekä erilaiset etenemistä hidastavat tekijät.
Sekä askelmäärä että kulunut aika otetaan talteen ja liitetään pelaajan käyttäjänimeen.
#### Kaksinpeli
Kahden pelaajan versiossa kaksi pelaajaa liikkuu samalla pelialueella vuorotellen siirtoja tehden. Jokaisella
siirrolla pelaajan harkinnan varaan jää edistääkö hän oman tavoitteensa saavuttamista vai vaikeuttaako hän 
kilpailijan etenemistä. Muuttuvina tekijöinä pelialueella ovat erilaiset siirrettävät esteet.
## Käyttöliittymän määrittely
### Alkunäkymä
- Alkunäkymä sisältää jonkin peliä esittelevän kuvan.
- Sisäänkirjautumislomake
- Linkki uuden pelaajan luomiseen
- Kirjautuneen pelaajan tiedot (kahden pelaajan tapauksessa näitä on kaksi)
- Linkit pelitiloihin (kun kirjautuminen on suoritettu)
- Linkki parhaita pelituloksia esittelevään näkymään.
### Kirjautumisnäkymä
- Nimikenttä ja salasanakenttä
- Pelaajan profiilikuvan lisäys
### Pelitilat
- Linkki yhden pelaajan pelitilaan
- Linkki kahden pelaajan pelitilaan
### Parhaiden pelituloksien näkymä
- Listamuotoinen näkymä, jossa esitellään pelialue kohtaisesti parhaan tuloksen saanut pelaaja.
  - Pelialueita voi selata.
### Pelinäkymä
- Päivittyvä näkymä pelialueesta. Sisältää pelaajan, laatikon, kiinteät esteet ja muuttuvat tekijät.
