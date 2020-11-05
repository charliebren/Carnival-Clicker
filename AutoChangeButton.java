import javax.swing.*;
import java.awt.event.*;

public class AutoChangeButton implements Button
{
    private JButton theButton;
    private int cost;
    private int change;

    public AutoChangeButton(JButton button, int co, int ch)
    {
        theButton = button;
        cost = co;
        change = ch;
    }

    public JButton getButton()
    {
        return theButton;
    }

    public void changeJButtonText(String t)
    {
        theButton.setText(t);
    }

    public void addAction(ActionListener l)
    {
        theButton.addActionListener(l);
    }

    public int getCost()
    {
        return cost;
    }

    public void setCost(int c)
    {
        cost = c;
    }

    public int getChange()
    {
        return change;
    }

    public void setBoundsOfButton(int x, int y, int w, int h)
    {
        theButton.setBounds(x, y, w, h);
    }
}