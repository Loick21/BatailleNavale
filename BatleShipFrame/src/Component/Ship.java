package Component;

import java.util.List;



public class Ship {
	
	private Coordinate start;
	private Coordinate end;
	private int length;
	private boolean isVertical;
	private int partHit;
	private Coordinate coordHit[];
	
	public Ship(Coordinate start,int length,String orientation) {
		
		this.start = start;
		end = new Coordinate();
		this.length = length;
		if(orientation.equals("V")) {
			end.setColonne(start.getColonne());
			end.setLigne((this.length + start.getLigne())- 1 );
			isVertical = true;
		}
		else {
			end.setColonne((start.getColonne() + length) - 1 );
			end.setLigne(start.getLigne());
			isVertical = false;
		}
		
		coordHit = new Coordinate[length];
	}
	
	public Ship(Coordinate start,int length,boolean orientation) {
		
		this.start = start;
		end = new Coordinate();
		this.length = length;
		if(orientation) {
			end.setColonne(start.getColonne());
			end.setLigne((this.length + start.getLigne())- 1 );
			isVertical = true;
		}
		else {
			end.setColonne((start.getColonne() + length) - 1 );
			end.setLigne(start.getLigne());
			isVertical = false;
		}
		
		coordHit = new Coordinate[length];
	}
	
	public static  boolean isValidShip(Coordinate start, int Len,boolean orientation) {
		return (orientation)?(10-start.getLigne()>= Len) : (10-start.getColonne() >= Len );
	}
	
	public static  boolean isOverlaps(Ship s, List<Ship> fleet) {
		boolean isOvership = false;
		
		for(Ship ship : fleet) {

			if(ship.getStart().getLigne() == ship.getEnd().getLigne() && s.getStart().getLigne() == ship.getEnd().getLigne()) {
				if (s.getStart().getColonne() >= ship.getStart().getColonne()
			 			&& s.getStart().getColonne() <= ship.getEnd().getColonne()) {
					isOvership = true;
					break;
				}
		}
			else if(ship.getStart().getColonne() != ship.getEnd().getColonne() && s.getStart().getColonne() == ship.getEnd().getColonne()){
				 	if(s.getStart().getLigne() >= ship.getStart().getLigne()
						&& s.getStart().getLigne() <= ship.getEnd().getLigne()) {
				 		isOvership = true;
				 		break;
				 	}
			}
		}

		return isOvership;
}
	
	public boolean getHit(Coordinate coord) {
		
		boolean result = false;
		
		if(containCoordinate(coord)) {

			for(int i = 0; i < coordHit.length; i++) {
				if(coordHit[i] != null && coordHit[i].isEqual(coord)) {
					result = false;
					break;
				}
				else result = true;
			}
			if(result) {
				coordHit[partHit] = coord;
				partHit ++;
			}
		}
		return result;
	}

	public boolean containCoordinate(Coordinate coord) {
		
		return (coord.getColonne() >= start.getColonne())
				&& coord.getColonne() <= end.getColonne()
				&& coord.getLigne() >= start.getLigne()
				&& coord.getLigne() <= end.getLigne();
				
	}
	
	public boolean isSink(){
		return coordHit.length == partHit;
	}
	
	public Coordinate getStart() {
		return start;
	}

	public void setStart(Coordinate start) {
		this.start = start;
	}

	public Coordinate getEnd() {
		return end;
	}

	public void setEnd(Coordinate end) {
		this.end = end;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public boolean isVertical() {
		return isVertical;
	}

	public void setVertical(boolean isVertical) {
		this.isVertical = isVertical;
	}
	
	public int getPartHit() {
		return partHit;
	}

	public void setPartHit(int partHit) {
		this.partHit = partHit;
	}

	

}
