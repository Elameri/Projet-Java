package puissancequatre.joueur;

import puissancequatre.grille.Grille;

public class Joueur {
	
	protected String nom;
	protected String type;
	protected int coup;
	
	
	// Constructeur de joueur
	public Joueur(String nom, String type){
		this.nom = nom;
		this.type = type;
	}
	
	//
	public String getNom() {
		return this.nom;
	}
	
	public String getType() {
		return this.type;
	}
	
	// diviser le string et retourner le type du joueur et son nom
	public static String[] typeNomJoueur(String entreeJoueur) {
			
		String[] divisee = entreeJoueur.split("\\s");
		
		String typeJoueur = divisee[0];
		String nomJoueur = divisee[1];
		for (int i=2; i<divisee.length; i++) {
			nomJoueur = nomJoueur.concat(" " + divisee[i]); 
		}
		String[] resultat = new String[]{typeJoueur, nomJoueur};
		return resultat;
	}
	
	// savoir si le nom de joueur et son type sont valides
	public static boolean valideTypeJoueur(String typeJoueur, int numeroJoueur) {
		return false;
	}

	
	// avoir le coup du joueur
	public int coupJoueur(Grille grille, int largeur) {
		int coup = 0;
		return coup;
	}


}