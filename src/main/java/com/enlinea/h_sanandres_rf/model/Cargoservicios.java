/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.enlinea.h_sanandres_rf.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author eriksigcha
 */
@Entity
@Table(name = "cargoservicios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cargoservicios.findAll", query = "SELECT c FROM Cargoservicios c"),
    @NamedQuery(name = "Cargoservicios.findByIdcargoservicios", query = "SELECT c FROM Cargoservicios c WHERE c.idcargoservicios = :idcargoservicios"),
    @NamedQuery(name = "Cargoservicios.findByNombre", query = "SELECT c FROM Cargoservicios c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "Cargoservicios.findByPorcentaje", query = "SELECT c FROM Cargoservicios c WHERE c.porcentaje = :porcentaje")})
public class Cargoservicios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcargoservicios")
    private Integer idcargoservicios;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "porcentaje")
    private float porcentaje;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cargoserviciosIdcargoservicios")
    private List<CabeceraFactura> cabeceraFacturaList;

    public Cargoservicios() {
    }

    public Cargoservicios(Integer idcargoservicios) {
        this.idcargoservicios = idcargoservicios;
    }

    public Cargoservicios(Integer idcargoservicios, String nombre, float porcentaje) {
        this.idcargoservicios = idcargoservicios;
        this.nombre = nombre;
        this.porcentaje = porcentaje;
    }

    public Integer getIdcargoservicios() {
        return idcargoservicios;
    }

    public void setIdcargoservicios(Integer idcargoservicios) {
        this.idcargoservicios = idcargoservicios;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(float porcentaje) {
        this.porcentaje = porcentaje;
    }

    @XmlTransient
    public List<CabeceraFactura> getCabeceraFacturaList() {
        return cabeceraFacturaList;
    }

    public void setCabeceraFacturaList(List<CabeceraFactura> cabeceraFacturaList) {
        this.cabeceraFacturaList = cabeceraFacturaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcargoservicios != null ? idcargoservicios.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cargoservicios)) {
            return false;
        }
        Cargoservicios other = (Cargoservicios) object;
        if ((this.idcargoservicios == null && other.idcargoservicios != null) || (this.idcargoservicios != null && !this.idcargoservicios.equals(other.idcargoservicios))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.enlinea.h_sanandres_rf.model.Cargoservicios[ idcargoservicios=" + idcargoservicios + " ]";
    }
    
}
