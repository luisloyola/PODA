
package cl.diinf.managedBeans;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
 
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
import javax.faces.component.html.HtmlInputTextarea;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.facelets.FaceletContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
 
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
/**
 *
 * @author teamPODA
 */
@Named(value = "FileDownload")
@ApplicationScoped
public class FileDownload {

    private StreamedContent file;
    private StreamedContent manual;
    //private HtmlInputTextarea text_area;
    //private String content;
    /*
    FileDownload(){
        content="<objeto></objeto>";
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
    
    public void setText_area(HtmlInputTextarea text_area) {
        this.text_area = text_area;
    }   
    public HtmlInputTextarea getText_area() {
        return text_area;
    }
    */
    public void setFile(StreamedContent file) {
        this.file = file;
    }
    public void setManual(StreamedContent manual) {
        this.manual = manual;
    }      
 
    /**
     * Devuelve un stream para ser capaz de ser descargado.
     * @return StreamedContent file
     */
    public StreamedContent getFile() {
        InputStream stream = ((ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext()).getResourceAsStream("/resources/plantilla_oa.xml");
        file = new DefaultStreamedContent(stream, "text/plain", "plantilla_objeto.xml");
        
        return file;
    }
    
    public StreamedContent getManual() {
        InputStream stream = ((ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext()).getResourceAsStream("/resources/manual.pdf");
        manual = new DefaultStreamedContent(stream, "application/pdf", "Manual de Usuario.pdf");
        
        return manual;
    }
    /*
    public StreamedContent getXml(){               
        
        System.out.println("PORQ!!!");
        
        return null;        
    } */                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               
    /*
    public void onSelect() {
        FacesContext context = FacesContext.getCurrentInstance();
        Map map = context.getExternalContext().getRequestParameterMap();
        content = (String) map.get("areaText");
    }*/
    /*
    public void execute() {
        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String txtProperty = request.getParameter("formtext:comments");        
        System.out.println(txtProperty);        
    }*/
    /*
    public void download() throws IOException {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();

        ec.responseReset(); // Some JSF component library or some Filter might have set some headers in the buffer beforehand. We want to get rid of them, else it may collide.
        ec.setResponseContentType(ec.getMimeType(text_area)); // Check http://www.iana.org/assignments/media-types for all types. Use if necessary ExternalContext#getMimeType() for auto-detection based on filename.
        ec.setResponseContentLength(contentLength); // Set it with the file size. This header is optional. It will work if it's omitted, but the download progress will be unknown.
        ec.setResponseHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\""); // The Save As popup magic is done here. You can give it any file name you want, this only won't work in MSIE, it will use current request URL as file name instead.

        OutputStream output = ec.getResponseOutputStream();
        // Now you can write the InputStream of the file to the above OutputStream the usual way.
        // ...

        fc.responseComplete(); // Important! Otherwise JSF will attempt to render the response which obviously will fail since it's already written with a file and closed.
    }*/
}
