package Serveur;
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
			end.setColumn(start.getColumn());
			end.setLine((this.length + start.getLine())- 1 );
			isVertical = true;
		}
		else {
			end.setColumn((start.getColumn() + length) - 1 );
			end.setLine(start.getLine());
			isVertical = false;
		}
		
		coordHit = new Coordinate[length];
	}
	
	public static boolean isValidShip(Coordinate start, int Len,String orientation) {
		return (orientation.equals("V"))?(10-start.getLine()>= Len) : (10-start.getColumn() >= Len );
	}
	
	public  static boolean isValidLength(Coordinate start, Coordinate end, int length) {
		if (start.getColumn() == end.getColumn()){
			return (((end.getLine() - start.getLine()) + 1) == length );
		}
		else return ((end.getColumn() - start.getColumn()) + 1 == length);
	}
	
//	public static boolean isOverlaps(Ship s, Ship ship) {
//		if(s.getStart().getLine() == s.getEnd().getLine() && s.getStart().getLine() == ship.getEnd().getLine()) {
//			 	return (ship.getStart().getColumn() >= s.getStart().getColumn()
//			 			&& ship.getStart().getColumn() <= s.getEnd().getColumn());
//		}
//		else if(s.getStart().getColumn() != s.getEnd().getColumn() && s.getStart().getColumn() == ship.getEnd().getColumn()){
//				return (ship.getStart().getLine() >= s.getStart().getLine()
//						&& ship.getStart().getLine() <= s.getEnd().getLine());
//		}
//		return false;
//}
	
	public static boolean isOverlaps(Ship s, List<Ship> fleet) {
		boolean isOvership = false;
		
		for(Ship ship : fleet) {
//			System.out.println("A1 Column : " + ship.getStart().getColumn() +" "+  "Line : " +ship.getStart().getLine());
//			System.out.println("A1 end Column : " + s.getEnd().getColumn() +" "+  "Line : " + s.getEnd().getLine());
//			System.out.println("C1 Column : " + s.getStart().getColumn() +" "+  "Line : " + s.getStart().getLine());
//
//			System.out.println(ship.getStart().getLine() == ship.getEnd().getLine() && s.getStart().getLine() == ship.getEnd().getLine());
			if(ship.getStart().getLine() == ship.getEnd().getLine() && s.getStart().getLine() == ship.getEnd().getLine()) {
				if (s.getStart().getColumn() >= ship.getStart().getColumn()
			 			&& s.getStart().getColumn() <= ship.getEnd().getColumn()) {
					isOvership = true;
					break;
				}
		}
			else if(ship.getStart().getColumn() != ship.getEnd().getColumn() && s.getStart().getColumn() == ship.getEnd().getColumn()){
				 	if(s.getStart().getLine() >= ship.getStart().getLine()
						&& s.getStart().getLine() <= ship.getEnd().getLine()) {
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
		
		return (coord.getColumn() >= start.getColumn())
				&& coord.getColumn() <= end.getColumn()
				&& coord.getLine() >= start.getLine()
				&& coord.getLine() <= end.getLine();
				
	}
	
	public static boolean checkOrientation(String orientation) {
		return (orientation.equals("H") || orientation.equals("V"));
		
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
