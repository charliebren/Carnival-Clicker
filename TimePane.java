import javax.swing.*;
import java.awt.event.*;

public class TimePane
{
    private Timer time;
    public TimePane(Money m)
    {
        class TimeListener implements ActionListener
        {
            public void actionPerformed(ActionEvent event)
            {
                m.increaseMoney(m.getChangeAuto());
                m.updateMoneyViewer("Money: $" + m.getMoney());
            }
        }

        time = new Timer(1000, new TimeListener());
    }

    public void startTimer()
    {
        time.start();
    }

    public void stopTimer()
    {
        time.stop();
    }
}