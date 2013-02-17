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
@Table(name = "privilegio_rol")
@NamedQueries({
    @NamedQuery(name = "PrivilegioRol.findAll", query = "SELECT p FROM PrivilegioRol p")})
public class PrivilegioRol implements Serializable {
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
    @JoinColumn(name = "rol_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Rol rolId;
    @JoinColumn(name = "privilegio_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Privilegio privilegioId;

    public PrivilegioRol() {
    }

    public PrivilegioRol(Integer id) {
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

    public Rol getRolId() {
        return rolId;
    }

    public void setRolId(Rol rolId) {
        this.rolId = rolId;
    }

    public Privilegio getPrivilegioId() {
        return privilegioId;
    }

    public void setPrivilegioId(Privilegio privilegioId) {
        this.privilegioId = privilegioId;
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
        if (!(object instanceof PrivilegioRol)) {
            return false;
        }
        PrivilegioRol other = (PrivilegioRol) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.debugtestwiewsoft.entity.PrivilegioRol[ id=" + id + " ]";
    }
    
}
