// ----------------------------------------------
// Matthew Bentz, Chase Hopkins, James Haberstroh

import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.lang.reflect.InvocationTargetException;

public class dbUtil {
    private final Connection conn;

    public dbUtil(String dbName, String port, String pass)
            throws SQLException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();

        conn = DriverManager.getConnection("jdbc:mysql://localhost:"
                + port + "/" + dbName + "?" + "user=root&password=" + pass);
    }

    public ResultSet execStatement(String query) throws SQLException {
        Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        return stmt.executeQuery(query);
    }
}