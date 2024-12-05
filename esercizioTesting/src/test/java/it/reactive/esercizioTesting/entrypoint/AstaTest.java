package it.reactive.esercizioTesting.entrypoint;

import it.reactive.esercizioTesting.businesslogic.ServiceAsta;
import it.reactive.esercizioTesting.exception.PartecipanteEsistenteException;
import it.reactive.esercizioTesting.utility.Utility;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AstaTest extends Utility {

    @InjectMocks
    Asta asta;
    @Mock
    ServiceAsta serviceAsta;


    @Before
    public void before() {
        asta = new Asta(serviceAsta);
    }


    @Test
    public void astaIniziata() {
        when(serviceAsta.verificaFineAsta()).thenReturn(false);
        asta.avvia(OGGETTO_PROPOSTO, partecipanti);
        assertFalse(asta.verificaFineAsta());
    }

    @Test
    public void testGetPartecipanti() {
        when(asta.visualizzaPartecipantiSessioneCorrente()).thenReturn(partecipanti);
        asta.avvia(OGGETTO_PROPOSTO, partecipanti);
        assertEquals(serviceAsta.getPartecipanti(), partecipanti);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testMetodoSetPArtecipanti() {
        when(serviceAsta.setPartecipanti(partecipanti)).thenThrow(UnsupportedOperationException.class);
        serviceAsta.setPartecipanti(partecipanti);
    }

    @Test(expected = PartecipanteEsistenteException.class)
    public void testMetodoPartecipanteEsistente() {
        doThrow(PartecipanteEsistenteException.class).when(serviceAsta).addPartecipante("Gabriele");
        asta.avvia(OGGETTO_PROPOSTO, partecipanti);
        serviceAsta.addPartecipante("Gabriele");
    }

    @Test
    public void testRimuoveDefault() {
        List<String> partecipantiMock = new ArrayList<>();
        partecipantiMock.add("Default");
        when(serviceAsta.getPartecipanti()).thenReturn(partecipantiMock);
        System.out.println(serviceAsta.getPartecipanti().toString());
        asta.addPartecipante("Pippo");
        assertFalse(serviceAsta.getPartecipanti().contains("Default"));

//		List<String> partecipantiConPippo = new ArrayList<>();
//		partecipantiConPippo.add("Pippo");
//		when(serviceAsta.getPartecipanti()).thenReturn(partecipantiConPippo);
//		assertFalse(serviceAsta.getPartecipanti().contains("Default"));
//		System.out.println(serviceAsta.getPartecipanti().toString());
//		assertTrue(serviceAsta.getPartecipanti().contains("Pippo"));
//		assertEquals(1, serviceAsta.getPartecipanti().size());
    }


    @Test
    public void testSetPartecipanti() {
        List<String> partecipanti = Arrays.asList("Mario", "Pippo");
        when(serviceAsta.setPartecipanti(partecipanti)).thenReturn(partecipanti);
        assertEquals(asta.setPartecipanti(partecipanti), partecipanti);
    }
}
