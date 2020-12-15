package puissancequatre.joueur;

import java.util.Random;

import puissancequatre.grille.Grille;

public class IA extends Joueur {

	public IA(String nom, String type) {
		super(nom, "ia");
	}
	
	public static boolean valideTypeJoueur(String typeJoueur, int numeroJoueur) {
		
		if ( (typeJoueur).equals("ia") ) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public int coupJoueur(Grille grille, int largeur) {
		
		int coup = 0;
		Random rand = new Random();
		
		while(grille.coupValable(coup) != 1) {
			coup = rand.nextInt(largeur - 1) + 1;
		}
		//System.out.println("\n" + nom + " joue : " + coup + "\n");

		return coup;
	}


}
