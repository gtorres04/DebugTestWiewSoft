/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.debugtestwiewsoft.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 * Clase PaisesDaoImpl: Clase Modelo de la Entidad Paises.
 * Esta es una Clase Generica donde se realizan los procesos que son factor comun de las entidades. 
 * Esta clase implementa de la Clase Generica "Dao<T>"
 * 1. Registro.
 * 2. Listado.
 * 3. Modificacion.
 * 5. Eliminacion.
 * 6. Entre otros.
 * @author Gerlin Orlando Torres Saavedra
 * @see Dao Clase donde se definen los procesos que toda entidad debe realizar.
 */
public class DaoImpl<T> implements Dao<T>{

    private EntityManagerFactory emf;
    /**
     * Constructor DaoImpl(EntityManagerFactory emf).
     * Se inicializa el EntityManagerFactory.
     * @param emf Parametro de tipo EntityManagerFactory.
     * @see EntityManagerFactory
     */
    public DaoImpl(EntityManagerFactory emf) {
        this.emf=emf;
    }
    /**
     * Metodo registrar(T entidad).
     * Registra la entidad en la base de datos.
     * @param entidad referencia generica.
     * @return String, una cadena donde se le expecifica el mensaje de exito o fracaso que se obtuvo del registro.
     */
    public String registrar(T entidad) {
        EntityManager em;
        em=emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(entidad);
            em.getTransaction().commit();
            em.close();
            return "Fue Agregado Exitosamente...!";
        }catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("Error en Insertar ["+entidad.toString()+"]==>"+e.getMessage());
            return "Error en Insertar ["+entidad.toString()+"]==>"+e.getMessage();
            
        }
    }
    /**
     * Metodo actualizar(T entidad).
     * Modifica la entidad en la base de datos.
     * @param entidad referencia generica.
     * @return String, una cadena donde se le expecifica el mensaje de exito o fracaso que se obtuvo de la modificacion.
     */
    public String actualizar(T entidad) {
        EntityManager em;
        em=emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.merge(entidad);
            em.getTransaction().commit();
            em.close();
            return "Fue Actualizado Exitosamente...!";
        }catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("Error en Actualizar ["+entidad.toString()+"]==>"+e.getMessage());
            return "Error en Actualizar ["+entidad.toString()+"]==>"+e.getMessage();
        }
    }
    /**
     * Metodo eliminar(T entidad).
     * Elimina la entidad en la base de datos.
     * @param entidad referencia generica.
     * @return String, una cadena donde se le expecifica el mensaje de exito o fracaso que se obtuvo de la eliminacion.
     */
    public String eliminar(T entidad) {
        EntityManager em;
        em=emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.merge(entidad);
            em.remove(entidad);
            em.getTransaction().commit();
            em.close();
            return "Fue Borrado de la Base de Datos Exitosamente...!";
        }catch (Exception e) {
            em.getTransaction().rollback();
            return "Error al Borrado de la Base de Datos ["+entidad.toString()+"]==>"+e.getMessage();
        }
    }
    /**
     * Metodo buscarPorId(Object id, String Clase).
     * Busca la entidad en la base de datos teniendo en cuenta el ID y Nombre de la clase a persistir.
     * @param id identificacion de la entidad a buscar.
     * @param Clase nombre de la clase a persistir.
     * @return Una instancia de tipo Generica <T> del "id" especificado como parametro.
     */
    public T buscarPorId(Object id, String Clase) {
        EntityManager em;
        em=emf.createEntityManager();
        try{
            Query q = em.createQuery("SELECT entidad FROM "+Clase+" entidad WHERE entidad.estado=TRUE AND entidad.id="+id);
            return (T)q.getSingleResult();
        }catch (Exception e) {
            System.out.println("Error al Buscar la (o) "+Clase+" Activa->"+e.getMessage());
            em.getTransaction().rollback();
            return null;
        }
    }
    /**
     * Metodo buscarActivos(String Clase).
     * Busca en la base de datos todos los registro que esten activos, es decir, que su estado sea TRUE.
     * @param Clase nombre de la clase a persistir.
     * @return List<T> Una Lista de tipo Generica <T> de todos los registros activos (Estado=True).
     */
    public List<T> buscarActivos(String Clase) {
        EntityManager em;
        em=emf.createEntityManager();
        try{
            em.getTransaction().begin();
            Query q = em.createQuery("SELECT entidad FROM "+Clase+" entidad WHERE entidad.estado=TRUE");
            List<T> lista=q.getResultList();
            return lista;
        }catch (Exception e) {
            System.out.println("Error al Buscar los (as) "+Clase+" Activos->"+e.getStackTrace());
            return null;
        }
    }
    /**
     * Metodo buscarTodos(String Clase).
     * Busca en la base de datos todos los registro que esten activos e inactivos, es decir, que su estado sea TRUE o FALSE.
     * @param Clase nombre de la clase a persistir.
     * @return List<T> Una Lista de tipo Generica <T> de todos los registros activos e inactivos(Estado=True o Estado=False).
     */
    public List<T> buscarTodos(String Clase) {
        EntityManager em;
        em=emf.createEntityManager();
        try{
            Query q = em.createQuery("SELECT entidad FROM "+Clase+" entidad");
            return q.getResultList();
        }catch (Exception e) {
            System.out.println("Error al Buscar los (as) "+Clase+" Activos y No Activos->"+e.getMessage());
            em.getTransaction().rollback();
            return null;
        }
    }
    /**
     * Metodo buscarInactivos(String Clase).
     * Busca en la base de datos todos los registro que esten inactivos, es decir, que su estado sea FALSE.
     * @param Clase nombre de la clase a persistir.
     * @return List<T> Una Lista de tipo Generica <T> de todos los registros inactivos(Estado=False).
     */
    public List<T> buscarInactivos(String Clase) {
        EntityManager em;
        em=emf.createEntityManager();
        try{
            Query q = em.createQuery("SELECT entidad FROM "+Clase+" entidad WHERE entidad.estado=FALSE");
            return q.getResultList();
        }catch (Exception e) {
            System.out.println("Error al Buscar los (as) "+Clase+" NO Activos->"+e.getMessage());
            em.getTransaction().rollback();
            return null;
        }
    }
    /**
     * Metodo inactivarRegistro(T entidad).
     * inactiva la entidad en la base de datos, es decir, el estado de la entidad la modifica a FALSE.
     * @param entidad referencia generica.
     * @return String, una cadena donde se le expecifica el mensaje de exito o fracaso que se obtuvo en la modificacion.
     */
    @Override
    public String inactivarRegistro(T entidad) {
        EntityManager em;
        em=emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.merge(entidad);
            em.getTransaction().commit();
            em.close();
            return "Fue Eliminado Exitosamente...!";
        }catch (Exception e) {
            em.getTransaction().rollback();
            return "Error al Eliminar ["+entidad.toString()+"]==>"+e.getMessage();
        }
    }
    
}
