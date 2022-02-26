package puissancequatre.joueur;

import java.util.Scanner;

import puissancequatre.grille.Grille;

public class Humain extends Joueur {

	public Humain(String nom, String type) {
		super(nom, "humain");
	}

	public static boolean valideTypeJoueur(String typeJoueur, int numeroJoueur) {
		
		if ( (typeJoueur).equals("humain") ) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public int coupJoueur(Grille grille, int largeur) {
		int coup = 0;
		Scanner scanner = new Scanner(System.in);
		
		int valable = -1;
		String coupScan;
			
		while (valable == -1) {
			coupScan = scanner.nextLine();
			valable = validite(coupScan);

		}
			
		coup = valable;
			
		return coup;
	}
	private static int validite(String coupScan) {
		int coup = -1;
		if ( (coupScan).equals("sortir") ) {
			coup = 0;
		}
		else {
			try {
	            coup = Integer.parseInt(coupScan);
	        } catch (Exception e) {
	            System.out.println("Erreur saisie colonne " + coupScan);
	            //scanner.close();
	            return -1;
	        }
		}
        
        return coup;
    }
	
	/*
	public int coupJoueur(Grille grille, int largeur) {
		int coup = 0;

		int[] estCeInt = scannerInt();

			
		while (estCeInt[0] == 0) {
			estCeInt = scannerInt();
		}
			
		coup = estCeInt[1];
			
		return coup;
	}
	
	
	private static int[] scannerInt() {
		Scanner scanner = new Scanner(System.in);
		int coup = -1;
        try {
        	//System.out.print("Vous jouez : ");
            coup = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Erreur saisie colonne " + coup);
            //scanner.close();
            return new int[] {0, 0};
        }
        return new int[] {1, coup};
    }
    */

}
