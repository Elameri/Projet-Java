package puissancequatre;

import java.util.Scanner;
import puissancequatre.grille.Grille;
import puissancequatre.joueur.Humain;
import puissancequatre.joueur.IA;
import puissancequatre.joueur.Joueur;

import java.io.FileWriter;
import java.io.IOException;


public class Partie {

	private int nbrManchesPourGagner;
	private int pionsPourVictoire;
	static Scanner scan = new Scanner(System.in);
	private Grille maGrille;
	private Joueur[] joueur = new Joueur[2];
	private int[] mancheGagneJ = new int[2];
	
	Partie(){
		
		this.nbrManchesPourGagner = 2;
		this.pionsPourVictoire = 4;
		this.maGrille = new Grille();
		
	}
	
	// methode principale pour jouer
	private void jouer() {
		char symb = 'x';
		
		for (int i=0; i<2; i++){
			
			String joueurScan;
			String[] infosJoueurTmp = new String[] {};

			int bonJoueur = 0;
			while(bonJoueur != 1) {
				System.out.println("Joueur " + (i+1) + "?");
				joueurScan = scan.nextLine();
				
				if((joueurScan.split("\\s")).length > 1) {
					
					infosJoueurTmp = Joueur.typeNomJoueur(joueurScan);
					
					if ( Humain.valideTypeJoueur(infosJoueurTmp[0], (i+1)) ) {
						bonJoueur = 1;
						joueur[i] = new Humain(infosJoueurTmp[1], "");
					}
					else if ( IA.valideTypeJoueur(infosJoueurTmp[0], (i+1)) ) {
						bonJoueur = 1;
						joueur[i] = new IA(infosJoueurTmp[1], "");
					}
				}
				else {
					System.out.println("Erreur saisie Joueur " + (i+1));
				}
			}

		}

		//System.out.println("\nMatch de : " + joueur[0].getNom() + " vs " + joueur[1].getNom() + "\n");
		
		ecrireDansFichier("Joueur 1 est " + joueur[0].getType() + " " + joueur[0].getNom(), false);
		ecrireDansFichier("Joueur 2 est " + joueur[1].getType() + " " + joueur[1].getNom(), true);
		
		int quelquUnAGagneManche = 0;
		int coupRendu = -1;
		int finPartie = 0;
		
		while (mancheGagneJ[0] < nbrManchesPourGagner && mancheGagneJ[1] < nbrManchesPourGagner && finPartie == 0) {
			
			maGrille.initialiserGrille();
			maGrille.afficherGrille();
			quelquUnAGagneManche = 0;
			
			ecrireDansFichier("Manche commence", true);
			
			while (quelquUnAGagneManche != 1) {
				
				for (int i=0; i<2; i++){
					
					if(i == 0) {
						symb = 'x';
					}
					else {
						symb = 'o';
					}
					
					// verifier que la manche n'est pas finie avant de lire le coup du joueur
					if (quelquUnAGagneManche != 1) {
							coupRendu = lireCoupValide(joueur[i], (i+1), symb);
							if (coupRendu == 0) {
								finPartie = 1;
								quelquUnAGagneManche = 1;
								break;

							}
							maGrille.afficherGrille();
					}
					
					// verifier si un joueur a gagne ou partie nulle 
					if(maGrille.AGagne(symb, pionsPourVictoire)) {
						//System.out.println(joueur[i].getNom() + " a gagne la manche!\n");
						System.out.println("Joueur " + (i+1) + " gagne");
						quelquUnAGagneManche = 1;
						mancheGagneJ[i]++;
						ecrireDansFichier("Joueur " + (i+1) + " gagne", true);
					}
					else if( maGrille.estCeQueGrillePleine() )	{
						System.out.println(" Egalite !\n");
						quelquUnAGagneManche = 1;
						ecrireDansFichier("Egalite", true);
						
					}
					
				}
				

				//maGrille.afficherGrille();
				//System.out.println();

			}
			
			if (finPartie == 0) {
				ecrireDansFichier("Score " + mancheGagneJ[0] + " - " + mancheGagneJ[1], true);
			}
			
			
		}
		
		if (finPartie == 0) {
			if (mancheGagneJ[0] > mancheGagneJ[1]) {
				System.out.println(joueur[0].getNom() + " a gagne la partie!\n");
				ecrireDansFichier("Partie finie", true);
				ecrireDansFichier(joueur[0].getNom() + " a gagne la partie\n", true);
			}
			else {
				System.out.println(joueur[1].getNom() + " a gagne la partie!\n");
				ecrireDansFichier("Partie finie", true);
				ecrireDansFichier(joueur[1].getNom() + " a gagne la partie\n", true);
			}
		}
	}
	
	
	// Ecrire dans le fichier log.txt
	private void ecrireDansFichier(String ligne, boolean add) {
		try {

			if(add) { // ajouter au fichier et ne pas ecraser
				//FileWriter myWriter = new FileWriter("src" + File.separator + "puissancequatre" + File.separator + "log.txt", true);
				FileWriter myWriter = new FileWriter("log.txt", true);
				myWriter.write(ligne);
				myWriter.write("\n");
				myWriter.close();
			}
			else { // tt ecraser et ecrire
				//FileWriter myWriter = new FileWriter("src" + File.separator + "puissancequatre" + File.separator + "log.txt");
				FileWriter myWriter = new FileWriter("log.txt");
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
	private int lireCoupValide(Joueur joueur, int numJoueur, char symboleJoueur) {
		int coupJ = 0;
		int coupValableJ = 0;

		while (coupValableJ != 1) { // tant qu'on a pas un coup valable du joueur
			coupJ = joueur.coupJoueur(maGrille, 7);
			if (coupJ == 0) {
				return 0;
			}
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
		return coupJ;
	}
	
	
	
	
	public static void main(String[] args) {
		Partie partie = new Partie();
		
		partie.jouer();
		//System.exit(0);
		
	}
	

}
