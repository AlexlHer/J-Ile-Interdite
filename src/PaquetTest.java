import static org.junit.Assert.*;
import org.junit.Test;
import java.util.ArrayList;

public class PaquetTest {
	@Test
	public void testMelangePaquet(){
		// On crée un paquet.
		Paquet p = new Paquet(10);

		// On save la liste.
		ArrayList<Integer> paquet = new ArrayList<>();
		paquet.addAll(p.paquet);

		// On melange le paquet.
		p.melangePaquet();

		// On verifie que le paquet est bien mélangé.
		assertEquals(false, paquet.equals(p.paquet));
	}

	@Test
	public void testPremiereCarte(){
		// On crée un paquet.
		Paquet p = new Paquet(10);

		// On pioche la première carte.
		int carte = p.premiereCarte();

		// On verifie qu'elle est bien dans le paquet defausse.
		assertEquals(true, p.paquetDefausse.get(0) == carte);
	}
}
