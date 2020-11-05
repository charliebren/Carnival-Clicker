import javax.swing.*;
import java.awt.event.*;

public class Continent extends AutoChangeButton
{
    public Continent(Money m)
    {
        super(new JButton("Get a Continent: $" + 20000),20000,1000);

        class ClicksListener implements ActionListener
        {
            public void actionPerformed(ActionEvent event)
            {
                if(m.getMoney() >= getCost())
                {
                    m.setChangeAuto(m.getChangeAuto() + getChange());
                    m.updateAutoPower("Money per Second: $" + m.getChangeAuto());
                    m.decreaseMoney(getCost());
                    setCost((int) Math.round(getCost() * 1.15));
                    changeJButtonText("Get a Continent: $" + getCost()); 
                    m.updateMoneyViewer("Money: $" + m.getMoney());
                }
            }
        }

        ActionListener listener = new ClicksListener();
        addAction(listener);
    }
}