# Laatikkopeli
Ajanvietto- ja älypeli, jossa käyttäjä pyrkii löytämään lyhyimmän reitin jolla laatikko saadaan työnnettyä maaliin. Peliin voi kirjautua useampi käyttäjä.
## Toteutus
Ohjelma toteutettu Java 11:llä. Käyttöliittymä on toteutettu JavaFX 13:lla.
## Dokumentaatio
[Vaatimusmäärittely](https://github.com/Vilppula/ot-harjoitustyo/blob/master/Laatikkopeli/dokumentaatio/Vaatimusmaarittely.md)  
[Arkkitehtuurikuvaus](https://github.com/Vilppula/ot-harjoitustyo/blob/master/Laatikkopeli/dokumentaatio/Arkkitehtuuri.md)  
[Tuntikirjanpito](https://github.com/Vilppula/ot-harjoitustyo/blob/master/Laatikkopeli/dokumentaatio/Tyoaikakirjanpito.md)  
[changelog.md](https://github.com/Vilppula/ot-harjoitustyo/blob/master/Laatikkopeli/dokumentaatio/changelog.md)  
[käyttöohje](https://github.com/Vilppula/ot-harjoitustyo/blob/master/Laatikkopeli/dokumentaatio/kayttoohje.md)  
[Testausdokumentti](https://github.com/Vilppula/ot-harjoitustyo/blob/master/Laatikkopeli/dokumentaatio/testaus.md)  
## Ohjelman suorittaminen
Ohjelman voi suorittaa komennolla
```bash
mvn compile exec:java -Dexec.mainClass=laatikkopeli.App
```
## Testit ja koodin laadun tarkistaminen
Testit suoritetaan komennolla
```bash
mvn test
```
Testikattavuusraportti generoidaan tiedostoon "/target/site/jacoco/index.html". Tämä onnistuu komennolla: 
```bash
mvn test jacoco:report
```
Checkstyle-raportti (koodin laatu) generoidaan tiedostoon "/target/site/checkstyle.html". Tämä onnistuu komennolla: 
```bash
mvn jxr:jxr checkstyle:checkstyle
```
## Jar-tiedoston luominen
Ohjelma voidaan paketoida suoritettavaan jar-muotoon komennolla
```bash
mvn package
```
Jar-tiedosto suoritetaan komennolla
```bash
java -jar target/Laatikkopeli-1.0-SNAPSHOT.jar
```
Jos tämä ei toimi, jar-tiedoston pitäisi löytyä target-kansiosta (tiedostonimi ei sisällä 'original'-alkuosaa).
## Julkaisut
[Loppupalautus](https://github.com/Vilppula/ot-harjoitustyo/releases/tag/Loppupalautus)  
[viikko 5](https://github.com/Vilppula/ot-harjoitustyo/releases/tag/viikko5)  
[viikko 6](https://github.com/Vilppula/ot-harjoitustyo/releases/tag/viikko6)
