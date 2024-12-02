package it.reactive.esercizioTesting.entrypoint;

import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.when;

import it.reactive.esercizioTesting.utility.Utility;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import it.reactive.esercizioTesting.businesslogic.ServiceAsta;

@RunWith(MockitoJUnitRunner.class)
public class AstaTest extends Utility {

	@InjectMocks Asta asta;
	@Mock ServiceAsta serviceAsta;

	@Test
	public void astaIniziata() {
		when(serviceAsta.verificaFineAsta()).thenReturn(false);
		asta.avvia(OGGETTO_PROPOSTO, partecipanti);
		assertFalse(asta.verificaFineAsta());
	}
	
}
