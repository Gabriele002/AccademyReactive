package it.reactive.esercizioTesting.businesslogic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import it.reactive.esercizioTesting.exception.AstaInCorsoException;
import it.reactive.esercizioTesting.oggetto.SessioneAsta;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import it.reactive.esercizioTesting.entrypoint.Asta;

import static org.junit.Assert.*;

public class ServiceAstaTest {

	private final String ogettoBandito = "Vinile";
	static ServiceAsta serviceAsta;
	static Asta asta;


	@Before
	public void before(){
		serviceAsta = new ServiceAsta();
		asta = new Asta(serviceAsta);
	}


	@Test
	public void testAvviaAsta() {
		List<String> partecipanti = Arrays.asList("Daniele", "Gabriele", "Mario");
		asta.avvia(ogettoBandito, partecipanti);
		assertEquals(asta.visualizzaPartecipantiSessioneCorrente(), partecipanti);
	}


	@Test(expected = UnsupportedOperationException.class)
	public void testMetodoSetPArtecipanti() {
		List<String> partecipanti = new ArrayList<>();
		partecipanti.add("Default");
		serviceAsta.setPartecipanti(partecipanti);
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
		asta.avvia(ogettoBandito, Arrays.asList("Pippo","Gabriele"));
		asta.addPartecipante("Gabriele");
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


