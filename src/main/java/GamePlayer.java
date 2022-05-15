import java.io.IOException;

public interface GamePlayer {
    public void startTheGame(String fileNamePath, boolean isFirstGame);
    public void continueTheGame(String fileNamePath) throws IOException;
}
