package Component;


public class Coordinate {
	
	private int ligne,colonne;
    private char A = 'A';

	public Coordinate(String colonne, int ligne)
	{
		
		this.colonne = colonne.charAt(0)-65;
		this.ligne   = ligne-1;
	}
    public Coordinate(){
    	
    }
	public Coordinate(int colonne, int ligne)
	{
		this.colonne = colonne;
		this.ligne   = ligne;
	}
	public Coordinate(String coord) {
		if(coord.length() == 2) {
			colonne = coord.trim().charAt(0) - A + 1 ;
			ligne   = Integer.parseInt(String.valueOf(coord.trim().charAt(1)));
		}
		else {
			colonne = coord.trim().charAt(0) - A + 1 ;
			ligne   = Integer.parseInt(coord.trim().substring(1));
		}

	}
	
	public boolean isEqual(Coordinate coord){
		return (this.getLigne() == coord.getLigne() && this.getColonne() == coord.getColonne());
	}
	
	public int getLigne() {
		return ligne;
	}

	public void setLigne(int ligne) {
		this.ligne = ligne;
	}

	public int getColonne() {
		return colonne;
	}

	public void setColonne(int colonne) {
		this.colonne = colonne;
	}
	
	

}
