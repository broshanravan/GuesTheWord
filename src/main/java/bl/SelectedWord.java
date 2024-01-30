package bl;

public class SelectedWord {
    String encrypted;
    String referenceWord;

    public SelectedWord(String encrypted, String referenceWord) {
        this.encrypted = encrypted;
        this.referenceWord = referenceWord;
    }

    public String getEncrypted() {
        return encrypted;
    }

    public void setEncrypted(String encrypted) {
        this.encrypted = encrypted;
    }

    public String getReferenceWord() {
        return referenceWord;
    }

    public void setReferenceWord(String referenceWord) {
        this.referenceWord = referenceWord;
    }
}
