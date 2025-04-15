package mvc;

import java.io.Serializable;

public abstract class Model extends Publisher implements Serializable {
    private static final long serialVersionUID = 1L;

    boolean unsavedChanges = false;
    String fileName = null;
    public void setUnsavedChanges(boolean b){
        unsavedChanges = b;
    }

    public boolean getUnsavedChanges(){
        return unsavedChanges;
    }

    public void changed(){
        unsavedChanges = true;
        notifySubscribers();
    }

    public String getFileName(){
        return fileName;
    }

    public void setFileName(String s){
        fileName = s;
    }
}
