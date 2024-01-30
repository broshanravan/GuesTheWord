import bl.GamePlayer;
import bl.GamePlayerImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GuessTheWord extends JFrame {

    GamePlayer gamePlayer = new GamePlayerImpl();


    private int width = 400;
    private int hight = 180;

    JButton startBtn = new JButton("Start");
    JButton tryBtn = new JButton("Try");
    JButton exitBtn = new JButton("Exit");
    JLabel encryptedWordLbl = new JLabel("The word to guess");
    JLabel letterLbl = new JLabel("Insert a letter here");

    String referenceWord = "";
    String errorMessage = " Wrong Guess, Try again";
    String LoosMessage = " Sorry you lost, The word was " + referenceWord;
    String winMessage = " Hooray you won, well done";

    //textFields
    JTextField encryptedWordFld = new JTextField();
    JTextField letterFld = new JTextField();

    public GuessTheWord(){
        setupScreen();
        setVisible(true);
    }

    public void setupScreen(){
        ButtonListener buttonListener = new ButtonListener();
        startBtn.addActionListener(buttonListener);
        tryBtn.addActionListener(buttonListener);
        exitBtn.addActionListener(buttonListener);

        setTitle("Guess The word");
        setResizable(false);
        getContentPane().setBackground(new Color(255, 255, 242));
        this.setLayout(null);
        this.setSize(width, hight);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        getContentPane().add(encryptedWordLbl);
        getContentPane().add(encryptedWordFld);
        encryptedWordFld.setEnabled(false);

        encryptedWordLbl.setBounds(10,10,250,25);
        encryptedWordFld.setBounds(150,10,220,25);

        getContentPane().add(letterLbl);
        getContentPane().add(letterFld);

        getContentPane().add(letterLbl);
        getContentPane().add(letterFld);

        letterLbl.setBounds(10,50,250,25);
        letterFld.setBounds(150,50,20,25);

        getContentPane().add(startBtn);
        startBtn.setBounds(10,100,100,25);

        getContentPane().add(tryBtn);
        tryBtn.setBounds(150,100,100,25);

        getContentPane().add(exitBtn);
        exitBtn.setBounds(280,100,100,25);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                System.exit(0);
            }
        });

    }

    public void populateEncryptedField(){
        String fileNamePath = "reference.txt";
        gamePlayer.startTheGame(fileNamePath, true);

    }

    public void validateInsertedLetter(){

    }

    public class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("Exit")) {
                System.exit(0);
            } else if (e.getActionCommand().equals("Start")) {
                populateEncryptedField();
            } else if (e.getActionCommand().equals("Try")) {
                validateInsertedLetter();
            }
        }
    }

    public static void main(String[] args){
        GuessTheWord guessTheWord = new GuessTheWord();
    }


}
