package Component;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComponent;





public class GridComponent extends JComponent implements MouseListener{

    public Color COLOR_SHEET_SELECT = new Color(175, 170, 215,125);
    public Color COLOR_SHEET_TOUCH = new Color(125, 125, 125);
    public Color COLOR_SHEET_GRID = new Color(175, 170, 215);
    public Color COLOR_BOAT_GRID = new Color(65, 50, 150);
    public Color COLOR_BOAT_TOUCHED = new Color(220, 0, 0);
    int k = 30;
	int x1 = 100, x2 = x1 + k*11, y1=50,y2=y1 + k*11;
	
	Coordinate caseSelect = null;
	
    private GridComponentModel model;

	
	public GridComponent(GridComponentModel model) {
		super();
		this.setModel(model);
        getModel().setView(this);
		addMouseListener(this);
	}
	
	public void drawGrid(Graphics g) {
//        g.setColor(COLOR_BOAT_GRID);
		g.setColor(Color.DARK_GRAY);
		
        for(int i = 0; i <= 11 ; i++) {
        	g.drawLine(x1 + i*k, y1, x1 + i*k, y2);
        }
        for(int i = 0; i <= 11 ; i++) {
        	g.drawLine(x1, y1 + i*k, x2, y1 + i*k);
        }
        for(int i = 1,l='A'; i <=10 ; i++,l++) {
        	g.drawString((char)l+"", x1 + i*k + 15, y1+20);
        }
        for(int i = 1; i <=10 ; i++) {
        	g.drawString(String.valueOf(i),x1 + 9, y1 + i*k + 20);
        }
	}
	
	public void actionClicked(Point p) {
		if(p.x < x2 && p.x > x1 && p.y < y2 && p.y > y1 ) {
			int x = (int)((p.x-x1)/k);
			int y = (int)((p.y-y1)/k);
			//System.out.println(x + "; " + y);
			caseSelect = new Coordinate(x, y);
		}
		getModel().setSelectedCase(caseSelect);
	}
	
    private void drawKill(Graphics g,int x,int y,int width,int height)
    {
        g.drawLine(x, y, x+width, y+height);
        g.drawLine(x+width, y, x, y+height);
    }

    private void drawState(Graphics g)
    {
    	((Graphics2D)g).setStroke(new BasicStroke(3));
    	Coordinate coordonneesCurrentCoordinates;
    	//Tracé bateaux et Tracé des touches
    	for(int x = 1 ; x<= 10;x++)
    	{
    		for(int y = 1 ; y<= 10;y++)
    		{
    			coordonneesCurrentCoordinates = new Coordinate(y, x);

    			//Si le tracé des bateaux est autorisé est qu'un bateau est présent
    			if(getModel().estBateau(coordonneesCurrentCoordinates))
    			{
    				g.setColor(COLOR_BOAT_GRID);
    				g.drawRect((x1+(coordonneesCurrentCoordinates.getColonne()*k)),(y1+(coordonneesCurrentCoordinates.getLigne()*k)),k, k);
    			}
    			
                //Tracé des état touché des cases
                if(getModel().estTouche(coordonneesCurrentCoordinates))
                {
                    
                    //Choix de la couleur adéquate
                    if(getModel().estBateauTouche(coordonneesCurrentCoordinates))
                    {
                        g.setColor(COLOR_BOAT_TOUCHED);
                    }
                    else
                    {
                        g.setColor(COLOR_SHEET_TOUCH);
                    }
                    
                    drawKill(g,(int)(x1+(coordonneesCurrentCoordinates.getColonne())*k), (int)(y1+(coordonneesCurrentCoordinates.getLigne())*k),k, k);
                }
    		}
    	}
    }
    
    public void addGridComponentListener(GridComponentListener listeners)
    {
        getModel().addListener(listeners);
    }
    public void removeListener(GridComponentListener listeners)
    {
        getModel().removeListener(listeners);
    }
    
    
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
        ((Graphics2D)g).setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        
		drawGrid(g);
		drawState(g);
		g.setColor(Color.BLACK);
		g.fillRect(x1, y1, k, k);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		//System.out.println(true);
		if(this.isEnabled()) {
			actionClicked(e.getPoint());
		}
		//System.out.println(true);
	}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
	

	public GridComponentModel getModel() {
		return model;
	}

	public void setModel(GridComponentModel model) {
		this.model = model;
	}

}
