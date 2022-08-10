// ----------------------------------------------
// Matthew Bentz, Chase Hopkins, James Haberstroh

public class App {
    public static void main(String[] args) {
        databaseInput dialog = new databaseInput();
        dialog.pack();
        dialog.setVisible(true);

        gui mainWindow = new gui(dialog.dbUtil);
        mainWindow.display();
    }
}