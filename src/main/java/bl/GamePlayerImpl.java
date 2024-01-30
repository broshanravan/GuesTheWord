package bl;

import dl.TextFileWordSelector;
import dl.TextFileWordSelectorImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class GamePlayerImpl implements GamePlayer {


    //static List<String> referenceList = new ArrayList<String>(); //Arrays.asList("Hello","how","have","you*", "been","these", "beautiful", "mornings","my*Starry*word*here","what" );

    private String encrypt(String referenceWord, List<String> decryptedCharacterList){
        String decryptedWord = "";
        for(int i=0 ; i<referenceWord.length(); i++) {
            String characterInWord = String.valueOf(referenceWord.charAt(i));
            if(decryptedCharacterList.contains(characterInWord)){
                decryptedWord += String.valueOf(characterInWord);
            }else{
                decryptedWord += "*";
            }

        }

        return decryptedWord;

    }


    private String getTheRandomReferenceWord(String fileNamePath){
        TextFileWordSelector textFileWordSelector = new TextFileWordSelectorImpl();
        List<String> referenceList = textFileWordSelector.selectRandomCollectionFromFile(fileNamePath);
        int rand = new Random().nextInt(0,referenceList.size() - 1 );
        String selectedWord = referenceList.get(rand).toString();
        return selectedWord;
    }


    public boolean wordContainsTheCharacter(String characterIn, String referenceWord){
        boolean contains = false;
        String[] charactersCollection= referenceWord.split("");
        for(int i =0 ; i < charactersCollection.length ;i++){
            String character = charactersCollection[i];
            if(character.equalsIgnoreCase(characterIn)){
                return(true);
            }
        }
        return contains;
    }

    /**
     * Asking The player to input three attempt for
     * Characters
     * @return
     */
    public SelectedWord startTheGame(String fileNamePath, boolean isFirstGame) {
        List<String> decryptedCharacterList = new ArrayList<String>();
        String encrypted = "";
        String referenceWord = "";
        try {

             referenceWord = getTheRandomReferenceWord(fileNamePath);

             encrypted = encrypt(referenceWord, decryptedCharacterList);
            if (isFirstGame){
                System.out.println("The player needs to guess the word by attempting to guess encrypting letters.\r\n" +
                        "After Three consecutive wrong guesses the player will loos\r\n" +
                        "**********************************************************************************************");
            }

            System.out.println("Guess The word is : " + encrypted);
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(System.in));

            // Reading data using readLine
            int numberOfWrongAttempts = 0;
            int numberOfdecriptedCharacters = 0;
            while(numberOfdecriptedCharacters <= referenceWord.length()) {
                String input = reader.readLine();
                 if (!wordContainsTheCharacter(input, referenceWord)) {
                    decryptedCharacterList.add(input) ;


                    numberOfWrongAttempts++;
                    if (numberOfWrongAttempts == 3) {
                        System.err.println("Sorry, you lose");
                        System.out.println("The word was :" + referenceWord);
                        break;
                    }else{
                        System.err.println("Wrong guess! try again");
                    }
                    System.out.println("Your decrypted word so far is " + encrypted);
                }else {
                     numberOfWrongAttempts = 0;
                     decryptedCharacterList.add(input);
                     decryptedCharacterList.add(input.toUpperCase(Locale.ROOT));
                     encrypted = encrypt(referenceWord, decryptedCharacterList);
                     System.out.println(encrypted);
                     numberOfdecriptedCharacters = getNumberOfdecriptedCharacter(referenceWord, decryptedCharacterList);
                     if(numberOfdecriptedCharacters == referenceWord.length()){
                         System.out.println("Hora! you won");
                         break;
                     }
                 }
            }
            continueTheGame(fileNamePath);

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        SelectedWord SelectedWord = new SelectedWord(encrypted, referenceWord);

        return SelectedWord;

    }
    private int getNumberOfdecriptedCharacter(String referenceWord, List<String>decryptedCharacterList){
        int numberOfDecryptedCharacters = 0;
        referenceWord.toCharArray();
        for(int i=0 ; i<referenceWord.length(); i++) {
            String characterInWord = String.valueOf(referenceWord.charAt(i));
            if (decryptedCharacterList.contains(characterInWord)) {
                numberOfDecryptedCharacters++;
            }
        }
        return numberOfDecryptedCharacters;
    }

    public void continueTheGame(String fileNamePath) throws IOException {
        System.out.println("Would you like to have another go? y/n");
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        String input = reader.readLine();
        List<String> acceptedAnswers = List.of("yes","y", "no", "n");
        while(!acceptedAnswers.contains(input)){
            System.err.println("Wrong option, please try again (y/n)");
            input = reader.readLine();
        }
        if("y".equalsIgnoreCase(input) || "yes".equalsIgnoreCase(input) ){
            startTheGame(fileNamePath,false);
        }else{
            System.out.println("Nice playing with you! see you soon");
        }

    }

    public static void main(String[] args){
        String fileNamePath = "reference.txt";
        GamePlayer gamePlayer = new GamePlayerImpl();
        gamePlayer.startTheGame(fileNamePath,true);

    }
/**************************************************************improvement***********************************************************************************/
    public void play(String referenceWord) throws IOException {
        List<String> decryptedCharacterList = new ArrayList<String>();
        String encrypted = encrypt(referenceWord, decryptedCharacterList);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int numberOfWrongAttempts = 0;
        int numberOfDecryptedCharacters = 0;
        while (numberOfDecryptedCharacters <= referenceWord.length()) {
            String input = reader.readLine();
            if (!wordContainsTheCharacter(input, referenceWord)) {
                decryptedCharacterList.add(input);
                System.err.println("Wrong guess! try again");

                numberOfWrongAttempts++;
                if (numberOfWrongAttempts == 3) {
                    System.err.println("Sorry, you lose");
                    System.out.println("The word was :" + referenceWord);
                    break;
                }
                System.out.println("Your decrypted word so far is " + encrypted);
            } else {
                numberOfWrongAttempts = 0;
                decryptedCharacterList.add(input);
                decryptedCharacterList.add(input.toUpperCase(Locale.ROOT));
                encrypted = encrypt(referenceWord, decryptedCharacterList);
                System.out.println(encrypted);
                numberOfDecryptedCharacters = getNumberOfdecriptedCharacter(referenceWord, decryptedCharacterList);
                if (numberOfDecryptedCharacters == referenceWord.length()) {
                    System.out.println("Hora! you won");
                    break;
                }
            }

        }
    }

}
