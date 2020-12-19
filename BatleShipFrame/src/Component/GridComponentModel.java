package Component;


public class GridComponentModel extends AbstractGridModel{

    private Grille grille;

    public GridComponentModel()
    {
        super();
        setGrille(new Grille());
    }
    
    public Grille getGrille() 
    {
        return grille;
    }
	
    public final void setGrille(Grille grille) 
    {
        this.grille = grille;
        if(getComponent()!=null)
        {
            getComponent().repaint();
        }
    }
    	
	@Override
	public boolean estBateau(Coordinate coordonnee) {
        if (coordonnee != null)
        {
            Case[][] grille = getGrille().map;
            Case c = grille[coordonnee.getColonne()][coordonnee.getLigne()];
            Ship b = c.getShip();

            return (b!=null);
        }		
		return false;
	}

	@Override
	public boolean estBateauTouche(Coordinate coordonnee) {
        if (coordonnee != null)
        {
            Case[][] grille = getGrille().map;
            Case c = grille[coordonnee.getColonne()][coordonnee.getLigne()];

            return c.estTouchee() && c.estVide() == false;
        }
        return false;
	}

	@Override
	public boolean estTouche(Coordinate coordonnee) {
        if (coordonnee != null)
        {
            Case[][] grille = this.getGrille().map;
            Case c = grille[coordonnee.getColonne()][coordonnee.getLigne()];

            return c.estTouchee();
        }
        return false;
	}

	@Override
	public boolean estVisible() {
		return false;
	}
	
	

}
