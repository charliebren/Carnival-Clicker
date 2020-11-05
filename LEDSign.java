import javax.swing.*;
// import javafx.scene.image.Image;
import java.awt.event.*;

public class LEDSign extends Prize
{
    public LEDSign(Money m,ImageIcon image)
    {
        super(new JButton("LED Sign: $" + 100000),image,100000,"LED Sign");

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