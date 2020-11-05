import javax.swing.*;
// import javafx.scene.image.Image;
import java.awt.event.*;

public class Necklace extends Prize
{
    public Necklace(Money m,ImageIcon image)
    {
        super(new JButton("Necklace: $" + 10000),image,10000,"Necklace");

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