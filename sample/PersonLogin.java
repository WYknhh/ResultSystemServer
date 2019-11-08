package sample;

import org.json.JSONObject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PersonLogin {

    public static int personID=-1;
    //private static int personKind;

    public static String login(JSONObject root) throws SQLException, ClassNotFoundException {
        String id = root.getString("id");
        String account = root.getString("account");
        String password = root.getString("password");
        String tableName = "";
        //int personID=-1;
        //int personKind;

        if (id.equals("student")) {
            tableName = "studentpassword";

        }
        if (id.equals("teacher")) {
            tableName = "teacherpassword";

        }
        if (id.equals("admin")) {
            tableName = "adminpassword";

        }
        String sql = "select username,password from " + tableName;
        Connection connection = SQLConnection.CONN();
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            if (resultSet.getString("username").equals(account)) {
                if (resultSet.getString("password").equals(password)) {
                    personID=resultSet.getInt("id");
                    return "OK"+id;
                }
            }
        }
        return "Failed";

    }
}
