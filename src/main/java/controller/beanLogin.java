/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package controller;

import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.validation.constraints.NotNull;
import util.CommonStrings;
import util.MessageHelper;
import util.SessionHelper;

/**
 *
 * @author Daniel
 */
@Named(value = "beanLogin")
@ApplicationScoped
public class beanLogin implements Serializable {

    /**
     * Creates a new instance of beanLogin
     */
    @NotNull(message = "El nombre de usuario es requerido")
    private String usuario;
    @NotNull(message = "La contraseña es requerida")
    private String contrasenna;

    public beanLogin() {

    }

    public void onPageLoad() {
        String messageDelContexto = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("MensajeIniciarSesion");
        if (messageDelContexto != null) {
            MessageHelper.addErrorMessage(messageDelContexto);
        }
        // Si el usuario ya está logueado rediríga al index
        if (SessionHelper.getSessionByKey(CommonStrings.USUARIO_LOGUEADO) != null) {
            ExternalContext ec = FacesContext.getCurrentInstance()
                    .getExternalContext();
            try {
                ec.redirect(ec.getRequestContextPath()
                        + "/faces/index.xhtml");
            } catch (IOException e) {
            }
        }
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenna() {
        return contrasenna;
    }

    public void setContrasenna(String contrasenna) {
        this.contrasenna = contrasenna;
    }

    public String validarCredenciales() {
        if (usuario.equals("Maverick") && contrasenna.equals("123")) {
            SessionHelper.addSession(CommonStrings.USUARIO_LOGUEADO, this.getUsuario());
            return "index";
        } else {
            // Se agrega el mensaje de error
            MessageHelper.addErrorMessage(CommonStrings.LOGIN_INCORRECTO);
        }
        return "";
    }

    public String cerrarSesion() {
        SessionHelper.invalidateSesison();
        return "login.xhtml";
    }
}
