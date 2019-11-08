package sample;

import com.mysql.cj.log.Log;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ChangePassword {
    private static Exception LogException;


    public static void Change_Password(String personKind,int personID,String newPassword) throws Exception {
        String tableName;
        switch(personKind)
        {
            case "student":
                tableName="studentpassword";break;
            case "teacher":
                tableName="teacherpassword";break;
            case "admin":
                tableName="adminpassword";break;
            default:
                throw LogException;
        }
//UPDATE `studentpassword` SET `password`='123' WHERE (`id`='2')
        String sql="UPDATE "+tableName+" SET password="+newPassword+"WHERE(id="+personID;
        Connection connection = SQLConnection.CONN();
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery(sql);


    }
}
