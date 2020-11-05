import javax.swing.*;
// import javafx.scene.image.Image;
import java.awt.event.*;

public class RubiksCube extends Prize
{

    public RubiksCube(Money m, ImageIcon image)
    {
        super(new JButton("Rubiks Cube: $" + 2000),image,2000,"Rubiks Cube");

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