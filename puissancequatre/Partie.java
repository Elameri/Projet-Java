package puissancequatre;

import java.util.Scanner;
import puissancequatre.grille.Grille;
import puissancequatre.joueur.Joueur;

public class Partie {

	private int nbrManches;
	private int pionsPourVictoire;
	private int manche;
	Scanner scan = new Scanner(System.in);
	
	Partie(){
		this.nbrManches = 6;
		this.pionsPourVictoire = 4;
		this.manche = 0;
		
		System.out.println("--------------------------------");
		System.out.println(" Bienvenue sur Puissance Quatre ");
		System.out.println("--------------------------------\n");
		
		String joueurScan;
		String[] infosJoueur1;
		String[] infosJoueur2;
		
		while(true) {
			System.out.println("Joueur1 ?");
			joueurScan = scan.nextLine();
			if((joueurScan.split("\\s")).length > 1) {
				infosJoueur1 = Joueur.typeNomJoueur(joueurScan);
				if(Joueur.valideTypeJoueur(infosJoueur1[0], 1)) {
					break;
				}
			}
			else {
				System.out.println("Erreur saisie Joueur 1");
			}
		}
		
		while(true) {
			System.out.println("Joueur2 ?");
			joueurScan = scan.nextLine();
			if((joueurScan.split("\\s")).length > 1) {
				infosJoueur2 = Joueur.typeNomJoueur(joueurScan);
				if(Joueur.valideTypeJoueur(infosJoueur2[0], 2)) {
					break;
				}
			}
			else {
				System.out.println("Erreur saisie Joueur 2");
			}
		}

		System.out.println("\nMatch de : " + infosJoueur1[1] + " vs " + infosJoueur2[1] + "\n");
		
		Joueur joueur1 = new Joueur(infosJoueur1[1], infosJoueur1[0]);
		Joueur joueur2 = new Joueur(infosJoueur2[1], infosJoueur2[0]);
		
		new Grille();
		char[][] grille_jeu = Grille.getGrille();
		Grille.afficherGrille(grille_jeu);
		
		int coupJ1 = 0;
		int coupJ2 = 0;
		
		while (true) {
			
			while (true) { // tant qu'on a pas un coup valable du j1
				coupJ1 = joueur1.coupJoueur(joueur1, 7);
				if(Grille.coupValable(grille_jeu, coupJ1)) {
					grille_jeu = Grille.actualiserGrille(grille_jeu, 'X', coupJ1);
					break;
				}
				else {
					System.out.println("coup non valable, veuillez rejouer ");
				}
			}
			
			while (true) { // tant qu'on a pas un coup valable du j2
				coupJ2 = joueur2.coupJoueur(joueur2, 7);
				if(Grille.coupValable(grille_jeu, coupJ2)) {
					grille_jeu = Grille.actualiserGrille(grille_jeu, 'O', coupJ2);
					break;
				}
				else {
					System.out.println("coup non valable, veuillez rejouer ");
				}
			}
			Grille.afficherGrille(grille_jeu);
		}
		
		//scan.close();
	}
	
	
	
	// getter pour le nbr de manches d'une partie
	public int getNbrManches() {
		return nbrManches;
	}
	
	// getter et setter de quelle manche on est
	public int getManche() {
		return manche;
	}
	
	public void setManche(int manche) {
		this.manche = manche;
	}
	
	public static void main(String[] args) {
		new Partie();
		//System.exit(0);
		
	}
	

}