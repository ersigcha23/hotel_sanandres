/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.enlinea.h_sanandres_rf.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author eriksigcha
 */
@Entity
@Table(name = "cabecera_factura")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CabeceraFactura.findAll", query = "SELECT c FROM CabeceraFactura c"),
    @NamedQuery(name = "CabeceraFactura.findByIdcabeceraFactura", query = "SELECT c FROM CabeceraFactura c WHERE c.idcabeceraFactura = :idcabeceraFactura"),
    @NamedQuery(name = "CabeceraFactura.findByNumeroFactura", query = "SELECT c FROM CabeceraFactura c WHERE c.numeroFactura = :numeroFactura"),
    @NamedQuery(name = "CabeceraFactura.findByFecha", query = "SELECT c FROM CabeceraFactura c WHERE c.fecha = :fecha"),
    @NamedQuery(name = "CabeceraFactura.findBySubtotal", query = "SELECT c FROM CabeceraFactura c WHERE c.subtotal = :subtotal"),
    @NamedQuery(name = "CabeceraFactura.findByTotal", query = "SELECT c FROM CabeceraFactura c WHERE c.total = :total")})
public class CabeceraFactura implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcabecera_factura")
    private Integer idcabeceraFactura;
    @Size(max = 50)
    @Column(name = "numero_factura")
    private String numeroFactura;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "subtotal")
    private Float subtotal;
    @Column(name = "total")
    private Float total;
    @JoinColumn(name = "cargoservicios_idcargoservicios", referencedColumnName = "idcargoservicios")
    @ManyToOne(optional = false)
    private Cargoservicios cargoserviciosIdcargoservicios;
    @JoinColumn(name = "cliente_idcliente", referencedColumnName = "idcliente")
    @ManyToOne(optional = false)
    private Cliente clienteIdcliente;
    @JoinColumn(name = "descuento_iddescuento", referencedColumnName = "iddescuento")
    @ManyToOne(optional = false)
    private Descuento descuentoIddescuento;
    @JoinColumn(name = "formadepago_idformadepago", referencedColumnName = "idformadepago")
    @ManyToOne(optional = false)
    private Formadepago formadepagoIdformadepago;
    @JoinColumn(name = "impuesto_idimpuesto", referencedColumnName = "idimpuesto")
    @ManyToOne(optional = false)
    private Impuesto impuestoIdimpuesto;
    @JoinColumn(name = "usuario_idusuario", referencedColumnName = "idusuario")
    @ManyToOne(optional = false)
    private Usuario usuarioIdusuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cabeceraFacturaIdcabeceraFactura")
    private List<DetalleFactura> detalleFacturaList;

    public CabeceraFactura() {
    }

    public CabeceraFactura(Integer idcabeceraFactura) {
        this.idcabeceraFactura = idcabeceraFactura;
    }

    public CabeceraFactura(Integer idcabeceraFactura, Date fecha) {
        this.idcabeceraFactura = idcabeceraFactura;
        this.fecha = fecha;
    }

    public Integer getIdcabeceraFactura() {
        return idcabeceraFactura;
    }

    public void setIdcabeceraFactura(Integer idcabeceraFactura) {
        this.idcabeceraFactura = idcabeceraFactura;
    }

    public String getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Float getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Float subtotal) {
        this.subtotal = subtotal;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public Cargoservicios getCargoserviciosIdcargoservicios() {
        return cargoserviciosIdcargoservicios;
    }

    public void setCargoserviciosIdcargoservicios(Cargoservicios cargoserviciosIdcargoservicios) {
        this.cargoserviciosIdcargoservicios = cargoserviciosIdcargoservicios;
    }

    public Cliente getClienteIdcliente() {
        return clienteIdcliente;
    }

    public void setClienteIdcliente(Cliente clienteIdcliente) {
        this.clienteIdcliente = clienteIdcliente;
    }

    public Descuento getDescuentoIddescuento() {
        return descuentoIddescuento;
    }

    public void setDescuentoIddescuento(Descuento descuentoIddescuento) {
        this.descuentoIddescuento = descuentoIddescuento;
    }

    public Formadepago getFormadepagoIdformadepago() {
        return formadepagoIdformadepago;
    }

    public void setFormadepagoIdformadepago(Formadepago formadepagoIdformadepago) {
        this.formadepagoIdformadepago = formadepagoIdformadepago;
    }

    public Impuesto getImpuestoIdimpuesto() {
        return impuestoIdimpuesto;
    }

    public void setImpuestoIdimpuesto(Impuesto impuestoIdimpuesto) {
        this.impuestoIdimpuesto = impuestoIdimpuesto;
    }

    public Usuario getUsuarioIdusuario() {
        return usuarioIdusuario;
    }

    public void setUsuarioIdusuario(Usuario usuarioIdusuario) {
        this.usuarioIdusuario = usuarioIdusuario;
    }

    @XmlTransient
    public List<DetalleFactura> getDetalleFacturaList() {
        return detalleFacturaList;
    }

    public void setDetalleFacturaList(List<DetalleFactura> detalleFacturaList) {
        this.detalleFacturaList = detalleFacturaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcabeceraFactura != null ? idcabeceraFactura.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CabeceraFactura)) {
            return false;
        }
        CabeceraFactura other = (CabeceraFactura) object;
        if ((this.idcabeceraFactura == null && other.idcabeceraFactura != null) || (this.idcabeceraFactura != null && !this.idcabeceraFactura.equals(other.idcabeceraFactura))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.enlinea.h_sanandres_rf.model.CabeceraFactura[ idcabeceraFactura=" + idcabeceraFactura + " ]";
    }
    
}
