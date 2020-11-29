package puissancequatre;

import java.util.Scanner;
import puissancequatre.grille.Grille;
import puissancequatre.joueur.Joueur;

public class Partie {

	private static int nbrManchesPourGagner;
	private static int pionsPourVictoire;
	static Scanner scan = new Scanner(System.in);
	
	Partie(){
		Partie.nbrManchesPourGagner = 3;
		Partie.pionsPourVictoire = 4;
		
	}
	
	// methode principale pour jouer
	public static void jouer() {
		
		//String joueurScan;
		String[] infosJoueur1 = new String[] {};
		String[] infosJoueur2 = new String[] {};
		
		// Avoir le nom et le type des 2 joueurs
		infosJoueur1 = lireNomTypeJoueur(1);
		infosJoueur2 = lireNomTypeJoueur(2);

		System.out.println("\nMatch de : " + infosJoueur1[1] + " vs " + infosJoueur2[1] + "\n");
		
		Joueur joueur1 = new Joueur(infosJoueur1[1], infosJoueur1[0]);
		Joueur joueur2 = new Joueur(infosJoueur2[1], infosJoueur2[0]);
		
		//Grille grilleJeu = new Grille();
		//char[][] grille_jeu = grilleJeu.getGrille();
		//Grille.afficherGrille(grille_jeu);
		
		int mancheGagneJ1 = 0;
		int mancheGagneJ2 = 0;
		int quelquUnAGagneManche = 0;
		
		while (mancheGagneJ1 < nbrManchesPourGagner && mancheGagneJ2 < nbrManchesPourGagner) {
			
			Grille grilleJeu = new Grille();
			char[][] grille_jeu = grilleJeu.getGrille();
			Grille.afficherGrille(grille_jeu);
			quelquUnAGagneManche = 0;
			
			while (quelquUnAGagneManche != 1) {
				
				if (quelquUnAGagneManche != 1) {
					grille_jeu = lireCoupValide(joueur1, grille_jeu, 1, 'X');
				}
				
				if(Grille.AGagne(grille_jeu, 'X', pionsPourVictoire)) {
					System.out.println(infosJoueur1[1] + " a gagné la manche!\n");
					quelquUnAGagneManche = 1;
					mancheGagneJ1++;
				}
				
				if (quelquUnAGagneManche != 1) {
					grille_jeu = lireCoupValide(joueur2, grille_jeu, 2, 'O');
				}
	
				if(Grille.AGagne(grille_jeu, 'O', pionsPourVictoire)) {
					System.out.println(infosJoueur2[1] + " a gagné la manche!\n");
					quelquUnAGagneManche = 1;
					mancheGagneJ2++;
				}
				
				if (quelquUnAGagneManche != 1) {
					Grille.afficherGrille(grille_jeu);
				}
			}
			
		}
		
		if (mancheGagneJ1 > mancheGagneJ2) {
			System.out.println(infosJoueur1[1] + " a gagné la partie!\n");
		}
		else {
			System.out.println(infosJoueur2[1] + " a gagné la partie!\n");
		}
	}
	

	// Avoir un coup valable d'un joueur
	public static char[][] lireCoupValide(Joueur joueur, char[][] grilleJeu, int numJoueur, char symboleJoueur) {
		char[][] grilleJeuUtilisee = grilleJeu;
		int coupJ = 0;
		int coupValableJ = 0;

		while (coupValableJ != 1) { // tant qu'on a pas un coup valable du joueur
			coupJ = joueur.coupJoueur(joueur, 7);
			if(Grille.coupValable(grilleJeuUtilisee, coupJ)) {
				grilleJeuUtilisee = Grille.actualiserGrille(grilleJeuUtilisee, symboleJoueur, coupJ);
				coupValableJ = 1;
			}
			else {
				System.out.println("coup non valable, veuillez rejouer ");
			}
		}
		return grilleJeuUtilisee;
	}
	
	// Avoir le nom et le type d'un joueur en lisant l'entrée standard
	public static String[] lireNomTypeJoueur(int numJoueur) {
		String joueurScan;
		String[] infosJoueur = new String[] {};

		int bonJoueur = 0;
		while(bonJoueur != 1) {
			System.out.println("Joueur" + numJoueur + " ?");
			joueurScan = scan.nextLine();
			if((joueurScan.split("\\s")).length > 1) {
				infosJoueur = Joueur.typeNomJoueur(joueurScan);
				if(Joueur.valideTypeJoueur(infosJoueur[0], numJoueur)) {
					bonJoueur = 1;
				}
			}
			else {
				System.out.println("Erreur saisie Joueur 1");
			}
		}
		return infosJoueur;
	}
	
	
	
	public static void main(String[] args) {
		new Partie();
		
		System.out.println("+--------------------------------+");
		System.out.println("| Bienvenue sur Puissance Quatre |");
		System.out.println("+--------------------------------+\n");
		
		jouer();
		//System.exit(0);
		
	}
	

}
