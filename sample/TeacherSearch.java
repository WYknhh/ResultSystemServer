package sample;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TeacherSearch {
    public static ListTest ReplySearch()//教师搜索
    {
        try{
            String sql="select id,name,Math,Chinese,English from "+Main.resultTableName;
            Connection connection=SQLConnection.CONN();
            Statement statement=connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ResultSet resultSet=statement.executeQuery(sql);
            ListTest transferPackage=new ListTest();
            List<Results> tempTransfer=new ArrayList<Results>();
            while(resultSet.next())
            {
                Results temp=new Results();

                temp.setId(resultSet.getInt("id"));
                temp.setName(resultSet.getString("name"));
                temp.setMath(resultSet.getInt("Math"));
                temp.setChinese(resultSet.getInt("Chinese"));
                temp.setEnglish(resultSet.getInt("English"));

                tempTransfer.add(temp);
                System.out.println(temp.getName());

            }
            transferPackage.setList(tempTransfer);
            return transferPackage;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return new ListTest();

    }
}
