/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.diinf.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

/**
 *
 * @author luis
 */
public class TTSDownloader {
    
    /**
     * Descarga el contenido de una url a un archivo
     * @param downloadUrl URL desde donde descargar
     * @param targetFilePath Ruta donde descargar el archivo
     * @throws MalformedURLException
     * @throws IOException 
     */
    public static void downloadFromUrl(String downloadUrl,
            String targetFilePath) throws MalformedURLException, IOException 
    {
        URL url = new URL(downloadUrl);
        Files.copy(url.openStream(), new File(targetFilePath).toPath(),
                StandardCopyOption.REPLACE_EXISTING);
    }
    
    /**
     * Descarga desde GoogleTTS el texto entregado en msg como un archivo .mp3 en el directorio indicado.
     * @param msg Mensaje a descargar como .mp3
     * @param targetFilePath ruta donde descargar el archivo.
     * @return nombre del archivo.
     * @throws MalformedURLException
     * @throws IOException 
     */
    public static String downloadFromGoogleTTS(String msg, String targetFilePath) 
            throws MalformedURLException, IOException
    { 
        
        /**
         * Crea un Universal Unic ID para el nombre del archivo filename
         * despues agrega el tiempo actual en milisegundos y la extension .mp3
        */
        String fileName = UUID.randomUUID().toString();
        fileName += ("" + System.currentTimeMillis() + ".mp3");
        
        /* URL de google translate*/
        String url_base = "http://translate.google.com/translate_tts?ie=utf-8&tl=es&q=";
        //String url_base = "hasdfasdfasdfttp://translate.google.com/translate_tts?ie=utf-8&tl=es&q=";
        
        /* Formatear el mensaje a enviar*/
        msg = msg.toLowerCase().replaceAll(" ", "+");
        url_base += msg;
        
        /* Crear una conexión con la url haciendose pasar por un navegador para obtener los permisos de acceso */
        URL url = new URL(url_base);
        URLConnection conn = url.openConnection();
        conn.setRequestProperty("User-Agent", "Mozilla/5.0");
        
        /* Crear carpeta si es que no existe*/
        boolean success = (new File(targetFilePath)).mkdirs();
        if (!success) {
            //System.out.println("ERROR CREATING FOLDER");
        } else{
            //System.out.println("FOLDER CREATED");
        }
        
        /* Descargar el archivo*/
        InputStream is = conn.getInputStream();
        Files.copy(is, new File(targetFilePath+fileName).toPath(),
                StandardCopyOption.REPLACE_EXISTING);
        return fileName;
    }
}
