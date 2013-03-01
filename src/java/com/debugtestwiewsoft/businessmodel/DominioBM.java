/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.debugtestwiewsoft.businessmodel;

import com.debugtestwiewsoft.dao.DaoImpl;
import com.debugtestwiewsoft.entity.Dominio;
import com.debugtestwiewsoft.util.Utileria;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManagerFactory;

/**
 * Clase DominioBM: Clase Modelo de la Entidad Dominio.
 * En esta Clase Modelo se intermedia el ManageBean "DominioMB" con toda la logica de negocio 
 * correspondiente a la entidad "Dominio". Esta clase hereda de la Clase Generica "@see DaoImp<T>",
 * herendando metodos como:
 * 1. Registro.
 * 2. Listado.
 * 3. Modificacion.
 * 5. Eliminacion.
 * 6. Entre otros.
 * @author Gerlin Orlando Torres Saavedra
 * @see DaoImpl
 */
public class DominioBM extends DaoImpl<Dominio> {
    EntityManagerFactory emf;
    /**
     * Constructor DominioBM (Vacio).
     * Se llama la instancia del @see EntityManagerFactory, y se le envia al contructor de la clase padre, 
     * ya que en ella es donde se realiza toda interaccion entre JPA y la Base de Datos. 
     * Ademas se referencia la instancia @see EntityManagerFactory en una variable global "emf".
     * 
     * @author Gerlin Orlando Torres Saavedra
     * @see EntityManagerFactory
     */
    
    public DominioBM() {
        super(Utileria.getEntityManagerFactory());
        emf=Utileria.getEntityManagerFactory();
    }
    
    /**
     * Metodo buscarPorId(Integer id).
     * Recibe el Id del Dominio a modificar y los consulta frente a la base de datos (utilizando un 
     * metodo de la clase padre super.buscarPorId(id, "Dominio")) obteniendo el objeto Dominio completo.
     * @param id  ID del Dominio a buscar.
     * @return Una instancia de tipo "Dominio" del "id" especificado como parametro.
     */
    public Dominio buscarPorId(Integer id) {
        return super.buscarPorId(id, "Dominio");
    }
    
    /**
     * Metodo registrar(Dominio entidad). @Override
     * Registra el Dominio en la base de datos.
     * @param entidad referencia de tipo "Dominio". Dominio que se intenta registrar en la base de datos.
     * @return String, una cadena donde se le expecifica el mensaje de exito o fracaso que se obtuvo del registro.
     */
    @Override
    public String registrar(Dominio entidad) {
        entidad.setTiempoEstado(new Date());
        entidad.setEstado(Boolean.TRUE);
        return super.registrar(entidad);
    }
    
    /**
     * Metodo actualizar(Dominio entidad). @Override
     * Modifica el Dominio en la base de datos.
     * @param entidad referencia de tipo "Dominio". Dominio que se intenta modificar en la base de datos.
     * @return String, una cadena donde se le expecifica el mensaje de exito o fracaso que se obtuvo de la modificacion.
     */
    @Override
    public String actualizar(Dominio entidad){
        try {
            Dominio entidad_aux=buscarPorId(entidad.getId(),"Dominio");
            if(entidad_aux.getCuentaList().isEmpty()){
                entidad.setEstado(Boolean.TRUE);
                entidad.setTiempoEstado(new Date());
                return super.actualizar(entidad); 
            }else{
                return "Dominio a modificar:"+entidad.toString()+". No es permitido Modificar este Dominio, ya que existen usuarios relacionado con este Dominio.";
            }
        } catch (Exception ex) {
            System.out.println("ERROR DESCONOCIDO CONSULTE CON EL SOPORTE TECNICO DE SU PROVEEDOR. Error al Modificar ["+entidad.toString()+"]==>"+ex.getMessage());
            return "ERROR DESCONOCIDO CONSULTE CON EL SOPORTE TECNICO DE SU PROVEEDOR. Error al Modificar ["+entidad.toString()+"]==>"+ex.getMessage();
        }
    }

    /**
     * Metodo eliminar(Dominio entidad). @Override
     * Elimina el Dominio en la base de datos.
     * @param entidad referencia de tipo "Dominio". Dominio que se intenta eliminar en la base de datos.
     * @return String, una cadena donde se le expecifica el mensaje de exito o fracaso que se obtuvo de la eliminacion.
     */
    @Override
    public String eliminar(Dominio entidad) {
        try {
            Dominio entidad_aux=buscarPorId(entidad.getId(),"Dominio");
            if(entidad_aux.getCuentaList().isEmpty()){
                entidad.setEstado(Boolean.TRUE);
                entidad.setTiempoEstado(new Date());
                return super.eliminar(entidad); 
            }else{
                return "Dominio a eliminar:"+entidad.toString()+". No es permitido Eliminar este Dominio, ya que existen usuarios relacionado con este pais.";
            }
        } catch (Exception ex) {
            return "ERROR DESCONOCIDO CONSULTE CON EL SOPORTE TECNICO DE SU PROVEEDOR. Error al Eliminar ["+entidad.toString()+"]==>"+ex.getMessage();
        }
    }
    
    /**
     * Metodo buscarActivos().
     * Consulta en la base de datos los Dominio que estan activos, es decir, los que en la base de datos
     * se encuentren en un estado TRUE o 1.
     * @return List<Dominio>, una lista de los Dominio que se encuentran activos, es decir,
     * que su estado es TRUE.
     */
    public List<Dominio> buscarActivos() {
        List<Dominio> listaDominioActivas=super.buscarActivos("Dominio");
        return listaDominioActivas;
    }
    
    /**
     * Metodo buscarTodos().
     * Consulta en la base de datos todos los Dominio, tando los activos como los inactivos, es decir, 
     * los que el estado sea TRUE o FALSE.
     * @return List<Dominio>, una lista de los Dominio que se encuentran activos e inactivos, es decir, que su estado es TRUE y FALSE.
     */
    public List<Dominio> buscarTodos() {
        List<Dominio> listaDominioActivas=super.buscarTodos("Dominio");
        return listaDominioActivas;
    }
   
     /**
     * Metodo inactivarRegistro(Dominio entidad). @Override
     * Modifica el estado al Dominio (Inactivar) siempre y cuando este Dominio no se encuentre relacionado(a) con otro registro activo de otra tabla.
     * @param entidad Referencia de Tipo "Dominio", cuyo Dominio sera el referente para modificar el estado a FALSE.
     * @return String, una cadena donde se le expecifica el mensaje de exito o fracaso que se obtuvo de la inactivación.
     */
    @Override
    public String inactivarRegistro(Dominio entidad) {
        try {
            entidad=buscarPorId(entidad.getId(),"Dominio");
            if(entidad.getCuentaList().isEmpty()){
                entidad.setEstado(Boolean.FALSE);
                entidad.setTiempoEstado(new Date());
                return super.inactivarRegistro(entidad); 
            }else{
                return "Dominio a eliminar:"+entidad.toString()+". No es permitido Eliminar este Dominio, ya que existen usuarios relacionado con este Dominio.";
            }
        } catch (Exception ex) {
            return "ERROR DESCONOCIDO CONSULTE CON EL SOPORTE TECNICO DE SU PROVEEDOR. Error al Eliminar ["+entidad.toString()+"]==>"+ex.getMessage();
        }   
    }  
}