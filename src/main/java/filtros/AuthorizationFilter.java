/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package filtros;

import java.io.IOException;
import javax.faces.application.ResourceHandler;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import util.CommonStrings;
import util.MessageHelper;
import util.SessionHelper;

/**
 *
 * @author Daniel
 */
@WebFilter("/faces/*")
public class AuthorizationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest sr, ServletResponse sr1, FilterChain fc) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) sr;
        HttpServletResponse response = (HttpServletResponse) sr1;
        HttpSession sesionActual = request.getSession();
        String loginURL = request.getContextPath() + "/faces/login.xhtml";
        boolean estaLogeado = sesionActual.getAttribute(CommonStrings.USUARIO_LOGUEADO) != null;
        boolean loginRequest = request.getRequestURI().equals(loginURL);
        boolean resourceRequest = request.getRequestURI().startsWith(request.getContextPath() + ResourceHandler.RESOURCE_IDENTIFIER + "/");

        if (estaLogeado || loginRequest || resourceRequest) {
            fc.doFilter(request, response);
        } else {
            sesionActual.setAttribute("MensajeIniciarSesion", CommonStrings.FAVOR_INICIAR_SESION);
            response.sendRedirect(loginURL);
        }
    }
}
