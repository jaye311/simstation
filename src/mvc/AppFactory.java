package mvc;

public interface AppFactory {
    default void makeModel(){}
    default void makeView(){}
    default void getTitle(){}
    default void getHelp(){}
    default void about(){}
    default void getEditCommands(){}
    default void makeEditCommand(String name){}

}
