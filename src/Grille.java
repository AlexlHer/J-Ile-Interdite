// ------------------------
// Projet POGL : Île Interdite
// Par Alexandre l'Heritier
// ------------------------
// Classe Grille : Représente un tableau 2D de Case et s'occupe de l'initialiser.
// ------------------------

import java.util.Random;

public class Grille {
	private int sizex, sizey;
	private Case[][] tabCase;
	private Random random = new Random();
	private Case[] caseSpe;

	/*
	 * Constructeur.
	 * @param x et y le nombre de ligne et de colonnes.
	 */
	public Grille(int x, int y){
		sizex = x;
		sizey = y;
		caseSpe = new Case[5];

		tabCase = new Case[x][y];
		for(int i = 0; i < x; i++){
			for(int j = 0; j < y; j++)
				tabCase[i][j] = new Case(false, 0, i, j);
		}
		return;
	}

	/*
	 * Méthode permettant d'initialiser la grille (affecter des spécificités aux cases).
	 * @return la case de l'héliport.
	 */
	public Case init(){
		// Liste contenant les positions des cases déjà prise ({x, y, x, y,...}).
		int[] pos = new int[10];

		int compt = 0, x, y;
		Case caseAeroport = null;

		// Tant que toutes les spécificités ne sont pas attribué.
		while(compt < 5){

			// On cherche une position aléatoire non prise.
			do{
				x = random.nextInt(sizex);
				y = random.nextInt(sizey);
			}
			while(contains(x, y, pos));

			// Au premier tour, on place l'héliport.
			if(compt == 0) {
				tabCase[x][y] = new Case(true, 0, x, y);
				caseAeroport = tabCase[x][y];
			}

			// Après on place les artefacts.
			else
				tabCase[x][y] = new Case(false, compt, x, y);

			// On ajoute la position aux listes pour ne pas reprendre les même cases après.
			pos[compt*2] = x;
			pos[compt*2+1] = y;

			caseSpe[compt] = tabCase[x][y];
			compt++;
		}
		return caseAeroport;
	}

	/*
	 * Méthode recherchant le point x, y dans pos.
	 * @param x, y : le point recherché.
	 * @param pos : le tableau dans lequel il faut rechercher le point. ({x, y, x, y, ...}).
	 * @return true si le point est trouvé, false sinon.
	 */
	protected boolean contains(int x, int y, int[] pos){
		for(int i = 1; i < pos.length; i+=2){
			if(pos[i-1] == x && pos[i] == y) return true;
		}
		return false;
	}

	/*
	 * Méthode permettant de rechercher si une case d'un certain niveau est presente dans la grille.
	 * @param level : le niveau recherché
	 * @return si une case du niveau demandé est trouvée.
	 */
	protected boolean caseInonde(int level){
		for(Case[] l : tabCase){
			for(Case c : l)
				// Si on trouve une case avec le niveau recherché, on return true.
				if(c.getLevel() == level) return true;
		}
		return false;
	}

	// Accesseurs.
	public Case getCase(int x, int y){
		if(x < 0 || x >= sizex || y < 0 || y >= sizey) return new Case(false, 0, -1, -1);
		return tabCase[x][y];
	}

	public int getTaillex() {
		return sizex;
	}

	public int getTailley(){
		return sizey;
	}

	public Case getCaseSpe(int pos){
		if(pos < 0 || pos > 4) return new Case(false, 0, -1, -1);
		return caseSpe[pos];
	}
}
