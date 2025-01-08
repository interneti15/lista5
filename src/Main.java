
public class Main {
    private static JObjectsHandler jObjectsHandler;

    public static JObjectsHandler getjObjectsHandler() {
        return jObjectsHandler;
    }

    public static void main(String[] args) {
        Main.jObjectsHandler = new JObjectsHandler();
    }
}