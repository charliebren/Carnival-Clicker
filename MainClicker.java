import javax.swing.*;
import java.awt.event.*;

//Class for the main button clicked
public class MainClicker implements Button
{
    private JButton mainButton;

    public MainClicker(Money m)
    {
        mainButton = new JButton("Click Me: ");
        m.updateMoneyViewer("Money: $" + m.getMoney());


        class ClicksListener implements ActionListener
        {
            public void actionPerformed(ActionEvent event)
            {
                m.increaseMoney(m.getChangeClick());
                m.updateMoneyViewer("Money: $" + m.getMoney());
            }
        }

        ActionListener listener = new ClicksListener();
        mainButton.addActionListener(listener);
    }

    public JButton getButton()
    {
        return mainButton;
    }

    public void setBoundsOfButton(int x, int y, int w, int h)
    {
        mainButton.setBounds(x, y, w, h);
    }
}