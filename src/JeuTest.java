import static org.junit.Assert.*;
import org.junit.Test;

import java.util.ArrayList;

public class JeuTest {
	@Test
	public void testDeplacement(){
		Joueur[] liste = new Joueur[1];
		liste[0] = new Joueur("a", 0);
		Jeu jeu = new Jeu(5, 5, liste);

		// On met le joueur au milieu.
		jeu.joueurs[0].setCase(jeu.grille.getCase(3, 3));

		// On déplace le joueurs sur plusieurs positions et on verifie si le deplacement est bon.
		assertEquals(0, jeu.deplacement("H", jeu.joueurs[0]));
		assertEquals(2, jeu.joueurs[0].getPosy());

		assertEquals(0, jeu.deplacement("B", jeu.joueurs[0]));
		assertEquals(3, jeu.joueurs[0].getPosy());

		assertEquals(0, jeu.deplacement("D", jeu.joueurs[0]));
		assertEquals(4, jeu.joueurs[0].getPosx());

		assertEquals(0, jeu.deplacement("G", jeu.joueurs[0]));
		assertEquals(3, jeu.joueurs[0].getPosx());
	}

	@Test
	public void testAssechement() {
		Joueur[] liste = new Joueur[1];
		liste[0] = new Joueur("a", 0);
		Jeu jeu = new Jeu(5, 5, liste);

		// On dessend d'un niveau.
		jeu.joueurs[0].getCase().downLevel();

		// On remonte.
		assertEquals(0, jeu.assechement("C", jeu.joueurs[0]));

		// On regarde si c'est bien remonté.
		assertEquals(2, jeu.joueurs[0].getCase().getLevel());

		// On verifie si le niveau ne peut plus etre monté.
		assertEquals(1, jeu.assechement("C", jeu.joueurs[0]));

		// On dessend de deux niveaux.
		jeu.joueurs[0].getCase().downLevel();
		jeu.joueurs[0].getCase().downLevel();

		// On verifie si le niveau ne peut plus etre monté.
		assertEquals(1, jeu.assechement("C", jeu.joueurs[0]));
	}

	@Test
	public void testRecupArtefact() {
		Joueur[] liste = new Joueur[1];
		liste[0] = new Joueur("a", 0);
		Jeu jeu = new Jeu(5, 5, liste);

		// On met le joueur sur un artefact.
		jeu.joueurs[0].setCase(jeu.grille.getCaseSpe(1));

		// On lui donne la clef 0.
		jeu.joueurs[0].addClef(0);

		// On recup l'artefact.
		assertEquals(0, jeu.recupArtefact(jeu.joueurs[0]));

		// On verifie qu'on ne peut pas le reprendre une deuxième fois.
		assertEquals(1, jeu.recupArtefact(jeu.joueurs[0]));

		// On verifie qu'on l'a récuperé.
		assertEquals(true, jeu.joueurs[0].verifArtefact(0));
	}

	@Test
	public void testSplitString() {
		Joueur[] liste = new Joueur[1];
		liste[0] = new Joueur("a", 0);
		Jeu jeu = new Jeu(5, 5, liste);

		// On split le string avec la méthode.
		ArrayList<Integer> l = jeu.splitString("1,2,3,4");

		// On crée une liste equivalente.
		ArrayList<Integer> l1 = new ArrayList<>() {{
			add(1);
			add(2);
			add(3);
			add(4);
		}};

		// On verifie que ce sont les mêmes.
		assertEquals(true, l1.equals(l));
	}

	@Test
	public void testDonClef() {
		Joueur[] liste = new Joueur[2];
		liste[0] = new Joueur("a", 0);
		liste[1] = new Joueur("b", 0);
		Jeu jeu = new Jeu(5, 5, liste);

		// On donne une clef à a.
		jeu.joueurs[0].addClef(0);

		// On donne la clef à b.
		assertEquals(0, jeu.donClef("A", "1", jeu.joueurs[0]));

		// On verifie que le transfère à été effectué.
		assertEquals(true, jeu.joueurs[1].verifClef(0));
	}
}
