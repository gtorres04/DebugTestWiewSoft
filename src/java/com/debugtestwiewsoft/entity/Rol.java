/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.debugtestwiewsoft.entity;

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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author ALMACEN
 */
@Entity
@Table(name = "rol")
@NamedQueries({
    @NamedQuery(name = "Rol.findAll", query = "SELECT r FROM Rol r")})
public class Rol implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "estado")
    private Boolean estado;
    @Column(name = "tiempo_estado")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tiempoEstado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rolId")
    private List<PrivilegioRol> privilegioRolList;
    @OneToMany(mappedBy = "rolPadreId")
    private List<RolRol> rolRolList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rolHijoId")
    private List<RolRol> rolRolList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rolId")
    private List<Cuenta> cuentaList;

    public Rol() {
    }

    public Rol(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public List<PrivilegioRol> getPrivilegioRolList() {
        return privilegioRolList;
    }

    public void setPrivilegioRolList(List<PrivilegioRol> privilegioRolList) {
        this.privilegioRolList = privilegioRolList;
    }

    public List<RolRol> getRolRolList() {
        return rolRolList;
    }

    public void setRolRolList(List<RolRol> rolRolList) {
        this.rolRolList = rolRolList;
    }

    public List<RolRol> getRolRolList1() {
        return rolRolList1;
    }

    public void setRolRolList1(List<RolRol> rolRolList1) {
        this.rolRolList1 = rolRolList1;
    }

    public List<Cuenta> getCuentaList() {
        return cuentaList;
    }

    public void setCuentaList(List<Cuenta> cuentaList) {
        this.cuentaList = cuentaList;
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
        if (!(object instanceof Rol)) {
            return false;
        }
        Rol other = (Rol) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.debugtestwiewsoft.entity.Rol[ id=" + id + " ]";
    }
    
}
