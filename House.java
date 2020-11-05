import javax.swing.*;
import java.awt.event.*;

public class House extends AutoChangeButton
{
    public House(Money m)
    {
        super(new JButton("Get a House: $" + 5000),5000,50);

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
                    changeJButtonText("Get a House: $" + getCost()); 
                    m.updateMoneyViewer("Money: $" + m.getMoney());
                }
            }
        }

        ActionListener listener = new ClicksListener();
        addAction(listener);
    }
}