package sample;

import org.json.JSONObject;

        import java.io.*;
        import java.net.ServerSocket;
        import java.net.Socket;
        import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        method();

    }
    public static String resultTableName="result";


    public static void method() throws IOException{
        //  1.创建一个服务器对ServerSocket对象和指定的端口号要一致.
        ServerSocket serverSocket=new ServerSocket(8888);
        while(true){
            //2.使用ServerSocket对象中的的方法accept,获取到请求的客户端对象Scoket
            // 创建多线程,提高可以同时与多个客户端进行数据的传输,提高效率
            Socket socket =serverSocket.accept();//阻塞(如果没有客户端连接,程序会停止在这个地方)
            new Thread(()->{
                try{
                    //获得客户端Socket对象
                    //给保存的文件设置随机名,避免覆盖

                    //3.使用Socket对象中的方法getInputSteam() 获得输入流
                    String personKind="null";
                    int personID=-1;
                    System.out.println(socket.getInetAddress()+"is connected.\n");
                    InputStream is =socket.getInputStream();
                    OutputStream os=socket.getOutputStream();
                    String message="";
                    BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(is));
                    String lineMessage;
                    while ((lineMessage=bufferedReader.readLine())!=null)
                    {
                        message+=lineMessage;
                    }
                    JSONObject root=new JSONObject(message);
                    String operator=root.getString("operator");

                    if(operator.equals("login")){//登录操作
                        if(PersonLogin.login(root).equals("Failed"))
                        {
                            os.write("Failed".getBytes());
                            os.flush();
                        }
                        else
                        {
                            os.write(PersonLogin.login(root).getBytes());
                            os.flush();
                            personKind=root.getString("id");
                            personID=PersonLogin.personID;

                        }

                    }
                    else if(operator.equals("search"))//搜索操作
                    {
                        ListTest listTest = TeacherSearch.ReplySearch();
                        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                        out.writeObject(listTest);
                        out.flush();
                        out.close();
                    }
                    else if(operator.equals("changepassword"))
                    {
                        ChangePassword.Change_Password(personKind,personID,"123");
                    }

                    is.close();
                    os.close();

                    socket.close();

                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (Exception e){
                    e.printStackTrace();
                }
            }).start();

        }
    }

}
