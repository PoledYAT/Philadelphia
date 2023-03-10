/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.itson.Philadelphia.persistencia;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.criteria.CriteriaQuery;
import mx.itson.Philadelphia.entidades.Conductor;
import mx.itson.Philadelphia.utilerias.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 * es un DATA ACCESS OBJECT, que acede a los datos de la tabla conductor 
 * @author pyatq
 */
public class ConductorDAO {
    
    public static List <Conductor> obtenerTodos(){
        List<Conductor> conductores = new ArrayList<>();
        try{
            
            Session session = HibernateUtil.getSessionFactory().openSession();
            
            CriteriaQuery<Conductor> criteriaQuery = session.getCriteriaBuilder().createQuery(Conductor.class);
                
            criteriaQuery.from(Conductor.class);
            
            conductores = session.createQuery(criteriaQuery).getResultList();

            
        }catch(Exception ex){
          System.err.println("Ocurrio un error:"+ ex.getMessage());
       }
        return conductores;
    }
    
    /**
     * Guarda un nuevo registro de conductro 
     * @param nombre Nombre del conductor
     * @param numeroLicencia numero de lincencia del conductor
     * @param fechaAlta Fecha de alta del conductor 
     * @return Indica si el registro fue guardado correcta mente
     */
    public static boolean  guardar(String nombre, String numeroLicencia, Date fechaAlta){
        boolean resultado = false;
        
        try{
            
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            
            //se va a generar una intancia de conductor
            Conductor c = new Conductor();
            c.setNombre(nombre);
            c.setNumeroLicencia(numeroLicencia);
            c.setFechaAlta(fechaAlta);
            
            session.save(c);
            
            session.getTransaction().commit();
            
            resultado = c.getId()!=0;
        }catch(Exception ex){
            
            System.err.println("Ocurrio un error:"+ ex.getMessage());
            
        }
        return resultado;
    }
    
    /**
     * Es para obtener los datos del conductor desde su id
     * @param id obtener los datos del conductor 
     * @return retorna conductor de lo contrario retarna a null
     */
    public static Conductor obtenerPorId(int id){
        Conductor conductor = null;
        
        try{
            
          Session session = HibernateUtil.getSessionFactory().openSession();
          conductor = session.get(Conductor.class, id);
            
        }catch(HibernateException ex){
            System.err.println("Ocurrio un error:"+ ex.getMessage());
        }
     return conductor;   
    }
    
    /**
     * Es para poder editar cualquier dato de la tabla conductor 
     * @param id editar por id
     * @param nombre editar el nombre
     * @param numeroLicencia editar el numero de licencia
     * @param fechaAlta editar la fecha de alta 
     * @return es para indicar si el registro fue editado de lo contrario retorna false
     */
    
    public boolean editar(int id, String nombre, String numeroLicencia, Date fechaAlta){
        
        boolean resultado = false;
        try{
            
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            
            Conductor conductor = obtenerPorId(id);
            
            if(conductor != null){
                conductor.setNombre(nombre);
                conductor.setNumeroLicencia(numeroLicencia);
                conductor.setFechaAlta(fechaAlta);
                
                session.saveOrUpdate(conductor);
                session.getTransaction().commit();
                
                resultado = true;
            }
            
        }catch(HibernateException ex){
            System.err.println("Ocurrio un error:"+ ex.getMessage());
        }
        return resultado;
    }
    
    /**
     * Es para eliminar una una fila de un dato de conductor desde el ID
     * @param id se eliminara el dato desde el ID
     * @return si el registro se elimina correcta mente entrara; pero si no, retornara flase 
     */
    public boolean eliminar(int id){
        boolean resultado = false;
        
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            
            Conductor conductor = obtenerPorId(id);
            
            if(conductor != null){
                session.delete(conductor);
                session.getTransaction().commit();
                
                resultado = true;
            }
            }catch(HibernateException ex){
            System.err.println("Ocurrio un error:"+ ex.getMessage());
        }
        return resultado;
    }
}


