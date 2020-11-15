
package puissancequatre.grille;
public class Grille {
	
	private int longGrille = 6;
	private int largGrille = 7;
	char[][] laGrille = new char[longGrille][largGrille] ;;
	
	// constructeur de la grille
	Grille(){
		for (int i=0; i<longGrille; i++){
			for (int j=0; j<largGrille; j++) {
				this.laGrille[i][j] = '.' ;
			}
		}
	}
	
	// pour avoir la grille
	public char[][] getGrille(){
		return laGrille;
	}
	
	
	// Savoir si un coup est valable
	public boolean coupValable(int[][] grille, int coup) {
		int longueur = grille.length;
		for (int i=0; i<longueur; i++){
			if (grille[i][coup-1] != '.') {
				return true;
			}
		}
		return false;
	}
	
	// Actualiser la grille apres un coup valide
	public int[][] actualiserGrille(int[][] grille, char symboleJoueur, int coup) {
		int longueur = grille.length;
		for (int i=0; i<longueur; i++){
			if (grille[i][coup-1] != '.') {
				grille[i-1][coup-1] = symboleJoueur;
				break;
			}

		}
		return grille;
	}
	
	// Afficher la grille
	public void afficherGrille(int[][] grille) {
		int longueur = grille.length;
		int largeur = grille[0].length;
		
		// affichage de la premiere ligne avec les numeros
		for (int j=0; j<largeur; j++) {
			System.out.print(j+1 + " ");
		}
		System.out.println();
		
		// affichage des . et des X et O
		for (int i=0; i<longueur; i++){
			for (int j=0; j<largeur; j++) {
				System.out.print(grille[i][j] + " ");
			}
			System.out.println();
		}
	}

}
