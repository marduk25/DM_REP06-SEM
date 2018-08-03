package com.etiquetas.qrsys.rol;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

import com.etiquetas.qrsys.e.model.Usuario;

@Named(value = "permisosBean")
@ViewScoped

public class PermisosBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private MenuModel model;

    Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");

    public PermisosBean() {

    }

    @PostConstruct
    public void init() {
        model = new DefaultMenuModel();
        // Inicia perfil administrador
        if (us.getPerfil().getIdperfil() == 1) {
            // Primer submenu
            DefaultSubMenu primerSubmenu = new DefaultSubMenu("INICIO");

            DefaultMenuItem item = new DefaultMenuItem("Inicio");
            item.setOutcome("/Views/Upload2.jsf");
            item.setIcon("ui-icon-home");
            primerSubmenu.addElement(item);

            model.addElement(primerSubmenu);

            // Segundo submenu
            DefaultSubMenu segundoSubmenu = new DefaultSubMenu("ARCHIVO");

            item = new DefaultMenuItem("Cargar Excel");
            item.setIcon("ui-icon-disk");
            item.setOutcome("/Views/Home.jsf");
            segundoSubmenu.addElement(item);

            model.addElement(segundoSubmenu);

            // Segundo submenu
            DefaultSubMenu septimoSubmenu = new DefaultSubMenu("REGISTRAR SAE");

            item = new DefaultMenuItem("Registrar series");
            item.setIcon("ui-icon-disk");
            item.setOutcome("/Views/Sae.jsf");
            septimoSubmenu.addElement(item);

            item = new DefaultMenuItem("Registrar compra");
            item.setIcon("ui-icon-disk");
            item.setOutcome("/Views/Register.jsf");
            septimoSubmenu.addElement(item);

            model.addElement(septimoSubmenu);

            // Tercer submenu
            DefaultSubMenu tercerSubmenu = new DefaultSubMenu("IMPRIMIR ETIQUETAS");
            item = new DefaultMenuItem("Compras");
            item.setIcon("ui-icon-print");
            item.setOutcome("/Views/Compras.jsf");
            tercerSubmenu.addElement(item);
            
            item = new DefaultMenuItem("Movimientos");
            item.setIcon("ui-icon-print");
            item.setOutcome("/Views/Movimientos.jsf");
            tercerSubmenu.addElement(item);
            
            item = new DefaultMenuItem("Ventas");
            item.setIcon("ui-icon-print");
            item.setOutcome("/Views/Ventas.jsf");
            tercerSubmenu.addElement(item);
            

            model.addElement(tercerSubmenu);



            // Quinto submenu
            DefaultSubMenu sextoSubmenu = new DefaultSubMenu("USUARIOS");
            item = new DefaultMenuItem("Usuarios");
            item.setIcon("ui-icon-disk");
            item.setOutcome("/Views/Home.jsf");
            sextoSubmenu.addElement(item);

            model.addElement(sextoSubmenu);
            // Finaliza perfil administrador

        } else if (us.getPerfil().getIdperfil() == 2) {
            // Primer submenu
            DefaultSubMenu primerSubmenu = new DefaultSubMenu("Inicio");

            DefaultMenuItem item = new DefaultMenuItem("Inicio");
            item.setOutcome("/Views/Home.jsf");
            item.setIcon("ui-icon-home");
            primerSubmenu.addElement(item);

            model.addElement(primerSubmenu);

            // Tercer submenu
            DefaultSubMenu tercerSubmenu = new DefaultSubMenu("Reportes");
            item = new DefaultMenuItem("Viajes concluídos");
            item.setIcon("ui-icon-disk");
            item.setOutcome("/Views/Home.jsf");
            tercerSubmenu.addElement(item);

            model.addElement(tercerSubmenu);

        }

    }

    public MenuModel getModel() {
        return model;
    }

    public void setModel(MenuModel model) {
        this.model = model;
    }

    public void save() {
        addMessage("Success", "Data saved");
    }

    public void update() {
        addMessage("Success", "Data updated");
    }

    public void delete() {
        addMessage("Success", "Data deleted");
    }

    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
