package puissancequatre;

public class Partie {

	private int nbrManches;
	private int pionsPourVictoire;
	int manche;
	
	Partie(){
		this.nbrManches = 6;
		this.pionsPourVictoire = 4;
		this.manche = 0;
	}
	
	// Faire savoir combien il ya de manche dans une partie
	public int getNbrManches() {
		return nbrManches;
	}
	
	// Faire savoir dans quelle manche on est, et la changer
	public int getManche() {
		return manche;
	}
	
	public void setManche(int manche) {
		this.manche = manche;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	

}
