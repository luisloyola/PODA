/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.diinf.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


/**
 *
 * @author alex
 */
public class CreateDirectory {
     private File theDir; 
    private File file;

    public File getTheDir() {
        return theDir;
    }

    public void setTheDir(File theDir) {
        this.theDir = theDir;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public static void copyFolder(File src, File dest)
    	throws IOException{
 
    	if(src.isDirectory()){
 
    		//if directory not exists, create it
    		if(!dest.exists()){
    		   dest.mkdir();
    		   System.out.println("Directory copied from " 
                              + src + "  to " + dest);
    		}
 
    		//list all the directory contents
    		String files[] = src.list();
 
    		for (String file : files) {
                     if (file.compareTo("audios")!=0) {
    		   //construct the src and dest file structure
    		   File srcFile = new File(src, file);
    		   File destFile = new File(dest, file);
    		   //recursive copy
    		   copyFolder(srcFile,destFile);
                    }
                }
    	}else{
    		//if file, then copy it
    		//Use bytes stream to support all file types
    		InputStream in = new FileInputStream(src);
    	        OutputStream out = new FileOutputStream(dest); 
 
    	        byte[] buffer = new byte[1024];
 
    	        int length;
    	        //copy the file content in bytes 
    	        while ((length = in.read(buffer)) > 0){
    	    	   out.write(buffer, 0, length);
    	        }
 
    	        in.close();
    	        out.close();
    	        System.out.println("File copied from " + src + " to " + dest);
    	}
    }
    public void createDirectory(String code, String OAName, String OATitle) throws IOException{
        theDir = new File(("../standalone/deployments/PODA-ear-1.0.ear/PODA-web-1.0.war/OADownloads/"+OAName+"/resources/audios/"+OAName));
        String destino = "../standalone/deployments/PODA-ear-1.0.ear/PODA-web-1.0.war/OADownloads/"+OAName;
        File file2 = new File(destino);
        file= new File(file2,OATitle+".html");
        

        FileOutputStream fop = null;

	String content = code;
            
        if (!theDir.exists()) {
            System.out.println("creando directorio");
            boolean result = false;

            try{
                theDir.mkdirs();
                
                file.createNewFile();
                result = true;
            } 
            catch(SecurityException se){
                //handle it
            }        
            if(result) {    
                System.out.println("directorio creado con exito");  
            }
        }
 
	
	fop = new FileOutputStream(file);
 			// if file doesnt exists, then create it
 
			// get the content in bytes
	byte[] contentInBytes = content.getBytes();
 
	fop.write(contentInBytes);
	fop.flush();
	fop.close();
 
	System.out.println("Done");
 

    }
 
}
