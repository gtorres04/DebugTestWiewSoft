/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.debugtestwiewsoft.managebean;

import com.debugtestwiewsoft.dao.DaoImpl;
import com.debugtestwiewsoft.businessmodel.DocumentoIdentidadBM;
import com.debugtestwiewsoft.entity.DocumentoIdentidad;
import java.io.Serializable;


import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 * ManageBean DocumentoIdentidadBean.
 * En este ManageBean se controlan todos los eventos correspondientes
 * Al CRUD de la entidad DocumentoIdentidad lansados desde la vista:
 * 1. Registro.
 * 2. Listado.
 * 3. Modificacion.
 * 5. Eliminacion.
 * @author Gerlin Orlando Torres Saavedra.
 */
@ManagedBean
@RequestScoped
public class DocumentoIdentidadMB implements Serializable{
    private DocumentoIdentidad entidad;
    private List<DocumentoIdentidad> entidades;
    /**
     * Constructor DocumentoIdentidadBeanBean (Vacio).
     * Se crea una instancia del ManageBean y se crea un objeto de Tipo DocumentoIdentidadBean, 
     * el cual tiene como objetivo, recibir y devolver los valores de la vista. 
     * Ademas inicializa la lista de los DocumentoIdentidadBean que se han registrado hasta el momento.
     * @author Gerlin Orlando Torres Saavedra.
     */
    public DocumentoIdentidadMB() {
        Log("se crea un objeto DocumentoIdentidadBeanbean");
        entidad=new DocumentoIdentidad();
        getDocumentoIdentidades();
    }

    public DocumentoIdentidad getDocumentoIdentidad() {
        return entidad;
    }

    public void setDocumentoIdentidad(DocumentoIdentidad entidad) {
        this.entidad = entidad;
    }
    /**
     * Metodo getDocumentoIdentidad().
     * Trae de la base de datos los documentos de Identidad que se encuentran almacenados
     * @return Una lista Vacia si no hay ningun DocumentoIdentidad registrado en la base de datos.
     * @author Gerlin Orlando Torres Saavedra.
     */
    public List<DocumentoIdentidad> getDocumentoIdentidades() {
        DocumentoIdentidadBM documentosIdentidadDao=new DocumentoIdentidadBM();
        entidades=documentosIdentidadDao.buscarActivos();
        return entidades;
    }
    /**
     * Metodo insertar().
     * Toma la instancia "entidad" (Global) y la envia para su registro,
     * Y la respuesta del proceso de registro la envia a la vista en un FaceMessage.
     * @author Gerlin Orlando Torres Saavedra.
     */
    public void insertar(){
        Log("METODO INSERTAR DOCUMENTOS IDENTIDAD");
        DaoImpl documentosIdentidadDao=new DocumentoIdentidadBM();
        String mensaje=documentosIdentidadDao.registrar(entidad);
        FacesContext context = FacesContext.getCurrentInstance(); 
        context.addMessage("grwForMensajeConfirmacion",new FacesMessage("REGISTRO DE DOCUMENTOS IDENTIDAD",mensaje));        
    }
    /**
     * Metodo actualizar().
     * Toma la instancia "entidad" (Global), seteada por el metodo @see prepararActualizacion(Integer id), 
     * y la envia para su modificacion, la respuesta del proceso de modificacion la envia a la vista 
     * en un FaceMessage.
     * @see prepararActualizacion(Integer id).
     * @author Gerlin Orlando Torres Saavedra.
     */
    public void actualizar(){
        Log("METODO ACTUALIZAR DOCUMENTOS IDENTIDAD");
        DaoImpl documentosIdentidadDao=new DocumentoIdentidadBM();
        String mensaje=documentosIdentidadDao.actualizar(entidad);
        FacesContext context = FacesContext.getCurrentInstance(); 
        context.addMessage("grwForMensajeConfirmacion",new FacesMessage("ACTUALIZACION DE Documentos Identidad",mensaje));        
    }
    /**
     * Metodo prepararActualizacion(Integer id).
     * Recive el Id del DocumentoIdentidad a modificar y lo consulta frente a la base de datos 
     * obteniendo el objeto DocumentoIdentidad completo, para posteriormente asignarlo a la variable "entidad" (Global), 
     * para que cuando se llame el metodo @see actualizar() el objeto tenga sus valores modificados.
     * @see actualizar() modifica el objeto DocumentoIdentidad.
     * @param id identificados del DocumentoIdentidad a Modificar.
     * @author Gerlin Orlando Torres Saavedra.
     */
    public void prepararActualizacion(Integer id){
        Log("METODO PREPARAR ACTUALIZACION DEL DocumentoIdentidad");
        DocumentoIdentidadBM daoImpl=new DocumentoIdentidadBM();
        entidad=daoImpl.buscarPorId(id);
    }
    /**
     * Metodo eliminar().
     * Toma la instancia "entidad" (Global), seteada por el metodo @see prepararEliminacion(Integer id), 
     * y la envia para su Eliminacion, la respuesta del proceso de eliminacion la envia a la vista 
     * en un FaceMessage. Hay que recalcar que la eliminicion literalmente no se realiza, solo se le 
     * cambia el estado al registro, pero la informacion sigue en la base de datos para futuras inconsistencias.
     * @see prepararEliminacion(Integer id).
     * @author Gerlin Orlando Torres Saavedra.
     */
    public void eliminar(){
        Log("METODO ELIMINAR DocumentoIdentidad");
        DocumentoIdentidadBM daoImpl=new DocumentoIdentidadBM();
        String mensaje=daoImpl.inactivarRegistro(entidad);
        FacesContext context = FacesContext.getCurrentInstance(); 
        context.addMessage("grwForMensajeConfirmacion",new FacesMessage("ELIMINACION DE DOCUMENTO DE IDENTIDAD",mensaje));
    }
    /**
     * Metodo prepararEliminacion(Integer id).
     * Recive el Id del DocumentoIdentidad a modificar y lo consulta frente a la base de datos 
     * obteniendo el objeto DocumentoIdentidad completo, para posteriormente asignarlo a la variable "entidad" (Global), 
     * para que cuando se llame el metodo @see eliminar() el objeto tenga sus valores 
     * completos para su eliminacion.
     * @see eliminar() modifica el objeto DocumentoIdentidad.
     * @param id identificados del DocumentoIdentidad a Modificar.
     * @author Gerlin Orlando Torres Saavedra.
     */
    public void prepararEliminacion(Integer id){
        Log("METODO PREPARAR ELIMINACION DEL DOCUMENTO DE IDENTIDAD");
        DocumentoIdentidadBM daoImpl=new DocumentoIdentidadBM();
        entidad=daoImpl.buscarPorId(id);
        System.out.print("prueba");
    }
    /**
     * Metodo Log(String msn).
     * Tiene como objetivo imprimir banderas durante la ejecucion del programa y lo imprime como si fuera 
     * un WARNING, para resaltarse.
     * @param msn de tipo String, Cadena a imprimir.
     * @author Gerlin Orlando Torres Saavedra.
     */
    public void Log(String msn){
        Logger.getLogger(getClass().getName()).log(Level.WARNING, "<<<<[[[["+msn.toUpperCase()+"]]]]>>>>");
    }    
}