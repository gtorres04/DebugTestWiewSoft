/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.debugtestwiewsoft.dao;

import java.util.List;

/**
 * Interface Generica Dao<T>.
 * Interface donde se definen los procesos que todas las entidades deben realizar:
 * 1. Registro.
 * 2. buscarActivos.
 * 3. Modificacion.
 * 5. Eliminacion.
 * 6. inactivarRegistro.
 * 7. buscarPorId.
 * 8. buscarInactivos.
 * 9. buscarTodos
 * @author Gerlin Orlando Torres Saavedra
 */
public interface Dao<T> {
    public String registrar(T entidad);
    public String actualizar(T entidad);
    public String eliminar(T entidad);
    public String inactivarRegistro(T  entidad);
    public T buscarPorId(Object id,String Clase);
    public List<T> buscarActivos(String Clase);
    public List<T> buscarTodos(String Clase);
    public List<T> buscarInactivos(String Clase);
}
