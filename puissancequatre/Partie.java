package puissancequatre;

import java.util.Scanner;
import puissancequatre.grille.Grille;
import puissancequatre.joueur.Joueur;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class Partie {

	private int nbrManchesPourGagner;
	private int pionsPourVictoire;
	static Scanner scan = new Scanner(System.in);
	private Grille maGrille;
	private Joueur joueur1;
	private Joueur joueur2;
	
	Partie(){
		
		this.nbrManchesPourGagner = 3;
		this.pionsPourVictoire = 4;
		this.maGrille = new Grille();
		
	}
	
	// methode principale pour jouer
	private void jouer() {
		
		//String joueurScan;
		String[] infosJoueur1 = new String[] {};
		String[] infosJoueur2 = new String[] {};
		
		// Avoir le nom et le type des 2 joueurs
		infosJoueur1 = lireNomTypeJoueur(1);
		infosJoueur2 = lireNomTypeJoueur(2);

		System.out.println("\nMatch de : " + infosJoueur1[1] + " vs " + infosJoueur2[1] + "\n");
		
		joueur1 = new Joueur(infosJoueur1[1], infosJoueur1[0]);
		joueur2 = new Joueur(infosJoueur2[1], infosJoueur2[0]);
		
		ecrireDansFichier("Joueur 1 est " + infosJoueur1[0] + " " + infosJoueur1[1], false);
		ecrireDansFichier("Joueur 2 est " + infosJoueur2[0] + " " + infosJoueur2[1], true);
		
		//maGrille.initialiserGrille();
		
		int mancheGagneJ1 = 0;
		int mancheGagneJ2 = 0;
		int quelquUnAGagneManche = 0;
		
		while (mancheGagneJ1 < nbrManchesPourGagner && mancheGagneJ2 < nbrManchesPourGagner) {
			
			maGrille.initialiserGrille();
			maGrille.afficherGrille();
			quelquUnAGagneManche = 0;
			
			ecrireDansFichier("Manche commence", true);
			
			while (quelquUnAGagneManche != 1) {
				
				// verifier que la manche n'est pas finie avant de lire le coup du joueur 1
				if (quelquUnAGagneManche != 1) {
					lireCoupValide(joueur1, 1, 'X');
				}
   
				// verifier si le joueur 1 a gagne ou partie nulle 
				if(maGrille.AGagne('X', pionsPourVictoire)) {
					System.out.println(infosJoueur1[1] + " a gagne la manche!\n");
					quelquUnAGagneManche = 1;
					mancheGagneJ1++;
					ecrireDansFichier("Joueur 1 gagne", true);
				}
				else if( maGrille.estCeQueGrillePleine() )	{
					System.out.println(" Egalite !\n");
					quelquUnAGagneManche = 1;
					ecrireDansFichier("Egalite", true);
					
				}
				
				// verifier que la manche n'est pas finie avant de lire le coup du joueur 2
				if (quelquUnAGagneManche != 1) {
					lireCoupValide(joueur2, 2, 'O');
				}
	
				// verifier si le joueur 2 a gagne ou partie nulle 
				if(maGrille.AGagne('O', pionsPourVictoire)) {
					System.out.println(infosJoueur2[1] + " a gagne la manche!\n");
					quelquUnAGagneManche = 1;
					mancheGagneJ2++;
					ecrireDansFichier("Joueur 2 gagne", true);
				}
				else if( maGrille.estCeQueGrillePleine() ) {
					System.out.println(" Egalite !\n");
					quelquUnAGagneManche = 1;
					ecrireDansFichier("Egalite", true);
				}
				

				maGrille.afficherGrille();
				System.out.println();

			}
			
			ecrireDansFichier("Score " + mancheGagneJ1 + " - " + mancheGagneJ2, true);
			
		}
		
		if (mancheGagneJ1 > mancheGagneJ2) {
			System.out.println(infosJoueur1[1] + " a gagne la partie!\n");
			ecrireDansFichier("Partie finie", true);
			ecrireDansFichier(infosJoueur1[1] + " a gagne la partie\n", true);
		}
		else {
			System.out.println(infosJoueur2[1] + " a gagne la partie!\n");
			ecrireDansFichier("Partie finie", true);
			ecrireDansFichier(infosJoueur2[1] + " a gagne la partie\n", true);
		}
	}
	
	// Ecrire dans le fichier log.txt
	private void ecrireDansFichier(String ligne, boolean add) {
		try {

			if(add) { // ajouter au fichier et ne pas ecraser
				FileWriter myWriter = new FileWriter("src/puissancequatre/log.txt", true);
				myWriter.write(ligne);
				myWriter.write("\n");
				myWriter.close();
			}
			else { // tt ecraser et ecrire
				FileWriter myWriter = new FileWriter("src/puissancequatre/log.txt");
				myWriter.write(ligne);
				myWriter.write("\n");
				myWriter.close();
			}

		}
		catch (IOException e) {
			System.out.println("Erreur pendant le lecture ou l'ecriture dans log.txt.");
			e.printStackTrace();
		}
	}
	

	// Avoir un coup valable d'un joueur
	private void lireCoupValide(Joueur joueur, int numJoueur, char symboleJoueur) {
		int coupJ = 0;
		int coupValableJ = 0;

		while (coupValableJ != 1) { // tant qu'on a pas un coup valable du joueur
			coupJ = joueur.coupJoueur(joueur, 7);
			if( maGrille.coupValable(coupJ) == 1 ) {
				maGrille.actualiserGrille(symboleJoueur, coupJ);
				coupValableJ = 1;
				ecrireDansFichier("Joueur " + numJoueur + " joue " + coupJ, true);
			}
			else if( maGrille.coupValable(coupJ) == 0 ) {
				System.out.println("Erreur colonne pleine " + coupJ);
			}
			else {
				System.out.println("Erreur colonne non valide " + coupJ);
			}
		}
	}
	
	// Avoir le nom et le type d'un joueur en lisant l'entrée standard
	private String[] lireNomTypeJoueur(int numJoueur) {
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
				System.out.println("Erreur saisie Joueur " + numJoueur);
			}
		}
		return infosJoueur;
	}
	
	
	
	public static void main(String[] args) {
		Partie partie = new Partie();
		
		System.out.println("+--------------------------------+");
		System.out.println("| Bienvenue sur Puissance Quatre |");
		System.out.println("+--------------------------------+\n");
		
		partie.jouer();
		//System.exit(0);
		
	}
	

}
