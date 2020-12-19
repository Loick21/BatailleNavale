package Component;



public class Grille {
	
	Case [][] map;

	public Grille() {
		map = new Case[11][11];

		for(int i = 0; i < map.length;i++) {
			for (int j = 0; j < map.length; j++) {
				map[i][j] = new Case(); 
			}
		}
	}
	
	public boolean isOverlaps(Ship b,Coordinate coord){
		int posx = coord.getLigne();
		int posy = coord.getColonne();
		System.out.println("x :" + posx + " y : " + posy  );
		for (int i = 0; i < b.getLength(); i++)
		{
			if (this.map[posy][posx + i].estVide() == false)
			{
				return true;
			}
		}
		return false;
	}
	
	public void getHit(Coordinate c) {
		if(c != null)
		map[c.getColonne()][c.getLigne()].setEstTouchee(true);
	}
	
	public boolean positionnerBateau(Ship b, Coordinate coord, boolean horizontal)
	{
		if (b == null || coord == null)
		{
			throw new NullPointerException();
		}
		
		int posx = coord.getLigne();
		int posy = coord.getColonne();
		if (horizontal)
		{
			if (coord.getLigne() + b.getLength() > 11) return false;
			
			for (int i = 0; i < b.getLength(); i++)
			{
				if (this.map[posy][posx + i].estVide() == false)
				{
					return false;
				}
			}
			
			for (int i = 0; i < b.getLength(); i++)
			{
				this.map[posy][posx + i] = new Case(b);
			}
		}
		else
		{
			if (coord.getColonne()+ b.getLength() > 11)
			{
				return false;
			}
			for (int i = 0; i < b.getLength(); i++)
			{
				if (this.map[posy + i][posx].estVide() == false)
				{
					return false;
				}
			}
			for (int i = 0; i < b.getLength(); i++)
			{
				this.map[posy + i][posx] = new Case(b);
			}
		}
		return true;
	}
	
	public boolean recevoirMissile(Coordinate coordonnees) 
	{
		if (coordonnees == null)
		{
			System.out.println("rien");
		}
		
                if(this.map[coordonnees.getColonne()][coordonnees.getLigne()].estTouchee())
                {
                    System.out.println("Case déja touché");
                    return false;
                }
                else
                {
                    Ship b = this.map[coordonnees.getColonne()][coordonnees.getLigne()].toucher();

                    if (b == null)
                    {
                            return false;
                    }
                    else
                    {
                            if (b.isSink())
                            {
                                    return false;
                            }
                            else
                            {
                                    return true;
                            }
                    }
                }
	}
	
	public void addShip(Coordinate coord) {
		map[coord.getLigne()][coord.getColonne()] = new Case(new Ship(coord,1,true));
	}
	
	public boolean retirerBateau(Ship b)
	{
		if(b.getStart()!=null)
		{
			int posx = b.getStart().getLigne();
			int posy = b.getStart().getColonne();
			if (b.isVertical())
			{
				for (int i = 0; i < b.getLength(); i++)
				{
					this.map[posy][posx + i].removeShip();
				}
			}
			else
			{
				for (int i = 0; i < b.getLength(); i++)
				{
					this.map[posy + i][posx].removeShip();
				}
			}
		}
		return false;            
	}
}
