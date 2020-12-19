package Component;

import java.util.ArrayList;


public abstract class AbstractGridModel {
	
    private ArrayList<GridComponentListener> listeners;
    private GridComponent m_componentMain;
    
    private Coordinate coordonneesSelected;

    public AbstractGridModel()
    {
    	listeners = new ArrayList<>();
    }
    
    public void addListener(GridComponentListener listener)
    {
    	listeners.add(listener);
    }
    
    public void removeListener(GridComponentListener listener)
    {
    	listeners.remove(listener);
    }  
    
    private void notifyListenersCaseSelected()
    {
        for(GridComponentListener listener:listeners)
        {
            listener.caseSelected(coordonneesSelected);
        }
    }
    
    public abstract boolean estBateau(Coordinate coordonnee);

    public abstract boolean estBateauTouche(Coordinate coordonnee);

    public abstract boolean estTouche(Coordinate coordonnee);

    public abstract boolean estVisible();
    
    public GridComponent getComponent() 
    {
        return m_componentMain;
    }
    
    public Coordinate getSelectedCase()
    {
        return coordonneesSelected;
    }

    public void setSelectedCase(Coordinate coordonnees)
    {
    	coordonneesSelected = coordonnees;
    	notifyListenersCaseSelected();
        if(m_componentMain!=null)
        {
            m_componentMain.repaint();
        }
    }
    
    public void setView(GridComponent m_componentMain) 
    {
        this.m_componentMain = m_componentMain;
    }
}
