
package cl.diinf.managedBeans;

import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
 
import java.io.InputStream;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
 
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
}
