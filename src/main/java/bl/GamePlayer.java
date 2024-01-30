package bl;

import java.io.IOException;

public interface GamePlayer {
    public SelectedWord startTheGame(String fileNamePath, boolean isFirstGame);
    public void continueTheGame(String fileNamePath) throws IOException;
}
