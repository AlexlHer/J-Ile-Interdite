import static org.junit.Assert.*;
import org.junit.Test;

public class GrilleTest {
	@Test
	public void testContains(){
		Grille g = new Grille(5, 5);
		int[] liste = new int[]{1, 2, 3, 4};

		// On verifie les points de liste.
		assertEquals(true, g.contains(1, 2, liste));
		assertEquals(true, g.contains(3, 4, liste));
		assertEquals(false, g.contains(2, 3, liste));
	}
	@Test
	public void testCaseInonde(){
		Grille g = new Grille(5, 5);

		// On verifie qu'il y a bien que des cases de niveau 2.
		assertEquals(true, g.caseInonde(2));
		assertEquals(false, g.caseInonde(1));
		assertEquals(false, g.caseInonde(0));

		// On baisse le niveau d'une case.
		g.getCase(0, 0).downLevel();

		// On verifie qu'il y a bien une case inondé.
		assertEquals(true, g.caseInonde(2));
		assertEquals(true, g.caseInonde(1));
		assertEquals(false, g.caseInonde(0));

		// On baisse le niveau de la même case.
		g.getCase(0, 0).downLevel();

		// On verifie qu'il n'y a que des cases de niveau 0 et 2.
		assertEquals(true, g.caseInonde(2));
		assertEquals(false, g.caseInonde(1));
		assertEquals(true, g.caseInonde(0));

		// On baisse le niveau d'une autre case.
		g.getCase(0, 1).downLevel();

		// On verifie qu'il y a tous les niveaux.
		assertEquals(true, g.caseInonde(2));
		assertEquals(true, g.caseInonde(1));
		assertEquals(true, g.caseInonde(0));

	}
}
