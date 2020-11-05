import javax.swing.*;

public class Money 
{
    private int money;
    private int changeInClick;
    private int changeInAuto;
    private final JLabel moneyViewer;
    private final JLabel clickingPower;
    private final JLabel autoPower;
    private final JLabel yourPrizes;

    public Money()
    {
        money = 0;
        changeInClick = 1;
        changeInAuto = 0;
        moneyViewer = new JLabel();
        clickingPower = new JLabel();
        autoPower = new JLabel("Money per Second: $" + changeInAuto);
        yourPrizes = new JLabel("The prizes you have claimed include: ");
    }

    public int getMoney()
    {
        return money;
    }

    public void decreaseMoney(int m)
    {
        money -= m;
    }

    public void increaseMoney(int m)
    {
        money += m;
    }

    public int getChangeClick()
    {
        return changeInClick;
    }

    public void setChangeClick(int c)
    {
        changeInClick = c;
    }

    public int getChangeAuto()
    {
        return changeInAuto;
    }

    public void setChangeAuto(int a)
    {
        changeInAuto = a;
    }

    public void setVisibilityMoneyViewer(boolean b)
    {
        moneyViewer.setVisible(true);
    }

    public JLabel getMoneyViewer()
    {
        return moneyViewer;
    }

    public void updateMoneyViewer(String a)
    {
        moneyViewer.setText(a);
    }

    public JLabel getClickPower()
    {
        return clickingPower;
    }

    public void updateClickPower(String c)
    {
        clickingPower.setText(c);
    }

    public JLabel getAutoPower()
    {
        return autoPower;
    }

    public void updateAutoPower(String a)
    {
        autoPower.setText(a);
    }

    public void increaseMoneyAuto()
    {
        money += changeInAuto;
    }

    public void setYourPrizesText(String s)
    {
        yourPrizes.setText(s);
    }

    public String getYourPrizesText()
    {
        return yourPrizes.getText();
    }

    public JLabel getYourPrizes()
    {
        return yourPrizes;
    }

    public void setBoundsOfMoneyViewer(int x, int y, int w, int h)
    {
        moneyViewer.setBounds(x,y,w,h);
    }

    public void setBoundsOfClickingPower(int x, int y, int w, int h)
    {
        clickingPower.setBounds(x,y,w,h);
    }

    public void setBoundsOfAutoPower(int x, int y, int w, int h)
    {
        autoPower.setBounds(x,y,w,h);
    }

    public void setBoundsOfYourPrizes(int x, int y, int w, int h)
    {
        yourPrizes.setBounds(x,y,w,h);
    }
}