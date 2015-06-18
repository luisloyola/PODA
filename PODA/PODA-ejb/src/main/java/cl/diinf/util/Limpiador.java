
package cl.diinf.util;


import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;

/**
 * 
 * @author teban
 */
public class Limpiador extends Thread {
    private final String Path;
    private final int minutes;
    
    /**
     * Borra el directorio PATH a los n minutos de ser generado.
     * @param Path Directorio a borrar.
     * @param minutes En cuantos minutos más borrarlo.
     */
    public Limpiador(String Path,int minutes){
        this.Path = Path;
        this.minutes = minutes;
    }
    
    public void DeleteMedia() throws IOException{
        FileUtils.deleteDirectory(new File(Path));
    }
    
    @Override
    public void run(){
        try {
            Thread.sleep(60000*minutes);
            try {
                this.DeleteMedia();
            } catch (IOException ex) {
                System.out.println("Imposibilidad de borrar, realícelo manualmente. \n"+ex.getLocalizedMessage());
            }
        } catch (InterruptedException ex) {
            try {
                System.out.println("No pudo inicializarse el temporizador: \n"+ex.getLocalizedMessage());
                this.DeleteMedia();
            } catch (IOException ex1) {
                System.out.println("Imposibilidad de borrar, realícelo manualmente. \n"+ex1.getLocalizedMessage());
            }
        }
    }
    
}
