package it.reactive.esercizioTesting.businesslogic;

import java.util.*;

import it.reactive.esercizioTesting.exception.*;
import org.junit.Before;
import org.junit.Test;

import it.reactive.esercizioTesting.entrypoint.Asta;

import static org.junit.Assert.*;

public class ServiceAstaTest {

    private final String oggettoBandito = "Vinile";
    static ServiceAsta serviceAsta;
    static Asta asta;
    private final String VALORE_MAPPA = "valore";
    private final String VINCITORE_MAPPA = "vincitore";
    private final String IS_FINITA_MAPPA = "fine asta";




    private List<String> partecipanti = setPartecipanti();

    public List<String> setPartecipanti() {
        List<String> partecipanti = new ArrayList<>();
        partecipanti.add("Pippo");
        partecipanti.add("Gabriele");
        partecipanti.add("Daniele");
        return partecipanti;
    }


    @Before
    public void before() {
        serviceAsta = new ServiceAsta();
        asta = new Asta(serviceAsta);
    }


    @Test
    public void testAvviaAsta() {
        asta.avvia(oggettoBandito, partecipanti);
        assertEquals(asta.visualizzaPartecipantiSessioneCorrente(), partecipanti);
    }

    @Test
    public void testGetPartecipanti() {
        asta.avvia(oggettoBandito, partecipanti);
        assertEquals(serviceAsta.getPartecipanti(), partecipanti);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testMetodoSetPArtecipanti() {
        List<String> partecipanti = new ArrayList<>();
        partecipanti.add("Default");
        serviceAsta.setPartecipanti(partecipanti);
    }

    @Test(expected = PartecipanteEsistenteException.class)
    public void testMetodoPartecipanteEsistente() {
        asta.avvia(oggettoBandito, partecipanti);
        asta.fineAstaForzata();
        serviceAsta.addPartecipante("Gabriele");
    }

    @Test
    public void testAddPartecipanteRimuoveDefault() {
        assertTrue(serviceAsta.getPartecipanti().contains("Default"));
        asta.addPartecipante("Gabriele");
        assertEquals(1, serviceAsta.getPartecipanti().size());
        assertTrue(serviceAsta.getPartecipanti().contains("Gabriele"));
        assertFalse(serviceAsta.getPartecipanti().contains("Default"));
    }

    @Test(expected = AstaInCorsoException.class)
    public void testAggiungiPartecipanteAstaInCorso() {
        asta.avvia(oggettoBandito, partecipanti);
        asta.addPartecipante("Daniele");
    }

    @Test
    public void testVerificaFineAsta() {
        asta.avvia(oggettoBandito, partecipanti);
        assertFalse("Asta ancora in corso parteciapanti: " + serviceAsta.getPartecipanti(), asta.verificaFineAsta());
    }

    @Test(expected = AstaTerminataException.class)
    public void testAstaInCorso() {
        asta.avvia(oggettoBandito, partecipanti);
        asta.passa(partecipanti.get(0));
        asta.passa(partecipanti.get(1));
        asta.passa(partecipanti.get(2));
        serviceAsta.rilancia(partecipanti.get(0), 2);
    }

    @Test
    public void testRilancia() {
        asta.avvia(oggettoBandito, partecipanti);
        asta.rilancia("Pippo", 1);
        assertEquals(2, asta.getValoreCorrente());
    }

    @Test(expected = PartecipanteNonCensitoException.class)
    public void testRilanciaPartecipanteNonCensito() {
        asta.avvia(oggettoBandito, partecipanti);
        asta.rilancia("Mario", 10);
    }

    @Test(expected = ValoreNonAmmessoException.class)
    public void testRilanciaValoreZero() {
        asta.avvia(oggettoBandito, partecipanti);
        asta.rilancia("Pippo", 0);
    }

    @Test
    public void testPassaggioTurno() {
        asta.avvia(oggettoBandito, partecipanti);
        asta.rilancia("Pippo", 5);
        assertEquals("Gabriele", serviceAsta.getPartecipanti().get(1));
    }


    @Test
    public void testPassaggioTurnoConPartecipantePassato() {
        asta.avvia(oggettoBandito, partecipanti);
        asta.rilancia("Pippo", 5);
        asta.passa("Gabriele");
        assertEquals("Daniele", serviceAsta.getPartecipanti().get(2));
    }

    @Test(expected = TurnoNonValidoException.class)
    public void testPassaggioTurnoConPartecipanteNonDiQuelTurno() {
        asta.avvia(oggettoBandito, partecipanti);
        asta.rilancia("Pippo", 5);
        asta.passa("Daniele");
    }

    @Test
    public void testVerificaTurnoCorretti() {
        asta.avvia(oggettoBandito, partecipanti);
        asta.rilancia("Pippo", 1);
        asta.rilancia("Gabriele", 2);
        assertEquals("Daniele", serviceAsta.getPartecipanti().get(2));
    }

    @Test
    public void testFineAsta() {
        asta.avvia(oggettoBandito, partecipanti);
        serviceAsta.fine();
        assertEquals(asta.getSessioneAsta().get(IS_FINITA_MAPPA), false);
    }


    @Test
    public void testFinale() {
        asta.addPartecipante(partecipanti.get(0));
        asta.addPartecipante(partecipanti.get(1));
        asta.addPartecipante(partecipanti.get(2));
        asta.avvia(oggettoBandito);
        assertEquals(asta.getSessioneAsta().get(IS_FINITA_MAPPA), false);
        assertEquals(asta.visualizzaPartecipantiSessioneCorrente(), partecipanti);
        asta.rilancia(partecipanti.get(0), 1);
        asta.rilancia(partecipanti.get(1), 2);
        asta.rilancia(partecipanti.get(2), 3);
        asta.rilancia(partecipanti.get(0), 2);
        assertEquals(asta.getSessioneAsta().get(IS_FINITA_MAPPA), false);
        assertEquals(asta.getSessioneAsta().get(VALORE_MAPPA), 9);
        asta.passa(partecipanti.get(1));
        asta.rilancia(partecipanti.get(2), 1);
        asta.rilancia(partecipanti.get(0), 2);
        asta.passa(partecipanti.get(2));
        assertEquals(asta.getSessioneAsta().get(VALORE_MAPPA), 12);
        assertEquals(asta.getSessioneAsta().get(VINCITORE_MAPPA), partecipanti.get(0));
        assertEquals(asta.getSessioneAsta().get(IS_FINITA_MAPPA), true);
    }


    /*
     * Questa non � una classe di test perch� non contiene Assert!!!!!
     * E' stata lasciata per documentare come pu� avvenire un flow di chiamate
     */
//	@Test
    public void test2() {
        Asta asta = new Asta(new ServiceAsta());
        asta.avvia("Auto");
        Map<String, Object> sessioneAsta = asta.getSessioneAsta();
        System.out.println(sessioneAsta);
        sessioneAsta = asta.getSessioneAsta();
        System.out.println(sessioneAsta);
        System.out.println(asta.visualizzaPartecipantiSessioneCorrente());
//		asta.addPartecipante("Daniele");
        asta.fineAstaForzata();
        asta.addPartecipante("Daniele");
        asta.addPartecipante("Mario");
        asta.avvia("Moto");
        System.out.println(asta.getValoreCorrente());
//		asta.rilancia("Mario", 3);
        asta.rilancia("Daniele", 3);
        asta.rilancia("Mario", 1);
        asta.rilancia("Daniele", 2);
        sessioneAsta = asta.getSessioneAsta();
        System.out.println(sessioneAsta);
        System.out.println(asta.visualizzaPartecipantiSessioneCorrente());
        System.out.println(asta.verificaFineAsta());
        System.out.println(asta.passa("Mario"));
        List<String> lista = new ArrayList<String>();
        lista.add("Mario");
        lista.add("Daniele");
        lista.add("Anna");
        asta.avvia("Bici", lista);
        asta.rilancia("Mario", 1);
        asta.passa("Daniele");
        asta.rilancia("Anna", 1);
        asta.rilancia("Mario", 1);
        asta.rilancia("Anna", 1);
        System.out.println(asta.passa("Mario"));
        sessioneAsta = asta.getSessioneAsta();
        System.out.println(sessioneAsta);
    }


}


