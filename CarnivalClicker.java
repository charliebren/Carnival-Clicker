import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class CarnivalClicker
{
    private JButton goToPrizeTable;
    private JButton done;
    private MainClicker mainButton;
    private ClickChanger powerButton;

    private ArrayList<AutoChangeButton> autoChangeButtons = new ArrayList<>();

    private ArrayList<Prize> prizeTable = new ArrayList<>();

    private JFrame gameFrame;
    private JFrame prizeFrame;
    
    private TimePane time;
    private Money gameMoney;
    private CaesarCipher cipher;
    private static final int FRAME_WIDTH = 1000;
    private static final int FRAME_HEIGHT = 1000;
    private boolean gameOver = false;


    public CarnivalClicker()
    {
        goToPrizeTable = new JButton("Go to the prize table");
        done = new JButton("Done");
        gameMoney = new Money();
        mainButton = new MainClicker(gameMoney);
        powerButton = new ClickChanger(mainButton, gameMoney);
        gameFrame = new JFrame("Carnival Clicker");
        time = new TimePane(gameMoney);
        cipher = new CaesarCipher(gameMoney);
        prizeFrame = new JFrame("Prizes");

        autoChangeButtons.add(new Grandpa(gameMoney));
        autoChangeButtons.add(new Farm(gameMoney));
        autoChangeButtons.add(new Boat(gameMoney));
        autoChangeButtons.add(new House(gameMoney));
        autoChangeButtons.add(new State(gameMoney));
        autoChangeButtons.add(new Continent(gameMoney));
        autoChangeButtons.add(new World(gameMoney));

        prizeTable.add(new Bear(gameMoney,new ImageIcon(getClass().getResource("TeddyBear.png"))));
        prizeTable.add(new RubiksCube(gameMoney,new ImageIcon(getClass().getResource("RubiksCube.png"))));
        prizeTable.add(new Necklace(gameMoney,new ImageIcon(getClass().getResource("Necklace.png"))));
        prizeTable.add(new LEDSign(gameMoney,new ImageIcon(getClass().getResource("LEDSign.png"))));
        prizeTable.add(new WeenieDog(gameMoney,new ImageIcon(getClass().getResource("WeenieDog.png"))));

        class ClickListener implements ActionListener
        {
            public void actionPerformed(ActionEvent e)
            {
                gameOver = true;
                gameFrame.setVisible(false);
                prizeFrame.setVisible(true);
            }
        }

        class DoneListener implements ActionListener
        {
            public void actionPerformed(ActionEvent e)
            {
                int amountOfPrizes = 0;
                for(Prize g : prizeTable)
                {
                    g.getButton().setVisible(false);
                    g.getImage().setVisible(false);
                    String name = "";
                    int count = g.getCount();
                    amountOfPrizes += count;
                    if (count == 1)
                    {
                        name = g.getName();
                        gameMoney.setYourPrizesText(gameMoney.getYourPrizesText() + count + " " + name + ", ");
                    }
                    else if (count > 1)
                    {
                        name = g.getName() + "s";
                        gameMoney.setYourPrizesText(gameMoney.getYourPrizesText() + count + " " + name + ", ");
                    }
                }
                if(amountOfPrizes > 0)
                {
                    gameMoney.setYourPrizesText("<html><h1>" + gameMoney.getYourPrizesText().substring(0,gameMoney.getYourPrizesText().length()-2) + "<br>Thank you for playing Carnival Clicker!<br>If you want to play again,<br>close the window and start again!</h1></html>");
                }
                else
                {
                    gameMoney.setYourPrizesText("<html><h1>Thank you for playing Carnival Clicker!<br>If you want to play again,<br>close the window and start again!</h1></html>");
                }
                

                gameMoney.getYourPrizes().setVisible(true);
                done.setVisible(false);
            }
        }

        gameMoney.getYourPrizes().setVisible(false);

        goToPrizeTable.addActionListener(new ClickListener());
        done.addActionListener(new DoneListener());
    }

    public void playGame()
    {
        gameFrame.setLayout(null);
        
        gameMoney.setVisibilityMoneyViewer(true);
        gameMoney.setBoundsOfMoneyViewer(2, 1, 300, 30);
        gameMoney.setBoundsOfClickingPower(2, 30, 300, 30);
        gameMoney.setBoundsOfAutoPower(2, 60, 300, 30);
        mainButton.setBoundsOfButton(2, 100, 300, 200);

        gameFrame.add(gameMoney.getMoneyViewer());
        gameFrame.add(gameMoney.getClickPower());
        gameFrame.add(gameMoney.getAutoPower());
        gameFrame.add(mainButton.getButton());

        
        powerButton.setBoundsOfButton(FRAME_WIDTH-250, 0, 200, 30);
        gameFrame.add(powerButton.getButton());

        int autoChangeButtonX = FRAME_WIDTH-250;
        int autoChangeButtonY = 35;
        for(int i = 0;i < autoChangeButtons.size();i++)
        {
            AutoChangeButton e = autoChangeButtons.get(i);
            e.setBoundsOfButton(autoChangeButtonX, autoChangeButtonY, 200, 30);
            gameFrame.add(e.getButton());
            autoChangeButtonY += 35;
        }

        

        cipher.setBoundsOfCaesarBet(1, FRAME_HEIGHT-400, 300, 20);
        cipher.setBoundsOfPlayButton(300, FRAME_HEIGHT-400, 150, 20);
        cipher.setBoundsOfDirections(0, FRAME_HEIGHT-700, 900, 100);
        cipher.setBoundsOfAmountOfTries(0, FRAME_HEIGHT-550, 500, 30);
        cipher.setBoundsOfTheWord(0, FRAME_HEIGHT-500, 400, 30);
        cipher.setBoundsOfCaesarText(0, FRAME_HEIGHT-400, 300, 20);
        cipher.setBoundsOfCaesarGuess(300, FRAME_HEIGHT-400, 150, 20);


        gameFrame.add(cipher.getCaesarBet());
        gameFrame.add(cipher.getPlayButton());
        gameFrame.add(cipher.getDirections());
        gameFrame.add(cipher.getAmountOfTries());
        gameFrame.add(cipher.getTheWord());
        gameFrame.add(cipher.getCaesarText());
        gameFrame.add(cipher.getCaesarGuess());

        goToPrizeTable.setBounds(FRAME_WIDTH-250,FRAME_HEIGHT-400,200,30);
        gameFrame.add(goToPrizeTable);
        
        prizeFrame.setLayout(null);
        int imageX = 0;
        int imageY = 0;
        int buttonX = 0;
        int buttonY = 201;
        for(int i = 0;i < prizeTable.size();i++)
        {
            if(imageX >= FRAME_WIDTH)
            {
                imageX = 0;
                imageY += 250;
                buttonX = 0;
                buttonY += 250;
            }
            
            Prize e = prizeTable.get(i);
            e.getImage().setBounds(imageX,imageY,200,200);
            e.getButton().setBounds(buttonX,buttonY,200,30);

            e.getImage().setVisible(true);
            e.getButton().setVisible(true);

            prizeFrame.add(e.getImage());
            prizeFrame.add(e.getButton());

            imageX += 201;
            buttonX += 201;
        }

        done.setBounds(FRAME_WIDTH-250,FRAME_HEIGHT-400,200,30);
        prizeFrame.add(done);

        gameMoney.setBoundsOfYourPrizes(0, 0, 800, 600);
        prizeFrame.add(gameMoney.getYourPrizes());


        gameFrame.setSize(FRAME_WIDTH,FRAME_HEIGHT);
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setVisible(true);

        prizeFrame.setSize(FRAME_WIDTH,FRAME_HEIGHT);
        prizeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        prizeFrame.setVisible(false);

        while(!gameOver)
        {
            time.startTimer();
        }
        time.stopTimer();
        gameMoney.setBoundsOfMoneyViewer(0, 500, 300, 30);
        prizeFrame.add(gameMoney.getMoneyViewer());
    }
}