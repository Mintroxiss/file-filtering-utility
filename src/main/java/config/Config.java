package config;

import java.util.ArrayList;
import java.util.List;

public class Config {
    private boolean addMode = false;
    private boolean shortStatMode = false;
    private boolean fullStatMode = false;
    private String path = "";
    private String namePrefix = "";
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getNamePrefix() {
        return namePrefix;
    }

    public void setNamePrefix(String namePrefix) {
        this.namePrefix = namePrefix;
    }
}
