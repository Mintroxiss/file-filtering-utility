import java.util.ArrayList;
import java.util.List;

public class Config {
    private boolean addMode = false;
    private boolean shortStatMode = false;
    private boolean fullStatMode = false;
    private final List<String> paths = new ArrayList<>();
    private final List<String> namePrefixes = new ArrayList<>();
    private final List<String> files = new ArrayList<>();

    public boolean isAddMode() {
        return addMode;
    }

    public boolean isShortStatMode() {
        return shortStatMode;
    }

    public boolean isFullStatMode() {
        return fullStatMode;
    }

    public List<String> getPaths() {
        return paths;
    }

    public List<String> getNamePrefixes() {
        return namePrefixes;
    }

    public List<String> getFiles() {
        return files;
    }

    public void setAddMode(boolean addMode) {
        this.addMode = addMode;
    }

    public void setShortStatMode(boolean shortStatMode) {
        this.shortStatMode = shortStatMode;
    }

    public void setFullStatMode(boolean fullStatMode) {
        this.fullStatMode = fullStatMode;
    }
}
