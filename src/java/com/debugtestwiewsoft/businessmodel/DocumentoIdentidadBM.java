/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.debugtestwiewsoft.businessmodel;

import com.debugtestwiewsoft.dao.DaoImpl;
import com.debugtestwiewsoft.entity.DocumentoIdentidad;
import com.debugtestwiewsoft.util.Utileria;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManagerFactory;

/**
 * Clase DocumentoIdentidadBM: Clase Modelo de la Entidad DocumentoIdentidad.
 * En esta Clase Modelo se intermedia el ManageBean "DocumentoIdentidadMB" con toda la logica de negocio 
 * correspondiente a la entidad "DocumentoIdentidad". Esta clase hereda de la Clase Generica "@see DaoImp<T>",
 * herendando metodos como:
 * 1. Registro.
 * 2. Listado.
 * 3. Modificacion.
 * 5. Eliminacion.
 * 6. Entre otros.
 * @author Gerlin Orlando Torres Saavedra
 * @see DaoImpl
 */
public class DocumentoIdentidadBM extends DaoImpl<DocumentoIdentidad> {
    EntityManagerFactory emf;
    /**
     * Constructor DocumentoIdentidadBM (Vacio).
     * Se llama la instancia del @see EntityManagerFactory, y se le envia al contructor de la clase padre, 
     * ya que en ella es donde se realiza toda interaccion entre JPA y la Base de Datos. 
     * Ademas se referencia la instancia @see EntityManagerFactory en una variable global "emf".
     * 
     * @author Gerlin Orlando Torres Saavedra
     * @see EntityManagerFactory
     */
    
    public DocumentoIdentidadBM() {
        super(Utileria.getEntityManagerFactory());
        emf=Utileria.getEntityManagerFactory();
    }
    
    /**
     * Metodo buscarPorId(Integer id).
     * Recibe el Id del DocumentoIdentidad a modificar y los consulta frente a la base de datos (utilizando un 
     * metodo de la clase padre super.buscarPorId(id, "DocumentoIdentidad")) obteniendo el objeto DocumentoIdentidad completo.
     * @param id  ID del DocumentoIdentidad a buscar.
     * @return Una instancia de tipo "DocumentoIdentidad" del "id" especificado como parametro.
     */
    public DocumentoIdentidad buscarPorId(Integer id) {
        return super.buscarPorId(id, "DocumentoIdentidad");
    }
    
    /**
     * Metodo registrar(DocumentoIdentidad entidad). @Override
     * Registra el DocumentoIdentidad en la base de datos.
     * @param entidad referencia de tipo "DocumentoIdentidad". DocumentoIdentidad que se intenta registrar en la base de datos.
     * @return String, una cadena donde se le expecifica el mensaje de exito o fracaso que se obtuvo del registro.
     */
    @Override
    public String registrar(DocumentoIdentidad entidad) {
        entidad.setTiempoEstado(new Date());
        entidad.setEstado(Boolean.TRUE);
        return super.registrar(entidad);
    }
    
    /**
     * Metodo actualizar(DocumentoIdentidad entidad). @Override
     * Modifica el DocumentoIdentidad en la base de datos.
     * @param entidad referencia de tipo "DocumentoIdentidad". DocumentoIdentidad que se intenta modificar en la base de datos.
     * @return String, una cadena donde se le expecifica el mensaje de exito o fracaso que se obtuvo de la modificacion.
     */
    @Override
    public String actualizar(DocumentoIdentidad entidad){
        try {
            DocumentoIdentidad entidad_aux=buscarPorId(entidad.getId(),"DocumentoIdentidad");
            if(entidad_aux.getUsuarioList().isEmpty()){
                entidad.setEstado(Boolean.TRUE);
                entidad.setTiempoEstado(new Date());
                return super.actualizar(entidad); 
            }else{
                return "DocumentoIdentidad a modificar:"+entidad.toString()+". No es permitido Modificar este DocumentoIdentidad, ya que existen usuarios relacionado con este DocumentoIdentidad.";
            }
        } catch (Exception ex) {
            System.out.println("ERROR DESCONOCIDO CONSULTE CON EL SOPORTE TECNICO DE SU PROVEEDOR. Error al Modificar ["+entidad.toString()+"]==>"+ex.getMessage());
            return "ERROR DESCONOCIDO CONSULTE CON EL SOPORTE TECNICO DE SU PROVEEDOR. Error al Modificar ["+entidad.toString()+"]==>"+ex.getMessage();
        }
    }

    /**
     * Metodo eliminar(DocumentoIdentidad entidad). @Override
     * Elimina el DocumentoIdentidad en la base de datos.
     * @param entidad referencia de tipo "DocumentoIdentidad". DocumentoIdentidad que se intenta eliminar en la base de datos.
     * @return String, una cadena donde se le expecifica el mensaje de exito o fracaso que se obtuvo de la eliminacion.
     */
    @Override
    public String eliminar(DocumentoIdentidad entidad) {
        try {
            DocumentoIdentidad entidad_aux=buscarPorId(entidad.getId(),"DocumentoIdentidad");
            if(entidad_aux.getUsuarioList().isEmpty()){
                entidad.setEstado(Boolean.TRUE);
                entidad.setTiempoEstado(new Date());
                return super.eliminar(entidad); 
            }else{
                return "DocumentoIdentidad a eliminar:"+entidad.toString()+". No es permitido Eliminar este DocumentoIdentidad, ya que existen usuarios relacionado con este pais.";
            }
        } catch (Exception ex) {
            return "ERROR DESCONOCIDO CONSULTE CON EL SOPORTE TECNICO DE SU PROVEEDOR. Error al Eliminar ["+entidad.toString()+"]==>"+ex.getMessage();
        }
    }
    
    /**
     * Metodo buscarActivos().
     * Consulta en la base de datos los DocumentoIdentidad que estan activos, es decir, los que en la base de datos
     * se encuentren en un estado TRUE o 1.
     * @return List<DocumentoIdentidad>, una lista de los DocumentoIdentidad que se encuentran activos, es decir,
     * que su estado es TRUE.
     */
    public List<DocumentoIdentidad> buscarActivos() {
        List<DocumentoIdentidad> listaDocumentoIdentidadActivas=super.buscarActivos("DocumentoIdentidad");
        return listaDocumentoIdentidadActivas;
    }
    
    /**
     * Metodo buscarTodos().
     * Consulta en la base de datos todos los DocumentoIdentidad, tando los activos como los inactivos, es decir, 
     * los que el estado sea TRUE o FALSE.
     * @return List<DocumentoIdentidad>, una lista de los DocumentoIdentidad que se encuentran activos e inactivos, es decir, que su estado es TRUE y FALSE.
     */
    public List<DocumentoIdentidad> buscarTodos() {
        List<DocumentoIdentidad> listaDocumentoIdentidadActivas=super.buscarTodos("DocumentoIdentidad");
        return listaDocumentoIdentidadActivas;
    }
   
     /**
     * Metodo inactivarRegistro(DocumentoIdentidad entidad). @Override
     * Modifica el estado al DocumentoIdentidad (Inactivar) siempre y cuando este DocumentoIdentidad no se encuentre relacionado(a) con otro registro activo de otra tabla.
     * @param entidad Referencia de Tipo "DocumentoIdentidad", cuyo DocumentoIdentidad sera el referente para modificar el estado a FALSE.
     * @return String, una cadena donde se le expecifica el mensaje de exito o fracaso que se obtuvo de la inactivación.
     */
    @Override
    public String inactivarRegistro(DocumentoIdentidad entidad) {
        try {
            entidad=buscarPorId(entidad.getId(),"DocumentoIdentidad");
            if(entidad.getUsuarioList().isEmpty()){
                entidad.setEstado(Boolean.FALSE);
                entidad.setTiempoEstado(new Date());
                return super.inactivarRegistro(entidad); 
            }else{
                return "DocumentoIdentidad a eliminar:"+entidad.toString()+". No es permitido Eliminar este DocumentoIdentidad, ya que existen usuarios relacionado con este DocumentoIdentidad.";
            }
        } catch (Exception ex) {
            return "ERROR DESCONOCIDO CONSULTE CON EL SOPORTE TECNICO DE SU PROVEEDOR. Error al Eliminar ["+entidad.toString()+"]==>"+ex.getMessage();
        }   
    }  
}