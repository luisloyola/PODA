/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.diinf.util;

import static cl.diinf.util.ResourcesDownloader.generatePathForOA;
import java.io.File;
import java.net.MalformedURLException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author luis
 */
public class ResourcesDownloaderTest {
    
    public ResourcesDownloaderTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of DownloadFromURLAsMozilla method, of class ResourcesDownloader.
     */
    @Test
    public void testDownloadFromURLAsMozilla(){
        System.out.println("DownloadFromURLAsMozilla");
        String inURL = "http://i.imgur.com/ypE48A4.png";
        String targetFilePath = generatePathForOA()+"/test/";
        String extension = ".jpg";
        String result = "";
        try{
            result = ResourcesDownloader.DownloadFromURLAsMozilla(inURL, targetFilePath, extension);
        }
        catch(Exception e){
            System.out.println("DownloadFromURLAsMozilla.targetFilePath = " + targetFilePath);
            System.out.println("DownloadFromURLAsMozilla.result = " + result);
            System.out.println("DownloadFromURLAsMozilla.extension = " + extension); 
        }
        File f = new File(targetFilePath + result);
        assertTrue((f.exists() && !f.isDirectory()));
    }
    
        /**
     * Test of DownloadFromURLAsMozilla method, of class ResourcesDownloader.
     * Malformed URL, si falla entonces está bien.
     */
    @Test
    public void testDownloadFromURLAsMozilla_MalformedURL(){
        System.out.println("DownloadFromURLAsMozilla_MalformedURL");
        String inURL = "no_url";
        String targetFilePath = "/../standalone/deployments/PODA-ear-1.0.ear/PODA-web-1.0.war";
        String extension = ".jpg";
        String result = "";
        try{
            result = ResourcesDownloader.DownloadFromURLAsMozilla(inURL, targetFilePath, extension);
        
            File f = new File(targetFilePath + result + extension);
            System.out.println("");
            assertTrue((f.exists() && !f.isDirectory()));
        }
        catch(Exception e){
            assertTrue(true); 
        }
    }

    
    /**
     * Test of downloadFromGoogleTTS method, of class ResourcesDownloader.
     */
    @Test
    public void testDownloadFromGoogleTTS() throws Exception {
        System.out.println("downloadFromGoogleTTS");
        String msg = "Esto es una prueba de sonido para Google Translate TTS";
        String targetFilePath = generatePathForOA();
        String result = "";
        try{
            result = ResourcesDownloader.downloadFromGoogleTTS(msg, targetFilePath);
        }
        catch(Exception e){System.out.println("GoogleTTS = Exception e ");}
        
        System.out.println("GoogleTTS = " + result);
        File f = new File(targetFilePath + result);
        assertTrue((f.exists() && !f.isDirectory()));
    }

    /**
     * Test of generatePathForOA method, of class ResourcesDownloader.
     */
    @Test
    public void testGeneratePathForOA() {
        System.out.println("generatePathForOA");
        String result = ResourcesDownloader.generatePathForOA();
        assertTrue((result.compareTo("")!=0)); //result != ""
    }
    
}
