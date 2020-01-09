/*package com.liang.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class demo {
	static Logger log = LoggerFactory.getLogger(SchedulerTask.class);
	//private static String savePath = "D:/";
	private static String savePath = "/backups/";
	private static String dataZipPath = "";
	private static String dataFilePrefix = "";
	private static String zipName = "d1_database.zip";
	
	public static void demo01() {
		List<String> database = new ArrayList<String>(Arrays.asList("d1_bill","d1_employee","d1_tech"));
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
		String thisTime = df.format(new Date());
		System.out.println(thisTime);// new Date()为获取当前系统时间
		System.out.println(database.size());
		
		dataFilePrefix = savePath+thisTime;
		
		for(String name : database) {
    		try {  
                if (
                	exportDatabaseTool("localhost", "root", "root123456", savePath, thisTime+name+".sql", name)) { 
                	log.info(name+"数据库成功备份！！！");
                } else {  
                	log.info(name+"数据库成功失败！！！");
                }  
            } catch (InterruptedException e) {  
                e.printStackTrace();  
            } 
    	}
		File[] databaseName = new File[database.size()];
		
		for(int i = 0 ;i <database.size();i++) {
			databaseName[i] = new File(dataFilePrefix+database.get(i)+".sql");
		}
		
		//压缩文件
		dataZipPath =savePath+thisTime+"_"+zipName;
		File zipFile = new File(dataZipPath);
		zipFiles(databaseName, zipFile);
		
		//删除原文件
		for (int i=0;i<databaseName.length;i++){
			System.gc();  
			for(int j =0 ;j <10 ; j++) {
				databaseName[i].delete();
			}
		}
		log.info(dataZipPath);
		//发送到ftp
		BackToFtp();
	}
	
	
	
	public static void BackToFtp(){
	       //创建客户端对象
	       FTPClient ftp = new FTPClient();
	       InputStream local=null;
	       try {
	           //连接ftp服务器
	           ftp.connect("144.34.188.26", 21);
	           //登录
	           ftp.login("lzq", "lzq1234..");
	           //设置上传路径
	           String path="/BackupsFile/database";
	           //检查上传路径是否存在 如果不存在返回false
	           boolean flag = ftp.changeWorkingDirectory(path);
	           if(!flag){
	               //创建上传的路径  该方法只能创建一级目录，在这里如果/home/ftpuser存在则可创建image
	               ftp.makeDirectory(path);
	           }
	           //指定上传路径
	           ftp.changeWorkingDirectory(path);
	           
	           //告诉server开通一个端口来传输数据
	           ftp.enterLocalPassiveMode();
	           
	           //指定上传文件的类型  二进制文件
	           ftp.setFileType(FTP.BINARY_FILE_TYPE);
	           //读取本地文件
	           File file = new File(dataZipPath);
	           local = new FileInputStream(file);
	           //第一个参数是文件名
	           ftp.storeFile(file.getName(), local);
	        } catch (SocketException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }finally {
	            try {
	                //关闭文件流
	                local.close();
	                //退出
	                ftp.logout();
	                //断开连接
	                ftp.disconnect();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	   }
	
	
	
	*//** 
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
     *//*  
    public static boolean exportDatabaseTool(String hostIP, String userName, String password, String savePath, String fileName, String databaseName) throws InterruptedException {  
        File saveFile = new File(savePath);  
        if (!saveFile.exists()) {// 如果目录不存在  
            saveFile.mkdirs();// 创建文件夹  
        }  
        if(!savePath.endsWith(File.separator)){  
            savePath = savePath + File.separator;  
        }  
          
        PrintWriter printWriter = null;  
        BufferedReader bufferedReader = null;  
        try {  
            printWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(savePath + fileName), "utf8"));  
            //Process process = Runtime.getRuntime().exec("C:\\Program Files\\MySQL\\MySQL Server 5.6\\bin\\mysqldump -h "+ hostIP +" -u"+ userName +" -p"+ password + " " + databaseName);  
            Process process = Runtime.getRuntime().exec("mysqldump -h "+ hostIP +" -u"+ userName +" -p"+ password + " " + databaseName);  
            
            InputStreamReader inputStreamReader = new InputStreamReader(process.getInputStream(), "utf8");  
            bufferedReader = new BufferedReader(inputStreamReader);  
            String line;  
            while((line = bufferedReader.readLine())!= null){  
                printWriter.println(line);  
            }  
            printWriter.flush();  
            if(process.waitFor() == 0){//0 表示线程正常终止。  
                return true;  
            }  
        }catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            try {  
                if (bufferedReader != null) {  
                    bufferedReader.close();  
                }  
                if (printWriter != null) {  
                    printWriter.close();  
                }  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
        return false;  
    }  
	
    
    public static void zipFiles(File[] srcFiles, File zipFile) {
        // 判断压缩后的文件存在不，不存在则创建
        if (!zipFile.exists()) {
            try {
                zipFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // 创建 FileOutputStream 对象
        FileOutputStream fileOutputStream = null;
        // 创建 ZipOutputStream
        ZipOutputStream zipOutputStream = null;
        // 创建 FileInputStream 对象
        FileInputStream fileInputStream = null;

        try {
            // 实例化 FileOutputStream 对象
            fileOutputStream = new FileOutputStream(zipFile);
            // 实例化 ZipOutputStream 对象
            zipOutputStream = new ZipOutputStream(fileOutputStream);
            // 创建 ZipEntry 对象
            ZipEntry zipEntry = null;
            // 遍历源文件数组
            for (int i = 0; i < srcFiles.length; i++) {
                // 将源文件数组中的当前文件读入 FileInputStream 流中
                fileInputStream = new FileInputStream(srcFiles[i]);
                // 实例化 ZipEntry 对象，源文件数组中的当前文件
                zipEntry = new ZipEntry(srcFiles[i].getName());
                zipOutputStream.putNextEntry(zipEntry);
                // 该变量记录每次真正读的字节个数
                int len;
                // 定义每次读取的字节数组
                byte[] buffer = new byte[1024];
                while ((len = fileInputStream.read(buffer)) > 0) {
                    zipOutputStream.write(buffer, 0, len);
                }
            }
            zipOutputStream.closeEntry();
            zipOutputStream.close();
            fileInputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    
	

}
*/