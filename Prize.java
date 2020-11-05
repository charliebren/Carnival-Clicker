import javax.swing.*;
import java.awt.Image;
import java.awt.event.*;

public class Prize
{
    private JButton purchaseButton;
    private final JLabel prizeImage;
    private int cost;
    private int count;
    private String name;

    public Prize(JButton button, ImageIcon i, int c, String n)
    {
        purchaseButton = button;
        Image image = i.getImage(); // transform it 
        Image newimg = image.getScaledInstance(200, 200,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
        ImageIcon theNewImg = new ImageIcon(newimg);
        prizeImage = new JLabel(theNewImg);
        cost = c;
        name = n;


        purchaseButton.setVisible(true);
        prizeImage.setVisible(true);
    }

    public JButton getButton()
    {
        return purchaseButton;
    }

    public void addAction(ActionListener l)
    {
        purchaseButton.addActionListener(l);
    }

    public JLabel getImage()
    {
        return prizeImage;
    }

    public int getCost()
    {
        return cost;
    }

    public void incrementCount()
    {
        count++;
    }

    public int getCount()
    {
        return count;
    }

    public String getName()
    {
        return name;
    }
}