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
public class ResourcesDownloader {
    
    /**
     * Descarga un recurso desde internet identificandose como Mozilla 5.0
     * ,asignando un nombre único a cada archivo.
     * @param inURL URL del recurso.
     * @param targetFilePath Path donde guardar el recurso.
     * @param extension Extensión del archivo.
     * @return Nombre del archivo descargado
     * @throws MalformedURLException Exception por URL no existente
     * @throws IOException Exception por falla de IO.
     */
    public static String DownloadFromURLAsMozilla(String inURL, String targetFilePath, String extension) throws MalformedURLException, IOException{
        URL url = new URL(inURL);
        URLConnection conn = url.openConnection();
        conn.setRequestProperty("User-Agent", "Mozilla/5.0");
        
        /**
         * Crea un Universal Unic ID para el nombre del archivo filename
         * despues agrega el tiempo actual en milisegundos y la extension .mp3
        */
        String fileName = UUID.randomUUID().toString();
        fileName += ("" + System.currentTimeMillis() + extension);
        
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
    
    
    /**
     *  Descarga el audio generado por GoogleTranslatorTSS
     * asignandole un nombre único a cada archivo.
     * (Utiliza la voz por defecto para Mozilla)
     * @param msg Texto a convertir en voz
     * @param targetFilePath Path donde descargar el archivo
     * @return Nombre del archivo generado
     * @throws MalformedURLException
     * @throws IOException 
     */
    public static String downloadFromGoogleTTS(String msg, String targetFilePath) 
            throws MalformedURLException, IOException
    { 
        /* URL de google translate*/
        String url_base = "http://translate.google.com/translate_tts?ie=utf-8&tl=es&q=";
        //String url_base = "hasdfasdfasdfttp://translate.google.com/translate_tts?ie=utf-8&tl=es&q=";
        
        /* Formatear el mensaje a enviar*/
        msg = msg.toLowerCase().replaceAll(" ", "+");
        if(msg.length()>100){
            msg = msg.substring(0, 100);
        }
        url_base += msg;
        return DownloadFromURLAsMozilla(url_base, targetFilePath, ".mp3");
    }
    
    /**
     * Busca la ruta del deply de la app en wildfly
     * @return ruta del del OA
     */
    public static String generatePathForOA(){
        String OAPath = new File("").getAbsolutePath(); // Wildfly/bin
        OAPath = OAPath.replaceAll("bin", "");
        OAPath += ("/standalone/deployments/PODA-ear-1.0.ear/PODA-web-1.0.war");
        OAPath = OAPath.replaceAll("localhost:8080", "");
        return OAPath;
    }
}
