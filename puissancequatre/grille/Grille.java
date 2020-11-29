package puissancequatre.grille;

public class Grille {
	
	private final int longGrille = 6;
	private final int largGrille = 7;
	private char[][] laGrille = new char[longGrille][largGrille];
	
	// constructeur de la grille
	public Grille(){
		
		for (int i=0; i<longGrille; i++){
			for (int j=0; j<largGrille; j++) {
				this.laGrille[i][j] = '.' ;
			}
		}
	}
	
	// getter de la Grille
	public char[][] getGrille(){
		return this.laGrille;
	}
	
	// Savoir si un coup est valable
	public static boolean coupValable(char[][] grille, int coup) {
		int longueur = grille.length;
		int largeur = grille[0].length;
		
		if( (coup >= 1) && (coup <= largeur) ) {
			for (int i=0; i<longueur; i++){
				if (grille[i][coup-1] == '.') {
					return true;
				}
			}
		}
		return false;
	}
	
	// Actualiser la grille apres un coup valide
	public static char[][] actualiserGrille(char[][] grille, char symboleJoueur, int coup) {
		int longueur = grille.length;
		int compt = 0;
		for (int i=0; i<longueur; i++){
			if (grille[i][coup-1] == '.') {
				compt++;
			}
		}
		grille[compt-1][coup-1] = symboleJoueur;
		//System.out.println("grille[compt-1][coup-1] = " + grille[compt-1][coup-1]);
		return grille;
	}
	
	// Afficher la grille
	public static void afficherGrille(char[][] grille) {
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
	
	public static boolean AGagne(char[][] grille, char symboleJoueur, int pionsPourVictoire) {
		int longueur = grille.length;
		int largeur = grille[0].length;
		
		int somme = 0;
		int x = 0; 
		int y = 0; 

		for (int i=0; i<longueur; i++){
			for (int j=0; j<largeur; j++) {
				
				// horizontale:
				x = i; 
				y = j; 
				somme=0;
				
				while(y >= 0 && grille[x][y] == symboleJoueur){ 
					y--; 
					somme++;
				}
				y = j;
				while(y < largeur && grille[x][y] == symboleJoueur){ 
					y++; 
					somme++;
				}
				
				if(somme > pionsPourVictoire) {
					return true;
				}
				
				// veticale:
				x = i;
				y = j;
				somme=0;
				while(x >= 0 && grille[x][y] == symboleJoueur){ 
					x--; 
					somme++;
				}
				x = i;
				while(x < longueur && grille[x][y] == symboleJoueur){ 
					x++; 
					somme++;
				}
				if(somme > pionsPourVictoire) {
					return true;
				}
				
				// diagonale
				x = longueur-1; 
				y = largeur-1; 
				somme = 0;
				
				while(y >= 0 && x >= 0 && grille[x][y] == symboleJoueur){ 
					y--;
					x--;
					somme++;
				}
				x = i; 
				y = j;
				
				while(y < largeur && x < longueur && grille[x][y] == symboleJoueur){ 
					y++; 
					x++; 
					somme++;
				}
				
				if(somme > pionsPourVictoire) {
					return true;
				}
				
				// anti-diagonale
				x = i; 
				y = j; 
				somme=0;
				
				while(y >= 0 && x < longueur && grille[x][y] == symboleJoueur){ 
					y--;
					x++;
					somme++;
				}
				x = i;
				y = j;
				while(y < largeur && x >= 0 && grille[x][y] == symboleJoueur){
					y++; 
					x--; 
					somme++;
				}
				
				if(somme > pionsPourVictoire) {
					return true;
				}

			}
		}
		return false;

	}

}
