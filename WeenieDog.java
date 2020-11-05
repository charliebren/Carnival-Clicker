import javax.swing.*;
// import javafx.scene.image.Image;
import java.awt.event.*;

public class WeenieDog extends Prize
{
    public WeenieDog(Money m,ImageIcon image)
    {
        super(new JButton("Weenie Dog: $" + 5000000),image,5000000,"Weenie Dog");

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