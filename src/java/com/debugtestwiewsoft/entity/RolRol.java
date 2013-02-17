/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.debugtestwiewsoft.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author ALMACEN
 */
@Entity
@Table(name = "rol_rol")
@NamedQueries({
    @NamedQuery(name = "RolRol.findAll", query = "SELECT r FROM RolRol r")})
public class RolRol implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "estado")
    private Boolean estado;
    @Column(name = "tiempo_estado")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tiempoEstado;
    @JoinColumn(name = "rol_padre_id", referencedColumnName = "id")
    @ManyToOne
    private Rol rolPadreId;
    @JoinColumn(name = "rol_hijo_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Rol rolHijoId;

    public RolRol() {
    }

    public RolRol(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Date getTiempoEstado() {
        return tiempoEstado;
    }

    public void setTiempoEstado(Date tiempoEstado) {
        this.tiempoEstado = tiempoEstado;
    }

    public Rol getRolPadreId() {
        return rolPadreId;
    }

    public void setRolPadreId(Rol rolPadreId) {
        this.rolPadreId = rolPadreId;
    }

    public Rol getRolHijoId() {
        return rolHijoId;
    }

    public void setRolHijoId(Rol rolHijoId) {
        this.rolHijoId = rolHijoId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RolRol)) {
            return false;
        }
        RolRol other = (RolRol) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.debugtestwiewsoft.entity.RolRol[ id=" + id + " ]";
    }
    
}
