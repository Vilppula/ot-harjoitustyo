# Vaatimusmäärittely
## Sovelluksen tarkoitus
Sovellus on ajanvietto-/pulmapeli, jonka tarkoitus on Tira2 kurssilla tehdyn laatikonsiirtelyalgoritmin innoittamana testata pelaajan
kykyä löytää lyhyimpiä reittejä joita kulkemalla pelialueella oleva laatikko saadaan työnnettyä maaliin.
## Käyttäjät
Luomalla pelaajaprofiili pelaajien on mahdollista tallettaa omia pelisuorituksiaan tai kilpailla toisiaan vastaan. 
Käyttäjällä on jokin käyttäjänimi ja sitä vastaava salasana. Tämä voidaan toteuttaa hyvin kevyenä ratkaisuna, sillä käyttätileihin ei liity mitään
arkaluontoista tietoa.
### Kirjautuminen uutena käyttäjänä
- Käyttäjän tulee keksiä jokin pelaajanimi sekä sitä vastaava salasana.
- Käyttäjällä voi olla profiilikuva
- Uusi käyttäjä lisätään paikalliseen tietokantaan.
### Kirjautumisen jälkeen
- Pääsy pelitiloihin
- Käyttäjäkohtaiset pelitulokset tallennetaan tietokantaan. Paras pelialuekohtainen tulos jää voimaan.
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
- Yhden pelaajan pelinäkymä
- Kahden pelaajan pelinäkymä
### Parhaiden pelituloksien näkymä
- Listamuotoinen näkymä, jossa esitellään pelialuekohtaisesti parhaan tuloksen saanut pelaaja.
  - Eri pelialueita voi selata.
### Pelinäkymä
- Päivittyvä näkymä pelialueesta
  - pelaaja/pelaajat
  - laatikko/laatikot
  - kiinteät esteet ja muuttuvat tekijät.
  - laskurit
## Mahdollisuudet jatkokehitykselle
#### Lisää pelitiloja
- Aikarajoitettu pelitila
- Useamman laatikon peli
#### Useamman pelaajan peli
- Useamman kuin kahden pelaajan välinen mittelö samalla pelialueella.
- Ryhmien välinen kilpailu, jossa yhden ryhmän jäsenet siirtävät samaa laatikkoa.
#### Editori
Melko helpolla lisäpanostuksella voidaan laatia pelialueiden suunnitteluun tarkoitettu editori
#### Monimutkaisempaa dynamiikkaa pelialueille
Pelialueet saattavat jatkossa sisältää 
- toiminnaltaan monimutkaisempia esteitä (esim. liikuvat esteet)
- pelaamista helpottavia tekijöitä (esim. liukuhihnoja)
#### Verkkopeli
Kahden tai useamman pelaajan peliä voi pelata myös verkon yli
#### Tilastointia
Lisätään tietokantaan tarkempaa pelaajakohtaista dataa.
- Keskimääräinen läpäisyaika
- Kahden pelaajan välinen keskinäinen paremmuus
