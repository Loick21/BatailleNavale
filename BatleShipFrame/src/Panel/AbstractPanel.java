package Panel;

import java.awt.Component;

import javax.swing.JPanel;

import Controller.Controller;


public abstract class AbstractPanel {
	
    private JPanel m_panelMain; 
    private Controller m_controllerMain;
    
    public AbstractPanel(Controller controllerMain)
    {
        m_controllerMain = controllerMain;
        m_panelMain = new JPanel();
    }
    
    protected Controller getController(){
        return m_controllerMain;
    }
    
    public JPanel getPanel(){
        return m_panelMain;
    }
    
    public boolean isEnabled()
    {
        return getPanel().isEnabled();
    } 

    protected void setController(Controller controllerMain) 
    {
        m_controllerMain = controllerMain;
    }
    public void setEnabled(boolean state)
    {
        getPanel().setEnabled(state);
        for(Component c:getPanel().getComponents())
        {
            c.setEnabled(state);
        }
    } 
    public void setPanel(JPanel panel) 
    {
        m_panelMain = panel;
    }

}
