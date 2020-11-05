import javax.swing.*;
import java.awt.event.*;

//The purpose of this class is to change the power of the click
public class ClickChanger implements Button
{
    private JButton powerButton;
    private int cost;
    private int change;

    public ClickChanger(MainClicker b, Money m)
    {
        cost = 100;
        change = 1;
        powerButton = new JButton("Increase click power $" + cost);
        m.updateClickPower("Clicking power: $" + m.getChangeClick());

        class ClicksListener implements ActionListener
        {
            public void actionPerformed(ActionEvent event)
            {
                if(m.getMoney()>=cost)
                {
                    m.setChangeClick(m.getChangeClick() + change);
                    m.decreaseMoney(cost);
                    cost = cost * 10;
                    powerButton.setText("Increase click power $" + cost);
                    m.updateMoneyViewer("Money: $" + m.getMoney());
                    m.updateClickPower("Clicking power: $" + m.getChangeClick());
                }
            }
        }

        ActionListener listener = new ClicksListener();
        powerButton.addActionListener(listener);
    }

    public JButton getButton()
    {
        return powerButton;
    }
    
    public void setBoundsOfButton(int x, int y, int w, int h)
    {
        powerButton.setBounds(x, y, w, h);
    }
}