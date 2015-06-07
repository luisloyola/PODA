
package cl.diinf.managedBeans;
 
import javax.faces.application.FacesMessage;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable; 
import cl.diinf.objetoAprendizaje.LearningObject;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import cl.diinf.sessionBeans.readerXml;
import cl.diinf.sessionBeans.TranslateHtml;
import cl.diinf.util.Compressor;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.NoSuchElementException;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.Part; 
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.apache.commons.io.FileUtils;
/**
 *
 * @author teamPODA
 */
@Named(value = "fileUpload")
@SessionScoped
public class ObjectManaged implements Serializable{
   
    private Part file;
    private StreamedContent zipFile;
    private StreamedContent scormFile;
    private String fileContent;    
    private String code_html;
    private String error_Message;
    private String error_Message_Donwload;
    private String generatedZipPath;
    private String OA_Name;

    public void setZipFile(StreamedContent file) {
        this.zipFile = file;
    } 
    
    public void setScormFile(StreamedContent file) {
        this.scormFile = file;
    }        
    
    public String getGeneratedZipPath() {
        return generatedZipPath;
    }

    public void setGeneratedZipPath(String generatedZipPath) {
        this.generatedZipPath = generatedZipPath;
    }
       
    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }

    public String getFileContent() {
        return fileContent;
    }

    public void setFileContent(String fileContent) {
        this.fileContent = fileContent;
    }

    public String getCode_html() {
        return code_html;
    }

    public void setCode_html(String code_html) {
        this.code_html = code_html;
    }

    public String getError_Message() {
        return error_Message;
    }

    public void setError_Message(String error_Message) {
        this.error_Message = error_Message;
    }

    public String getError_Message_Donwload() {
        return error_Message_Donwload;
    }

    public void setError_Message_Donwload(String error_Message_Donwload) {
        this.error_Message_Donwload = error_Message_Donwload;
    } 
    /**
     * Cambia el contenido del la variable privada code_html para ser mostrada
     * como objeto de aprendizaje.
     * @throws IOException 
     */
    public void upload() throws IOException {
        try {
            if(!file.equals(null)){            
                Scanner scan = new Scanner(file.getInputStream());
                try{
                    fileContent = scan.useDelimiter("\\A").next();
                    error_Message = null;
                }
                catch(NoSuchElementException e){
                    /*Archivo vacío*/
                    code_html = null;
                    error_Message = "El archivo ingresado se encuentra vacío.";
                }
            }
            else{
                fileContent = "Error: debe ingresar un archivo";
            }
        } catch (IOException e) {
            error_Message = "Archivo inválido";
        }
        if (fileContent != null && !fileContent.isEmpty()){
           
            readerXml nuevoOAR = new readerXml();
            
            nuevoOAR.setFileContent(fileContent);
            
            nuevoOAR.AppendDTD();
            
            List<LearningObject> OA_List = nuevoOAR.readOA();                        
            
            if(OA_List.size() > 0){
                
                TranslateHtml OA_translate = new TranslateHtml();            

                error_Message = "Su objeto de aprendizaje ha sido creado correctamente";
                code_html = OA_translate.writeHtml(OA_List.get(0));                  
               
                if(OA_translate.getTranslateError().equals("NO_AUDIO")){
                    code_html = null;
                    error_Message = "No se ha podido conectar con el servidor de audio.";
                }
                
                if(OA_translate.getTranslateError().equals("NO_MEDIA")){
                    code_html = null;
                    error_Message = "No se ha podido conectar con el servidor de medios (imagen no encontrada).";
                }
                
                this.OA_Name = OA_List.get(0).getTitle();
                //prepareDownload();
                try{
                    FacesContext contex = FacesContext.getCurrentInstance();
                    if(error_Message.equals("Su objeto de aprendizaje ha sido creado correctamente")){
                        
                        contex.getExternalContext().redirect("/PODA-web/objeto_aprendizaje.xhtml");
                    }
                    else{
                        contex.getExternalContext().redirect("/PODA-web/");
                    }
                }catch(Exception e){
                    System.err.println(e);
                }
                
            }
            else{
                /*Archivo con errores o inválido.*/
                code_html = null;
                error_Message = nuevoOAR.getParsingError();
            }     
        }        
    }
    
    public void show_message(){
            
        if(this.error_Message != null){
            
            if("Su objeto de aprendizaje ha sido creado correctamente".equals(this.error_Message))
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", this.getError_Message()));
            else
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", this.getError_Message()));
        }        
    }    
    public void reset_message(){
        this.error_Message = null;
    }    
    
    /**
     * 
     * @return Devuelve el archivo .Zip generado.
     * @throws java.io.IOException
     */
    public File prepareDownload() throws IOException{
        
        String folderName = String.valueOf(Math.random()*100000+1);
        String folderPath;
        File newDirectory = new File(folderName);
        if(!newDirectory.exists()){
            try{
                newDirectory.mkdir();
                folderPath = newDirectory.getAbsolutePath();
                    //System.out.println(folderPath);
                File createdOA = new File(folderPath+"/index.html");
                try{
                    FileWriter fw = new FileWriter(createdOA);
                    fw.write(code_html);
                    fw.close();
                    // /standalone/deployments/PODA-web-1.0.war/resources
                    File source = new File("../standalone/deployments/PODA-ear-1.0.ear/PODA-web-1.0.war/resources");
                    File target = new File(folderPath+"/resources");
                    this.copyDirectory(source,target);
                    Compressor compress = new Compressor();
                    compress.setInputPath(folderPath);
                    compress.setOutputPath(folderName+".zip");
                    compress.zipIt();
                    FileUtils.deleteDirectory(new File(folderPath));
                    
                }
                catch(IOException e){
                    this.error_Message_Donwload = "Falló la descarga. Reintente, por favor.";
                    FileUtils.deleteDirectory(new File(folderPath));
                }
            }
            catch(SecurityException ex){
                this.error_Message_Donwload = "Falló la descarga. Reintente, por favor.";
                newDirectory.delete();
            }
        }   
        
        File returnFile = new File(folderName+".zip");
        return returnFile;
        
    }
    
    public File prepareScormDownload() throws IOException{
        String folderName = String.valueOf(Math.random()*100000+1);
        String folderPath;
        File newDirectory = new File(folderName);
        if(!newDirectory.exists()){
            try{
                newDirectory.mkdir();
                folderPath = newDirectory.getAbsolutePath();
                File createdOA = new File(folderPath+"/index.html");
                try{
                    FileWriter fw = new FileWriter(createdOA);
                    fw.write(code_html);
                    fw.close();
                    File source = new File("../standalone/deployments/PODA-ear-1.0.ear/PODA-web-1.0.war/resources");
                    File target = new File(folderPath+"/resources");
                    this.copyDirectory(source,target);
                    
                    File scormSource = new File("../standalone/deployments/PODA-ear-1.0.ear/PODA-web-1.0.war/Scorm");
                    File scormTarget = new File(folderPath);
                    this.copyDirectory(scormSource, scormTarget);
                    Compressor compress = new Compressor();
                    compress.setInputPath(folderPath);
                    compress.setOutputPath("SCORM-"+folderName+".zip");
                    compress.zipIt();                         
                    FileUtils.deleteDirectory(new File(folderPath));
                }
                catch(IOException e){
                    this.error_Message_Donwload = "Falló la descarga. Reintente, por favor.";
                    FileUtils.deleteDirectory(new File(folderPath));
                }
            }
            catch(SecurityException ex){
                this.error_Message_Donwload = "Falló la descarga. Reintente, por favor.";
                newDirectory.delete();
            }
        }   
        return new File("SCORM-"+folderName+".zip");
    }    
    
    /**
     * 
     * @return Stream para permitir la descarga
     * @throws FileNotFoundException 
     */
    public StreamedContent getZipFile() throws FileNotFoundException, IOException {
        File temp = this.prepareDownload();
        InputStream stream = new FileInputStream(temp);
        this.zipFile = new DefaultStreamedContent(stream, "application/zip", "PODA-"+this.OA_Name+".zip");    
        temp.delete();
        return this.zipFile;
    }
    
    public StreamedContent getScormFile() throws FileNotFoundException, IOException {
        File temp = this.prepareScormDownload();
        InputStream stream = new FileInputStream(temp);
        this.scormFile = new DefaultStreamedContent(stream, "application/zip", "PODA-SCORM-"+this.OA_Name+".zip");    
        temp.delete();
        return this.scormFile; 
    }    
    
    public void copyDirectory(File sourceLocation , File targetLocation)
    throws IOException {
        
        if (sourceLocation.isDirectory()) {
            if (!targetLocation.exists()) {
                targetLocation.mkdir();
            }
            
            String[] children = sourceLocation.list();
            for (int i=0; i<children.length; i++) {
                copyDirectory(new File(sourceLocation, children[i]),
                        new File(targetLocation, children[i]));
            }
        } else {
            
            InputStream in = new FileInputStream(sourceLocation);
            OutputStream out = new FileOutputStream(targetLocation);
            
            // Copy the bits from instream to outstream
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            in.close();
            out.close();
        }
    }            
}
