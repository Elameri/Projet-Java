package puissancequatre.grille;

public class Grille {
	
	private final int longGrille = 6;
	private final int largGrille = 7;
	private char[][] grille = new char[longGrille][largGrille];
	
	// constructeur de la grille
	public Grille(){
		for (int i=0; i<longGrille; i++){
			for (int j=0; j<largGrille; j++) {
				this.grille[i][j] = '.' ;
			}
		}
	}
	
	// Savoir si un coup est valable
	public int coupValable(int coup) {
		
		if( (coup >= 1) && (coup <= largGrille) ) {
			for (int i=0; i<longGrille; i++){
				if (grille[i][coup-1] == '.') {
					return 1;
				}
			}
			return 0;
		}
		else {
			return -1;
		}
	}
	
	// Actualiser la grille apres un coup valide
	public void initialiserGrille() {
		
		for (int i=0; i<longGrille; i++){
			for (int j=0; j<largGrille; j++) {
				grille[i][j] = '.' ;
			}
		}
	}	
	
	// Actualiser la grille apres un coup valide
	public void actualiserGrille(char symboleJoueur, int coup) {
		int compt = 0;
		for (int i=0; i<longGrille; i++){
			if (grille[i][coup-1] == '.') {
				compt++;
			}
		}
		grille[compt-1][coup-1] = symboleJoueur;
		//System.out.println("grille[compt-1][coup-1] = " + grille[compt-1][coup-1]);
	}
	
	// Afficher la grille
	public void afficherGrille() {
		
		// affichage de la premiere ligne avec les numeros
		for (int j=0; j<largGrille; j++) {
			System.out.print(j+1 + " ");
		}
		System.out.println();
		
		// affichage des . et des X et O
		for (int i=0; i<longGrille; i++){
			for (int j=0; j<largGrille; j++) {
				System.out.print(grille[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	// Savoir si la grille est pleine
	public boolean estCeQueGrillePleine() {
		
		for (int i=0; i<longGrille; i++){
			for (int j=0; j<largGrille; j++) {
				if (grille[i][j] == '.') {
					return false;
				}
			}
		}
		return true;
	}
	
	// Savoir s'il y a un gagnant
	public boolean AGagne(char symboleJoueur, int pionsPourVictoire) {
		
		int somme = 0;
		int x = 0; 
		int y = 0; 

		for (int i=0; i<longGrille; i++){
			for (int j=0; j<largGrille; j++) {
				
				// horizontale:
				x = i; 
				y = j; 
				somme=0;
				
				while(y >= 0 && grille[x][y] == symboleJoueur){ 
					y--; 
					somme++;
				}
				y = j;
				while(y < largGrille && grille[x][y] == symboleJoueur){ 
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
				while(x < longGrille && grille[x][y] == symboleJoueur){ 
					x++; 
					somme++;
				}
				if(somme > pionsPourVictoire) {
					return true;
				}
				
				// diagonale
				x = longGrille-1; 
				y = largGrille-1; 
				somme = 0;
				
				while(y >= 0 && x >= 0 && grille[x][y] == symboleJoueur){ 
					y--;
					x--;
					somme++;
				}
				x = i; 
				y = j;
				
				while(y < largGrille && x < longGrille && grille[x][y] == symboleJoueur){ 
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
				
				while(y >= 0 && x < longGrille && grille[x][y] == symboleJoueur){ 
					y--;
					x++;
					somme++;
				}
				x = i;
				y = j;
				while(y < largGrille && x >= 0 && grille[x][y] == symboleJoueur){
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
