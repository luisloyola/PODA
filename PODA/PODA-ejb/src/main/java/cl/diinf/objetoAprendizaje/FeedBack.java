/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.diinf.objetoAprendizaje;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


/**
 *
 * @author teban
 */
public class FeedBack {
    private String link;

    public FeedBack(){
        link = "";
    }
    
    
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
    
    
    /**
     * Verifica que el link pertenece a un formulario de google form o de google form usach.
     * @return True si es válido, false en caso contrario.
     */
    public boolean isLinkValid(){
        //System.out.println("URL: " + link);
        boolean flag1 = isLinkValid2("https://docs.google.com/forms/d/");
        boolean flag2 = isLinkValid2("https://docs.google.com/a/usach.cl/forms/d/");
        //System.out.println("flag1 = "+ flag1);
        //System.out.println("flag2 = "+ flag2);
        return (flag1 || flag2);
    }
    
    /**
     * Verifica que el link comienze con el prefijo dado para saber
     * si pertenece a google form o google form usach. Además corrige el link
     * quitandole los sufijos no necesarios para la visualización del formulario.
     * @param URL_prefix prefijo con el cual comparar el link.
     * @return True si es válido, false en caso contrario
     */
    private boolean isLinkValid2(String URL_prefix){
        try{
            /* Crear una conexión */
            URL obj = new URL(link);
            URLConnection conn = obj.openConnection();
            conn.setRequestProperty("User-Agent", "Mozilla/5.0");
            
            /* Verificar que la URL tenga el prefijo */
            int prefix_length = URL_prefix.length();
            
            if(link.length() < prefix_length){ //link más corto que el prefijo
                //System.out.println("error 1: link más corto que el prefijo");
                return false;
            }
            else{//link más largo que el prefijo
                String temp = link.substring(0, prefix_length);
                if(temp.compareToIgnoreCase(URL_prefix)!=0){ //link no contiene el prefijo
                    //System.out.println("error 2: link no contiene el prefijo");
                    return false;
                }
            }
            
            
            /* Eliminar sufijos no deseados */
            repairLink();
            
            /* Verificar que la URL tenga el sufijo */
            String URL_sufix = "/viewform";
            if(link.length() < URL_sufix.length()){//el link es más corto que el sufijo
                //System.out.println("error 3: link más corto que el sufijo");
                return false;
            }
            else{//link más largo que el sufijo
                int n = link.length();
                int sn = URL_sufix.length();
                String temp = link.substring(n-sn,n); //desde n-sufijo hasta el final.
                if(temp.compareToIgnoreCase(URL_sufix)!=0){//el último trozo del link no corresponde al sufijo
                    //System.out.println("error 4: link no contiene el sufijo");
                    return false;
                }
            }
            
            /* Verificar si el link con prefijo y sufijo tiene el ID de un formulario */
            String temp = link.replaceAll(URL_sufix, "");
            temp = temp.replaceAll(URL_prefix, "");
            if(temp.length()<=0){ //eliminando el prefijo y sufijo no queda nada
                //System.out.println("error 5: tiene prefijo y sufijo, pero no tiene el ID del formulario");
            }
            
            //System.out.println("All good: link válido");
            return true;
        }catch (MalformedURLException e){
            //System.out.println("MalformedURLException handled");
            return false;
        }catch (Exception e) {
            //e.printStackTrace();
            //System.out.println("Exception!:");
            return false;
        }
    }
    
    /**
     * Elimina los sufijos no deseados para la correcta visualización del
     * formulario.
     * @return el link corregido.
     */
    public String repairLink(){
        link = link.replaceAll("/edit", "");
        link = link.replaceAll("\\?usp=send_form", "");
        return link;
    }
}
