package sample;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentSearch {
    public static ListTest StuSearch()
    {
        try
        {
            //String tableName = "result";
            String sql = "select id,name,Math,Chinese,English from " + Main.resultTableName+"where id";//TODO
            Connection connection = SQLConnection.CONN();
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet resultSet = statement.executeQuery(sql);
            ListTest transferPackage = new ListTest();
            List<Results> tempTransfer = new ArrayList<Results>();

            Results temp=new Results();

            temp.setId(resultSet.getInt("id"));
            temp.setName(resultSet.getString("name"));
            temp.setMath(resultSet.getInt("Math"));
            temp.setChinese(resultSet.getInt("Chinese"));
            temp.setEnglish(resultSet.getInt("English"));

            tempTransfer.add(temp);

            transferPackage.setList(tempTransfer);
            return transferPackage;
        }
        catch (Exception e)
        {
            e.printStackTrace();;
        }
        return new ListTest();
    }


}
