import javax.swing.*;
import java.awt.event.*;

public class CaesarCipher 
{
    private String[] alphabet = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};


    private JTextField caesarText;
    private JTextField caesarBet;

    private JButton playButton;
    private JButton caesarGuess;

    private final JLabel theWord = new JLabel("");
    private final JLabel directions = new JLabel("");
    private final JLabel amountOfTries = new JLabel("");

    private int threshold;
    private int bet;
    private int tries;
    private double betMultiplier;
    private int caesarCipherNum;

    private String[] words = {"hello","goodbye","word","hey","accept","check","carnival","player"};
    private String selectedWord;
    private String selectedWordCiphered;
    private String guess;

    public CaesarCipher(Money m)
    {
        threshold = 1000;
        caesarCipherNum = (int) ((Math.random() * 25) + 1);
        tries = 3;

        caesarBet = new JTextField("Enter amount you want to bet...");
        caesarText = new JTextField("Enter your guess...");

        playButton = new JButton("Play Caesar Cipher");
        caesarGuess = new JButton("Make a Guess");

        caesarBet.setSize(20,10);
        caesarText.setSize(20,10);

        caesarBet.setVisible(true);
        playButton.setVisible(true);

        caesarText.setVisible(false);
        caesarGuess.setVisible(false);
        theWord.setVisible(false);
        amountOfTries.setVisible(false);
        directions.setVisible(false);

        directions.setText("<html>A word (shown below) has been encrypted using a caesar cipher.<br>Your goal is to decipher the word.<br>You have 3 tries to do so.<br>Everytime you make an incorrect guess,<br>the answer you gave will be encrypted into the cipher used,<br>and the amount you can win will be decreased</html>");
        amountOfTries.setText("Tries left: " + tries);
        theWord.setText("n/a");
        
        class ClicksListener implements ActionListener
        {
            public void actionPerformed(ActionEvent event)
            {
                if(m.getMoney() > threshold)
                {
                    boolean onlyNum = true;
                    for(int i = 0;i < caesarBet.getText().length() && onlyNum;i++)
                    {
                        String t = caesarBet.getText().substring(i,i+1);
                        onlyNum = ((t.equals("0")) || (t.equals("1")) || (t.equals("2")) || (t.equals("3")) || (t.equals("4")) || (t.equals("5")) || (t.equals("6")) || (t.equals("7")) || (t.equals("8")) || (t.equals("9")));
                    }

                    if(onlyNum)
                    {
                        bet = Integer.parseInt(caesarBet.getText());
                        if(bet <= m.getMoney() && bet > 0)
                        {
                            betMultiplier = 2;
                            tries = 3;
                            m.decreaseMoney(bet);
                            m.updateMoneyViewer("Money: $" + m.getMoney());

                            selectedWord = chooseRandomWord();
                            selectedWordCiphered = cipherWord(caesarCipherNum,selectedWord);

                            theWord.setText("The word: " + selectedWordCiphered);
                            amountOfTries.setText("Tries: " + tries);

                            caesarText.setVisible(true);
                            caesarGuess.setVisible(true);
                            directions.setVisible(true);
                            theWord.setVisible(true);
                            amountOfTries.setVisible(true);
                            

                            playButton.setVisible(false);
                            caesarBet.setVisible(false);

                            caesarBet.setText("Enter amount you want to bet...");
                        }
                        else
                        {
                            theWord.setText("Your bet must be within the money you have (excluding 0)");
                            theWord.setVisible(true);
                            caesarBet.setText("Enter amount you want to bet...");
                        }
                    }
                    else
                    {
                        theWord.setText("Your bet must be a number");
                        theWord.setVisible(true);
                        caesarBet.setText("Enter amount you want to bet...");
                    }
                }
                else
                {
                    theWord.setText("You must have at least $1000 to make a bet");
                    theWord.setVisible(true);
                    caesarBet.setText("Enter amount you want to bet...");
                }
            }
        }

        class ClicksListener2 implements ActionListener
        {
            public void actionPerformed(ActionEvent e)
            {
                directions.setVisible(false);

                guess = caesarText.getText();
                guess = guess.toUpperCase();

                if(guess.equalsIgnoreCase(selectedWord))
                {
                    theWord.setText("<html>Congratulations! You got the right word!<br>You have won: $" + (int) (bet * betMultiplier) + "</html>");
                    m.increaseMoney((int) (bet * betMultiplier));
                    m.updateMoneyViewer("Money: $" + m.getMoney());

                    caesarText.setVisible(false);
                    caesarGuess.setVisible(false);
                    directions.setVisible(false);
                    amountOfTries.setVisible(false);

                    theWord.setVisible(true);
                    playButton.setVisible(true);
                    caesarBet.setVisible(true);

                    caesarText.setText("Enter your guess...");
                }
                else if(!(guess.equalsIgnoreCase(selectedWord)))
                {
                    tries--;
                    if(tries > 0)
                    {
                        amountOfTries.setText("<html>Tries: " + tries + "<br>Incorrect however your guess ciphered is: " + cipherWord(caesarCipherNum,guess) + "</html>");
                    }
                    else
                    {
                        theWord.setText("I am sorry but you have run out of tries, thank you for playing!");

                        caesarText.setVisible(false);
                        caesarGuess.setVisible(false);
                        directions.setVisible(false);
                        amountOfTries.setVisible(false);

                        theWord.setVisible(true);
                        playButton.setVisible(true);
                        caesarBet.setVisible(true);

                        tries = 3;
                        caesarText.setText("Enter your guess...");
                        betMultiplier -= 0.33;
                    }
                }
            }
        }

        playButton.addActionListener(new ClicksListener());
        caesarGuess.addActionListener(new ClicksListener2());
    }

    public String chooseRandomWord()
    {
        return words[(int) (Math.random() * words.length)];
    }

    public String cipherWord(int caesarCipher, String s)
    {
        String toReturn = "";

        for(int i = 0;i < s.length();i++)
        {
            int currentPos = -1;
            for(int j = 0;j < alphabet.length;j++)
            {
                if(s.substring(i,i+1).equalsIgnoreCase(alphabet[j]))
                {
                    currentPos = j;
                }
            }

            currentPos += caesarCipher;

            if(currentPos >= alphabet.length)
                currentPos -= alphabet.length;
            
            toReturn += alphabet[currentPos];
        }

        return toReturn;
    }

    public JTextField getCaesarText()
    {
        return caesarText;
    }

    public JTextField getCaesarBet()
    {
        return caesarBet;
    }

    public JButton getPlayButton()
    {
        return playButton;
    }

    public JButton getCaesarGuess()
    {
        return caesarGuess;
    }

    public JLabel getTheWord()
    {
        return theWord;
    }

    public JLabel getDirections()
    {
        return directions;
    }

    public JLabel getAmountOfTries()
    {
        return amountOfTries;
    }

    public void setBoundsOfCaesarText(int x,int y,int w,int h)
    {
        caesarText.setBounds(x,y,w,h);
    }
    
    public void setBoundsOfCaesarBet(int x,int y,int w,int h)
    {
        caesarBet.setBounds(x,y,w,h);
    }

    public void setBoundsOfPlayButton(int x,int y,int w,int h)
    {
        playButton.setBounds(x,y,w,h);
    }

    public void setBoundsOfCaesarGuess(int x,int y,int w,int h)
    {
        caesarGuess.setBounds(x,y,w,h);
    }

    public void setBoundsOfTheWord(int x,int y,int w,int h)
    {
        theWord.setBounds(x,y,w,h);
    }

    public void setBoundsOfDirections(int x,int y,int w,int h)
    {
        directions.setBounds(x,y,w,h);
    }

    public void setBoundsOfAmountOfTries(int x,int y,int w,int h)
    {
        amountOfTries.setBounds(x,y,w,h);
    }
}