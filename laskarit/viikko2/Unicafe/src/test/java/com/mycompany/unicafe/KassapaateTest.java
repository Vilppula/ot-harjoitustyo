package com.mycompany.unicafe;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class KassapaateTest {

    Kassapaate kassa;
    Maksukortti kortti;
    
    @Before
    public void setUp() {
        this.kassa = new Kassapaate();
        this.kortti = new Maksukortti(1000);
    }
    
    @Test
    public void kassanMuuttujatAlussaOikein(){
        assertEquals(100000, this.kassa.kassassaRahaa());
        assertEquals(0, this.kassa.maukkaitaLounaitaMyyty());
        assertEquals(0, this.kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void kortinSaldonLataaminenKasvattaaKassanSaldoa() {
        assertEquals(100000, this.kassa.kassassaRahaa());
        kassa.lataaRahaaKortille(kortti, 1000);
        assertEquals(101000, this.kassa.kassassaRahaa());
        assertEquals(2000, this.kortti.saldo());
    }
    
    @Test
    public void kortilleEiVoiLadataNegatiivistaSummaa() {
        kassa.lataaRahaaKortille(kortti, -1);
        assertEquals(100000, this.kassa.kassassaRahaa());
        assertEquals(1000, this.kortti.saldo());
    }
    
    //========================================================================== Käteisostotestaus
    @Test
    public void kassanSaldoOikeinKunOstetaanKateisellaMaukas(){
        assertEquals(100, kassa.syoMaukkaasti(500));
        assertEquals(100400,kassa.kassassaRahaa());
    }
    
    @Test
    public void maukkaidenMyyntiOikeinKunOstetaanKateisellaMaukas(){
        kassa.syoMaukkaasti(400);
        assertEquals(1,kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void kassanSaldoEiMuutuJaRahatPalautetaanKunSummaEiRiitaMaukkaaseen(){
        assertEquals(300, kassa.syoMaukkaasti(300));
        assertEquals(100000,kassa.kassassaRahaa());
    }
    
    @Test
    public void maukkaidenMyyntiOikeinKunSummaEiRiitaMaukaaseen(){
        kassa.syoMaukkaasti(300);
        assertEquals(0,kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void kassanSaldoOikeinKunOstetaanKateisellaEdukas(){
        assertEquals(260, kassa.syoEdullisesti(500));
        assertEquals(100240,kassa.kassassaRahaa());
    }
    
    @Test
    public void edukkaidenMyyntiOikeinKunOstetaanKateisellaEdukas(){
        kassa.syoEdullisesti(400);
        assertEquals(1,kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void kassanSaldoEiMuutuJaRahatPalautetaanKunSummaEiRiitaEdulliseen(){
        assertEquals(200, kassa.syoEdullisesti(200));
        assertEquals(100000,kassa.kassassaRahaa());
    }
    
    @Test
    public void edullistenMyyntiOikeinKunSummaEiRiitaEdulliseen(){
        kassa.syoEdullisesti(200);
        assertEquals(0,kassa.edullisiaLounaitaMyyty());
    }
    
    //========================================================================== Korttiostotestaus
    @Test
    public void kassanjaKortinSaldotOikeinKunOstetaanKortillaMaukas(){
        kassa.syoMaukkaasti(kortti);
        assertEquals(600, kortti.saldo());
        assertEquals(100000, kassa.kassassaRahaa());
    }
    @Test
    public void kassanJakortinSaldoOikeinKunOstetaanKortillaEdukas(){
        kassa.syoEdullisesti(kortti);
        assertEquals(760, kortti.saldo());
        assertEquals(100000, kassa.kassassaRahaa());
    }
    //==========================================================================
    @Test
    public void maukkaidenMyyntiOikeinKunOstetaanKortillaMaukas(){
        kassa.syoMaukkaasti(kortti);
        assertEquals(1,kassa.maukkaitaLounaitaMyyty());
    }
    @Test
    public void edukkaidenMyyntiOikeinKunOstetaanKortillaEdukas(){
        kassa.syoEdullisesti(kortti);
        assertEquals(1,kassa.edullisiaLounaitaMyyty());
    }
    //==========================================================================
    @Test
    public void korttiaEiVeloitetaKunKortinSaldoEiRiitaMaukkaaseen(){
        kassa.syoMaukkaasti(kortti); kassa.syoMaukkaasti(kortti);
        assertEquals(false, kassa.syoMaukkaasti(kortti));
        assertEquals(200, kortti.saldo());
    }
    @Test
    public void korttiaEiVeloitetaKunKortinSaldoEiRiitaEdulliseen(){
        kassa.syoEdullisesti(kortti); kassa.syoEdullisesti(kortti);
        kassa.syoEdullisesti(kortti); kassa.syoEdullisesti(kortti);
        assertEquals(false, kassa.syoEdullisesti(kortti));
        assertEquals(40,kortti.saldo());
    }
    //==========================================================================
    @Test
    public void maukkaidenMyyntiOikeinKunKortinSaldoEiRiitaMaukaaseen(){
        kassa.syoMaukkaasti(kortti); kassa.syoMaukkaasti(kortti);
        assertEquals(false,kassa.syoMaukkaasti(kortti));
        assertEquals(2,kassa.maukkaitaLounaitaMyyty());
    }
    @Test
    public void edullistenMyyntiOikeinKunKortinSaldoEiRiitaEdulliseen(){
        kassa.syoEdullisesti(kortti);kassa.syoEdullisesti(kortti);
        kassa.syoEdullisesti(kortti);kassa.syoEdullisesti(kortti);
        kassa.syoEdullisesti(kortti);
        assertEquals(4,kassa.edullisiaLounaitaMyyty());
    }
}
