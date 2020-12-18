package com.zcorp.Client;


public class Coordinate {
	
	private int column;
    private int line;
    private char A = 'A';
    
    public Coordinate(){
    	
    }
    
	public Coordinate(String coord) {
		if(coord.length() == 2) {
			column = coord.trim().charAt(0) - A + 1 ;
			line   = Integer.parseInt(String.valueOf(coord.trim().charAt(1)));
		}
		else {
			column = coord.trim().charAt(0) - A + 1 ;
			line   = Integer.parseInt(coord.trim().substring(1));
		}

	}
	
	public static boolean checkCoordinate(String coord) {
		if(coord.trim().length() == 2 ) {
			int coordColumn = coord.trim().charAt(0) - 'A';
			int coordLine   = Integer.parseInt(String.valueOf(coord.trim().charAt(1)));
			return checkCoordinateFormat(coordColumn,coordLine);
		}
		else if(coord.trim().length() == 3) {
			int coordColumn = coord.trim().charAt(0) - 'A';
			int coordLine   = Integer.parseInt(coord.trim().substring(1));
			return checkCoordinateFormat(coordColumn,coordLine);
		}
		else return false;
	}
	
	public static boolean checkCoordinateFormat(int column, int line) {
		return ((column + 'A') >= 65 && (column + 'A') <= 'J') && ((line >= 1) && (line <= 10)); 
	}
	
	public boolean isEqual(Coordinate coord){
		return (this.getLine() == coord.getLine() && this.getColumn() == coord.getColumn());
	}
	
    public int getLine() {
		return line;
	}

	public void setLine(int line) {
		this.line = line;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}
 
}
