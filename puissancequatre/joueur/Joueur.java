package puissancequatre.joueur;

import java.util.Random;
import java.util.Scanner;

public class Joueur {
	
	String nom;
	String type;
	//String symbole;
	int coup;
	
	
	// Constructeur de joueur
	public Joueur(String nom, String type){
		this.nom = nom;
		this.type = type;
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
		
		if ( (typeJoueur).equals("ia") || (typeJoueur).equals("humain") ) {
			return true;
		}
		else {
			System.out.println("Erreur saisie Joueur " + numeroJoueur);
			return false;
		}
	}
	
	// avoir le coup du joueur
	public int coupJoueur(Joueur joueurX, int largeur) {
		int coup = 0;
		Random rand = new Random();
		
		if ( (joueurX.type).equals("ia") ) {
			coup = rand.nextInt(largeur - 1) + 1;
			System.out.println("\n" + joueurX.nom + " joue : " + coup + "\n");
		}
		else if ( (joueurX.type).equals("humain") ) {
			
			int[] estCeInt = scannerInt();;
			
			while (estCeInt[0] == 0) {
				estCeInt = scannerInt();
		    }
			
			coup = estCeInt[1];
			
		}
		return coup;
	}
	
	
	private static int[] scannerInt() {
		Scanner scanner = new Scanner(System.in);
		int coup = -1;
        try {
        	System.out.print("\nVous jouez : "); 
            coup = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Erreur saisie colonne xyz");
            //scanner.close();
            return new int[] {0, 0};
        }
        return new int[] {1, coup};
    }

}