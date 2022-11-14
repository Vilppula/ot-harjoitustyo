package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(1000);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void kortillaOnAluksiOikeaSaldo() {
        assertEquals("saldo: 10.0", kortti.toString());      
    }
    
    @Test
    public void rahanLataaminenKasvattaaSaldoaOikein() {
        assertEquals("saldo: 10.0", kortti.toString());
        kortti.lataaRahaa(500);
        assertEquals("saldo: 15.0", kortti.toString());
    }
    
    @Test
    public void saldoVäheneeOikein() {
        assertEquals("saldo: 10.0", kortti.toString());
        kortti.otaRahaa(200);
        assertEquals("saldo: 8.0", kortti.toString());
    }
    
    @Test
    public void saldoEiVäheneJosRahaaEiOleTarpeeksi(){
        kortti.otaRahaa(800);
        assertEquals("saldo: 2.0", kortti.toString());
        kortti.otaRahaa(300);
        assertEquals("saldo: 2.0", kortti.toString());
    }
    
    @Test
    public void otaRahaaPalauttaTrueJosRahatRiittivät(){
        assertEquals(true, kortti.otaRahaa(800));
    }
    
    @Test
    public void otaRahaaPalauttaFalseJosRahatEivätRiittä(){
        assertEquals(false, kortti.otaRahaa(1200));        
    }
}
