package com.zc.aussolar.util;

import java.io.File;

public class FileDeleteUtil implements Runnable {
	//目标文件地址  
    private String filePath = "";  
    //执行后过多少时间删除(单位:分)  
    private int flag = 1;
    public FileDeleteUtil(String path) {  
        this.filePath = path;  
    }  
    @Override  
    public void run() {  
    	try {
			Thread.sleep(flag * 60 * 1000);
			deleteDir(new File(filePath));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }  
    public boolean deleteDir(File someFile) {  
        if (!someFile.exists()) {  
            return false;  
        }  
        if (someFile.isDirectory()) {// is a folder  
            File[] files = someFile.listFiles();  
            for (File subFile : files) {  
                boolean isSuccess = deleteDir(subFile);  
                if (!isSuccess) {  
                    return isSuccess;  
                }  
            }  
        } else {// is a regular file  
            boolean isSuccess = someFile.delete();  
            if (!isSuccess) {  
                return isSuccess;  
            }  
        }  
        if (someFile.isDirectory()) {  
            return someFile.delete();  
        } else {  
            return true;  
        }  
    }  
    public String getFilePath() {  
        return filePath;  
    }  
    public void setFilePath(String filePath) {  
        this.filePath = filePath;  
    }  
    public int getFlag() {  
        return flag;  
    }  
    public void setFlag(int flag) {  
        this.flag = flag;  
    }  
}
