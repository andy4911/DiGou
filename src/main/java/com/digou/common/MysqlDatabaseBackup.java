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

public  class MysqlDatabaseBackup {

            public   static String filePath;
            public static  String fileName;
            public  static void Backup() {
                try {
                    Runtime rt = Runtime.getRuntime();
                    Process child = rt.exec("/usr/local/mysql/bin/mysqldump -h localhost -uroot -proot demo");
                    InputStream in = child.getInputStream();
                    InputStreamReader xx = new InputStreamReader(in, "utf-8");

                    String inStr;
                    StringBuffer sb = new StringBuffer("");
                    String outStr;

                    BufferedReader br = new BufferedReader(xx);
                    while ((inStr = br.readLine()) != null) {
                        sb.append(inStr + "\r\n");
                    }
                    outStr = sb.toString();
                    getfilePath();
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
    public   static void getfilePath()
    {
        long msec = System.currentTimeMillis();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        fileName = dateFormat.format(msec);
        filePath="/usr/java/tomcat/apache-tomcat-9.0.14/webapps/MysqlBackup/"+fileName+".sql";
    }
}
