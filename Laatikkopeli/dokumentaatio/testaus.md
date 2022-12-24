## Testausdokumentti
### Yksikkötestaus
Yksikkötestaus on toteutettu JUnitin versio 4.12:lla. Testauskeskittyy pelilogiikasta vastaavien luokkien testaukseen. Kurssiohjeen mukaisesti
käyttöliittymätestaus on sivuutettu. Tämä koskee myös joitain pelilogiikan keskeisiä luokkia, joihin vahvasti linkitetyt javaFX-objecktit vaativat testaukseen
hieman kurssista poikkeavia testaustyökaluja. Näihin saatetaan palata ohjelman jatkokehityksen puitteissa. Kurssin loppupalautuksen mukainen testauskattavuus
on kuvan mukainen. Katso ohje README:stä uuden Jacoco-raportin luomiseen.  

<img src="https://github.com/Vilppula/ot-harjoitustyo/blob/master/Laatikkopeli/dokumentaatio/img/Jacoco.png" widht=400>  

### Integraatiotestaus
Useampien luokkien yhteistoimintaa on testattu erityisesti tietokannan hallinnan osalta. Tähän kuuluvat [DAO-luokkien testit](https://github.com/Vilppula/ot-harjoitustyo/tree/master/Laatikkopeli/src/test/java/laatikkopeli/dao)
, [DBHandlerTest](https://github.com/Vilppula/ot-harjoitustyo/blob/master/Laatikkopeli/src/test/java/laatikkopeli/db/DBhandlerTest.java) 
ja [QueryBuilderTest](https://github.com/Vilppula/ot-harjoitustyo/blob/master/Laatikkopeli/src/test/java/laatikkopeli/db/QueryBuilderTest.java). Tässä erityisesti
DAO-luokkien testaus myös testaa tietokannan hallintaan käytetyn DBHandler-luokan ja sen apuluokan 'QueryBuilder' toimintaa.
### Järjestelmätestaus
Järjestelmätestaus on suoritettu manuaalisesti.
