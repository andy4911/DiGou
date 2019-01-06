package com.digou.common;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.*;
import java.text.SimpleDateFormat;

public class MysqlDatabaseBackup {
    /** 
          * Java代码实现MySQL数据库导出 
          *  
          * @author GaoHuanjie 
          * @param hostIP MySQL数据库所在服务器地址IP 
          * @param userName 进入数据库所需要的用户名 
          * @param password 进入数据库所需要的密码 
          * @param savePath 数据库导出文件保存路径 
          * @param fileName 数据库导出文件文件名 
          * @param databaseName 要导出的数据库名 
          * @return 返回true表示导出成功，否则返回false。 
          */

            public   static String filePath;  //文件路径
            public static  String fileName; //文件名
            public void MysqlDatabaseBackup() {
                try {
                    Runtime rt = Runtime.getRuntime();

                    // 调用 调用mysql的安装目录的命令
                    Process child = rt.exec("/usr/local/mysql/bin/mysqldump -h localhost -uroot -proot demo");
                    // 设置导出编码为utf-8。这里必须是utf-8
                    // 把进程执行中的控制台输出信息写入.sql文件，即生成了备份文件。注：如果不对控制台信息进行读出，则会导致进程堵塞无法运行
                    InputStream in = child.getInputStream();// 控制台的输出信息作为输入流
                    InputStreamReader xx = new InputStreamReader(in, "utf-8");
                    // 设置输出流编码为utf-8。这里必须是utf-8，否则从流中读入的是乱码
                    String inStr;
                    StringBuffer sb = new StringBuffer("");
                    String outStr;
                    // 组合控制台输出信息字符串
                    BufferedReader br = new BufferedReader(xx);
                    while ((inStr = br.readLine()) != null) {
                        sb.append(inStr + "\r\n");
                    }
                    outStr = sb.toString();
                    getfilePath();
                    // 要用来做导入用的sql目标文件：
                    FileOutputStream fout = new FileOutputStream(filePath);
                    OutputStreamWriter writer = new OutputStreamWriter(fout, "utf-8");
                    writer.write(outStr);
                    writer.flush();
                    in.close();
                    xx.close();
                    br.close();
                    writer.close();
                    fout.close();

                    System.out.println("");

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
    public   void getfilePath()
    {
        long msec = System.currentTimeMillis();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        fileName = dateFormat.format(msec);
        filePath="/usr/local/MysqlBackup/"+fileName+".sql";
    }
}
