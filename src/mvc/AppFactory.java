package mvc;

public interface AppFactory {
    default Model makeModel(){
        return null;
    }
    default View makeView(){
        return null;
    }
    default String getTitle(){
        return "";
    }
    default String[] getHelp(){
        return new String[0];
    }
    default String about(){
        return "";
    }
    default String[] getEditCommands(){
        return new String[0];
    }
    default Command makeEditCommand(String name){
        return null;
    }

}
