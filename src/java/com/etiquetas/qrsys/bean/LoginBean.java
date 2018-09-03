package com.etiquetas.qrsys.bean;

import java.awt.event.ActionEvent;
import java.io.Serializable;

import javax.faces.application.FacesMessage;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import com.etiquetas.qrsys.dao.UsuarioDao;
import com.etiquetas.qrsys.dao.UsuarioDaoImp;
import com.etiquetas.qrsys.e.model.Usuario;
import javax.inject.Named;

@Named(value = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private Usuario usuario;
    private String nombre;
    private String password;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LoginBean() {
        this.usuario = new Usuario();
    }

    public void login(ActionEvent event) {
        RequestContext context = RequestContext.getCurrentInstance();
        @SuppressWarnings("UnusedAssignment")
        FacesMessage message = null;
        @SuppressWarnings("UnusedAssignment")
        boolean loggedIn = false;
        String ruta = "";

        UsuarioDao uDao = new UsuarioDaoImp();
        this.usuario = uDao.login(this.usuario);

        if (this.usuario != null) {
            loggedIn = true;
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", this.usuario);
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido", this.usuario.getNombre());
            switch (this.usuario.getPerfil().getIdperfil()) {
                case 1:
                    ruta = "/WebAppEtiquetas/Views/Home.jsf";
                    break;
                case 2:
                    ruta = "/WebAppEtiquetas/Views/Sae.jsf";
                    break;
                default:
                    ruta = "/WebAppEtiquetas/Views/Home.jsf";
                    break;
            }

        } else {
            loggedIn = false;

            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "¡Error de sesión!",
                    "Usuario o password incorrectos...");
            FacesContext.getCurrentInstance().addMessage(null, message);
            this.usuario = new Usuario();

        }
        FacesContext.getCurrentInstance().addMessage(null, message);
        context.addCallbackParam("loggedIn", loggedIn);
        context.addCallbackParam("ruta", ruta);
    }

    public void cerrarSesion() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    }

}
