package mvc;

import tools.Publisher;

import java.io.Serializable;

abstract public class Model extends Publisher implements Serializable {
    private String fileName;
    private Boolean unsavedChanges = false;
    public String getFileName() {
        return fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public void setUnsavedChanges(Boolean value) {
        this.unsavedChanges = value;
    }
    public Boolean getUnsavedChanges() {
        return unsavedChanges;
    }
    public void changed() {
        unsavedChanges = true;
        notifySubscribers();
    }
}
