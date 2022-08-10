import javax.swing.*;
import java.awt.event.*;

public class databaseInput extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField dbPort;
    private JTextField dbName;
    private JPasswordField password;

    public dbUtil dbUtil;

    public databaseInput() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(e -> onOK());

        buttonCancel.addActionListener(e -> onCancel());

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        setLocationRelativeTo(null);
    }

    private void onOK() {
        try {
            String name = dbName.getText().trim();
            String port = dbPort.getText().trim();
            String pass = String.valueOf(password.getPassword());

            dbUtil = new dbUtil(name, port, pass);
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error connecting to the database.");
            System.out.println("Error:  " + e.getMessage());
            return;
        }

        dispose();
    }

    private void onCancel() {
        dispose();
    }
}
