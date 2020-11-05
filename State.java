import javax.swing.*;
import java.awt.event.*;

public class State extends AutoChangeButton
{
    public State(Money m)
    {
        super(new JButton("Get a State: $" + 10000),10000,100);

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
                    changeJButtonText("Get a State: $" + getCost()); 
                    m.updateMoneyViewer("Money: $" + m.getMoney());
                }
            }
        }

        ActionListener listener = new ClicksListener();
        addAction(listener);
    }
}