/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.diinf.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 *
 * @author teban
 */
public class Compressor {
    
    private String inputPath;
    private String outputPath;
    private boolean error;
    
    public Compressor(){
        error = false;
    }

    public String getInputPath() {
        return inputPath;
    }

    public void setInputPath(String inputPath) {
        this.inputPath = inputPath;
    }

    public String getOutputPath() {
        return outputPath;
    }

    public void setOutputPath(String outputPath) {
        this.outputPath = outputPath;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }
    
    
    
    public String zipIt(){
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(this.outputPath);
            ZipOutputStream zos = new ZipOutputStream(fos);
            zos.setLevel(9);
            addFolder(zos,this.inputPath,this.inputPath);
            zos.close();
            System.out.println("¡Compresión Exitosa!");
        } 
        catch (Exception ex) {
            
        }
        
        this.error = true;
        return this.outputPath;
        
    }
    
    private static void addFolder(ZipOutputStream zos,String folderName,String baseFolderName)throws Exception{
        File f = new File(folderName);
        if(f.exists()){
 
            if(f.isDirectory()){
                if(!folderName.equalsIgnoreCase(baseFolderName)){
                    String entryName = folderName.substring(baseFolderName.length()+1,folderName.length()) + File.separatorChar;
                    System.out.println("Agregando: " + entryName);
                    ZipEntry ze= new ZipEntry(entryName);
                    zos.putNextEntry(ze);    
                }
                File f2[] = f.listFiles();
                for(int i=0;i<f2.length;i++){
                    addFolder(zos,f2[i].getAbsolutePath(),baseFolderName);    
                }
            }else{
                String entryName = folderName.substring(baseFolderName.length()+1,folderName.length());
                System.out.print("Agregando: " + entryName + "...");
                ZipEntry ze= new ZipEntry(entryName);
                zos.putNextEntry(ze);
                FileInputStream in = new FileInputStream(folderName);
                int len;
                byte buffer[] = new byte[1024];
                while ((len = in.read(buffer)) > 0) {
                    zos.write(buffer, 0, len);
                }
                in.close();
                zos.closeEntry();
                System.out.println("OK!");
 
            }
        }else{
            System.out.println("Archivo o Directorio: " + folderName +" No encontrado.");
        }
 
    }
}    
    

