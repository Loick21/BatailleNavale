package Component;

public class Case {
	
	Ship ship;
	private boolean estTouchee;
	
	public Case(Ship ship) {
		this.ship = ship;
	}
	
	public Case() {
		ship = null;
	}
	
	public boolean isEmpty()
	{
		return ship == null ? true : false;
	}
	
	public void removeShip()
	{
		ship = null;
	}
	
	public boolean estTouchee()
	{
		return this.estTouchee;
	}
	
	public Ship toucher()
	{
		if (this.ship != null && this.estTouchee == false)
		{
//			this.ship.getHit();
			this.estTouchee = true;	
			return this.ship;
		}	
		else if(this.estTouchee == false)
		{
			this.estTouchee = true;
		}
		return null;
	}
	
	public boolean isEstTouchee() {
		return estTouchee;
	}

	public void setEstTouchee(boolean estTouchee) {
		this.estTouchee = estTouchee;
	}

	public boolean estVide() {
		return (ship == null) ? true : false;
	}
	
	public Ship getShip()
	{
		return this.ship;
	}
        


}
