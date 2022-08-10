// ----------------------------------------------
// Matthew Bentz, Chase Hopkins, James Haberstroh

import java.awt.*;
import javax.swing.*;
import java.util.List;
import java.awt.event.*;
import java.sql.ResultSet;
import javax.swing.table.*;
import java.util.ArrayList;
import java.sql.SQLException;
import java.sql.ResultSetMetaData;

public class gui extends JFrame implements ActionListener {

    private dbUtil dbInter = null;
    private ResultSet dbResults = null;
    private ResultSetMetaData dbMeta = null;

    private JPanel mainPanel = new JPanel();
    private JPanel titlePanel = new JPanel();
    private JPanel inputPanel = new JPanel();
    private JPanel inputSubmitPanel = new JPanel();
    private JPanel tableLabelPanel = new JPanel();

    private Font titleFont = new Font(Font.SANS_SERIF, Font.BOLD, 35);
    private Font labelFont = new Font(Font.SANS_SERIF, Font.BOLD,18);
    private Font buttonFont = new Font(Font.SANS_SERIF,Font.BOLD,14);
    private Font componentFont = new Font(Font.SANS_SERIF,Font.PLAIN,17);


    private JLabel Header = new JLabel("");
    private JComboBox<String> tableSelect;
    private JLabel inputLabel = new JLabel("");
    private JTextArea inputField = new JTextArea();
    private JButton inputClear = new JButton("Clear");
    private JButton inputSubmit = new JButton("Submit");
    private JTable ResultTable = null;
    private JLabel tableLabel = new JLabel("");
    private JScrollPane tablePane = new JScrollPane(ResultTable);

    gui(dbUtil exec) {

        super("Final Project");
        dbInter = exec;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        setLocation(60,60);
        setLayout(new FlowLayout());

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.LINE_AXIS));
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.PAGE_AXIS));
        inputSubmitPanel.setLayout(new BoxLayout(inputSubmitPanel, BoxLayout.LINE_AXIS));

        tableLabelPanel.setLayout(new FlowLayout());
        Header.setFont(titleFont);
        titlePanel.add(Header);
        titlePanel.add(Box.createRigidArea(new Dimension(80, 0)));

        String[] dbTables = getAllTables();
        tableSelect = new JComboBox<>(dbTables);
        tableSelect.setFont(componentFont);

        tableSelect.addActionListener(this);
        titlePanel.add(tableSelect);

        inputLabel.setFont(labelFont);
        inputPanel.add(inputLabel);
        inputField.setMinimumSize(new Dimension(80,100));
        inputField.setPreferredSize(new Dimension(80, 100));
        inputField.setLineWrap(true);
        inputField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        inputPanel.add(inputField);
        inputClear.setFont(buttonFont);
        inputClear.addActionListener(this);
        inputSubmit.setFont(buttonFont);
        inputSubmit.addActionListener(this);
        inputSubmitPanel.add(inputClear);
        inputSubmitPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        inputSubmitPanel.add(inputSubmit);


        tableLabel.setFont(labelFont);
        tableLabelPanel.add(tableLabel);
        mainPanel.add(titlePanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(tableLabelPanel);

        tablePane.setPreferredSize(new Dimension(700, 200));
        tablePane.setMaximumSize(new Dimension(700, 300));

        try {
            dbResults = dbInter.execStatement("SELECT * FROM " + dbTables[0]);
            createTable(dbResults);
        } catch (SQLException e) {
            showErrorMessage(e);
        }
        ResultTable.setFillsViewportHeight(true);
        mainPanel.add(tablePane);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(inputPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(inputSubmitPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        add(Box.createRigidArea(new Dimension(800,20)));
        add(mainPanel);

        setSize(800,600);
    }

    private String[] getAllTables() {
        List<String> resultString = new ArrayList<>();
        try {
            dbResults = dbInter.execStatement("SHOW TABLES");

            while(dbResults.next()) {
                resultString.add(dbResults.getString(1));
            }
        } catch(SQLException e) {
            showErrorMessage(e);
        }

        String[] tables = new String[resultString.size()];
        for(int i=0; i<resultString.size(); i++) {
            tables[i] = resultString.get(i);
        }

        return tables;
    }

    private void createTable(ResultSet rs) {

        // Make a new table model
        DefaultTableModel tableModel = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        try {
            dbMeta = dbResults.getMetaData();

            int columnCount = dbMeta.getColumnCount();
            String[] dbTableHeaders = new String[columnCount];
            for(int i=0; i<columnCount; i++) {
                dbTableHeaders[i] = dbMeta.getColumnName(i+1);
            }

            tableModel.setColumnIdentifiers(dbTableHeaders);
            rs.last();
            int rowCount = rs.getRow();
            rs.first();

            // add the data to the table model
            for(int i=0; i<rowCount; i++) {
                String[] tableRow = new String[columnCount];
                for(int h=0; h<columnCount; h++) {
                    tableRow[h] = rs.getString(dbTableHeaders[h]);
                }
                tableModel.addRow(tableRow);
                rs.next();
            }
        } catch(SQLException er) {
            showErrorMessage(er);
        }
        ResultTable = new JTable(tableModel);
        tablePane.setViewportView(ResultTable);
        tablePane.repaint();
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if(source instanceof JComboBox) {
            // JComboBox Event
            try {
                dbResults = dbInter.execStatement("SELECT * FROM " + ((JComboBox) source).getSelectedItem().toString());

                createTable(dbResults);
            } catch (SQLException er) {
                showErrorMessage(er);
            }

        }else if(source instanceof JButton) {
            // JButton Event


            if(((JButton) source).getText().equals("Submit")) {
                // get the text (query) from the input box and send it to the db
                if(!inputField.getText().equals("")) {
                    try{
                        dbResults = dbInter.execStatement(inputField.getText());
                        createTable(dbResults);
                    } catch (SQLException er) {
                        showErrorMessage(er);
                    }
                }
            } else if(((JButton) source).getText().equals("Clear")) {
                inputField.setText("");
            }
        }
    }

    private void showErrorMessage(SQLException e) {
        JOptionPane.showMessageDialog(this, e.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
    }

    public void display() {
        setVisible(true);
    }
}