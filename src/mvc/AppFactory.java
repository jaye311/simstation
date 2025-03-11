package mvc;

public interface AppFactory {
    Model makeModel();
    View makeModel(Model m);
    String getTitle();
    String[] getHelp();
    String about();
    String[] getEditCommands();
    Command makeEditCommand(Model m, String type, Object s);
}
