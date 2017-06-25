package com.zc.aussolar.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.Writer;
import java.util.Enumeration;
import java.util.Map;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.apache.tools.zip.ZipOutputStream;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class OfficeFileGenerateUtil {
	public static boolean generateFileBySharedString(String resultFileName, String resultSharedStringFileName,
			String excelTemplateFileName, String path, Map<String, Object> map){
		try {
			Writer writer = null;
			writer = new FileWriter(new File(resultSharedStringFileName));
			
			Configuration configuration = new Configuration();
			configuration.setDirectoryForTemplateLoading(new File(path+"resources"+File.separator+"file_templates"+File.separator));  
			Template template = configuration.getTemplate("installinfo_sharedStrings.ftl");
			template.setOutputEncoding("UTF-8");
			template.process(map, writer);
			
			ZipFile zipFile = new ZipFile(new File(excelTemplateFileName));
			Enumeration<? extends ZipEntry> zipEntrys = zipFile.getEntries();
			ZipOutputStream zipout = new ZipOutputStream(new FileOutputStream(resultFileName));
			int len = -1;
			byte[] buffer = new byte[1024];
			while (zipEntrys.hasMoreElements()) {
				ZipEntry next = zipEntrys.nextElement();
				InputStream is = zipFile.getInputStream(next);
				// 把输入流的文件传到输出流中 如果是word/document.xml由我们输入
				zipout.putNextEntry(new ZipEntry(next.toString()));
				if ("xl/sharedStrings.xml".equals(next.toString())) {
					InputStream in = new FileInputStream(resultSharedStringFileName);
					while ((len = in.read(buffer)) != -1) {
						zipout.write(buffer, 0, len);
					}
					in.close();
				}
				else {
					while ((len = is.read(buffer)) != -1) {
						zipout.write(buffer, 0, len);
					}
					is.close();
				}
			}
			zipout.close();
			zipFile.close();
			return true;
        } catch (Exception e) {  
            e.printStackTrace();  
            return false;
        }   
	}
	/** 
     * @desc 将源文件/文件夹生成指定格式的压缩文件,格式zip 
     * @param resourePath 源文件/文件夹 
     * @param targetDirPath  目的压缩文件保存路径 
     * @return void 
     * @throws Exception  
     */  
    public void compressedFile(String resourcesDirPath,String targetDirPath) throws Exception{  
        File resourcesDir = new File(resourcesDirPath);     //源文件  
        File targetDir = new File(targetDirPath);           //目的  
        //如果目的路径不存在，则新建  
        if(!targetDir.exists()){       
            targetDir.mkdirs();    
        }  
          
        String targetFileName = resourcesDir.getName()+".zip";   //目的压缩文件名  
        ZipOutputStream targetZipFile = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(targetDirPath+File.separator+targetFileName)));  
          
        createCompressedFile(targetZipFile, resourcesDir, "");  
        targetZipFile.close();    
    }  
      
    /** 
     * @desc 生成压缩文件。 
     *               如果是文件夹，则使用递归，进行文件遍历、压缩 
     *       如果是文件，直接压缩 
     * @param targetZipFile  输出流 
     * @param resourcesDir  目标文件 
     * @return void 
     * @throws Exception  
     */  
    public void createCompressedFile(ZipOutputStream targetZipFile, File resourcesDir, String path ) throws Exception{  
        //如果当前的是文件夹，则进行进一步处理  
        if(resourcesDir.isDirectory()){
            //得到文件列表信息  
            File[] fileList = resourcesDir.listFiles();
            //将文件夹添加到下一级打包目录  
            if ( !"".equals(path) ) {
            	targetZipFile.putNextEntry(new ZipEntry(path+File.separator));
			}
        	
        	path = (path.length() == 0 ? "" : (path + File.separator));
        	
              
            //循环将文件夹中的文件打包  
            for(int i = 0 ; i < fileList.length ; i++){  
                createCompressedFile(targetZipFile, fileList[i], path+fileList[i].getName());         //递归处理  
            }  
        }  
        else{   //当前的是文件，打包处理  
            //文件输入流  
            FileInputStream resourcesDirInputStream = new FileInputStream(resourcesDir);  
              
            targetZipFile.putNextEntry(new ZipEntry(path));  
            //进行写操作  
            int j =  0;  
            byte[] buffer = new byte[1024];  
            while((j = resourcesDirInputStream.read(buffer)) > 0){  
                targetZipFile.write(buffer,0,j);  
            }  
            //关闭输入流  
            resourcesDirInputStream.close();  
        }  
    }  
}
