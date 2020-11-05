import javax.swing.*;
import java.awt.event.*;

public class Bear extends Prize
{
    public Bear(Money m, ImageIcon image)
    {
        super(new JButton("Teddy Bear: $" + 100),image,100,"Bear");

        class ClickListener implements ActionListener
        {
            public void actionPerformed(ActionEvent e)
            {
                if(m.getMoney() >= getCost())
                {
                    m.decreaseMoney(getCost());
                    incrementCount();
                    m.updateMoneyViewer("Money: $" + m.getMoney());
                }
            }
        }

        addAction(new ClickListener());
    }
}