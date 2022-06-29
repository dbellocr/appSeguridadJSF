/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * Clase que se encarga de mostrar mensajes de error personalizados. 
 * Estos errores se crean desde fase PROCCESS_VALIDATION del ciclo JSF
 * @author Daniel
 */
public class MessageHelper {

    public static void addErrorMessage(String pErrorMessage) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, pErrorMessage, null));
    }

    public static void addSuccessMessage(String pSuccessMessage) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, pSuccessMessage, null));
    }
    
      public static void addWaringMessage(String pSuccessMessage) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, pSuccessMessage, null));
    }
}
