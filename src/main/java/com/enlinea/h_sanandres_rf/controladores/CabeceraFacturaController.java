package com.enlinea.h_sanandres_rf.controladores;

import com.enlinea.h_sanandres_rf.model.CabeceraFactura;
import com.enlinea.h_sanandres_rf.controladores.util.JsfUtil;
import com.enlinea.h_sanandres_rf.controladores.util.JsfUtil.PersistAction;
import com.enlinea.h_sanandres_rf.model.Cliente;
import com.enlinea.h_sanandres_rf.model.DetalleFactura;
import com.enlinea.h_sanandres_rf.negocio.CabeceraFacturaFacade;
import com.enlinea.h_sanandres_rf.negocio.ClienteFacade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.primefaces.context.RequestContext;

@Named("cabeceraFacturaController")
@SessionScoped
public class CabeceraFacturaController implements Serializable {

    @EJB
    private ClienteFacade ejbClienteFacade;
    
    @EJB
    private com.enlinea.h_sanandres_rf.negocio.CabeceraFacturaFacade ejbFacade;
    private List<CabeceraFactura> items = null;
    private CabeceraFactura selected;

    private DetalleFactura detalleSeleccionado;
    
    public CabeceraFacturaController() {
    }

    public CabeceraFactura getSelected() {
        return selected;
    }

    public void setSelected(CabeceraFactura selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private CabeceraFacturaFacade getFacade() {
        return ejbFacade;
    }

    public CabeceraFactura prepareCreate() {
        selected = new CabeceraFactura();
        selected.setClienteIdcliente(new Cliente());
        selected.setDetalleFacturaList(new ArrayList<DetalleFactura>());
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("CabeceraFacturaCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("CabeceraFacturaUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("CabeceraFacturaDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<CabeceraFactura> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public CabeceraFactura getCabeceraFactura(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<CabeceraFactura> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<CabeceraFactura> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = CabeceraFactura.class)
    public static class CabeceraFacturaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CabeceraFacturaController controller = (CabeceraFacturaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "cabeceraFacturaController");
            return controller.getCabeceraFactura(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof CabeceraFactura) {
                CabeceraFactura o = (CabeceraFactura) object;
                return getStringKey(o.getIdcabeceraFactura());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), CabeceraFactura.class.getName()});
                return null;
            }
        }

    }

    public DetalleFactura getDetalleSeleccionado() {
        return detalleSeleccionado;
    }

    public void setDetalleSeleccionado(DetalleFactura detalleSeleccionado) {
        this.detalleSeleccionado = detalleSeleccionado;
    }

    public void cargarCliente(){
        Cliente cliente=ejbClienteFacade.getClientebycedula(selected.getClienteIdcliente().getIdentificacion());
        if (cliente==null){
            System.out.println(selected.getClienteIdcliente());
            RequestContext.getCurrentInstance().execute("PF('ClienteCreateDialog').show()");
        }else{
            selected.setClienteIdcliente(cliente);
        }
        
    }
    
    public void crearCliente(){
    if (ejbClienteFacade.getClientebycedula(selected.getClienteIdcliente().getIdentificacion())!=null){
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Ya exste un cliente registrado con ese numero de identificacion");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return;
        }
            
        //if (!this.validadorDeCedula(selected.getClienteIdcliente().getIdentificacion())){
        //        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "La cedula ingresada es incorrecta");
        //        FacesContext.getCurrentInstance().addMessage(null, msg);
        //        return;
        //}
  
        ejbClienteFacade.create(selected.getClienteIdcliente());
        RequestContext.getCurrentInstance().execute("ClienteCreateDialog.hide()");
    }
    
    private boolean validadorDeCedula(String cedula) {
        boolean cedulaCorrecta = false;

        try {

            if (cedula.length() == 10) // ConstantesApp.LongitudCedula
            {
                int tercerDigito = Integer.parseInt(cedula.substring(2, 3));
                if (tercerDigito < 6) {
// Coeficientes de validación cédula
// El decimo digito se lo considera dígito verificador
                    int[] coefValCedula = {2, 1, 2, 1, 2, 1, 2, 1, 2};
                    int verificador = Integer.parseInt(cedula.substring(9, 10));
                    int suma = 0;
                    int digito = 0;
                    for (int i = 0; i < (cedula.length() - 1); i++) {
                        digito = Integer.parseInt(cedula.substring(i, i + 1)) * coefValCedula[i];
                        suma += ((digito % 10) + (digito / 10));
                    }

                    if ((suma % 10 == 0) && (suma % 10 == verificador)) {
                        cedulaCorrecta = true;
                    } else if ((10 - (suma % 10)) == verificador) {
                        cedulaCorrecta = true;
                    } else {
                        cedulaCorrecta = false;
                    }
                } else {
                    cedulaCorrecta = false;
                }
            } else {
                cedulaCorrecta = false;
            }
        } catch (NumberFormatException nfe) {
            cedulaCorrecta = false;
        } catch (Exception err) {
            cedulaCorrecta = false;
        }

        if (!cedulaCorrecta) {
        }
        return cedulaCorrecta;
    }
}
