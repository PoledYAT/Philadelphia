/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.itson.Philadelphia.persistencia;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaQuery;
import mx.itson.Philadelphia.entidades.Oficial;
import mx.itson.Philadelphia.utilerias.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 * es un DATA ACCESS OBJECT, que acede a los datos de la tabla Oficial
 * @author pyatq
 */
public class OficialDAO {
    
    public static List <Oficial> obtenerTodos(){
        List<Oficial> oficiales = new ArrayList<>();
        try{
            
            Session session = HibernateUtil.getSessionFactory().openSession();
            
            CriteriaQuery<Oficial> criteriaQuery = session.getCriteriaBuilder().createQuery(Oficial.class);
                
            criteriaQuery.from(Oficial.class);
            
            oficiales= session.createQuery(criteriaQuery).getResultList();

            
        }catch(Exception ex){
          System.err.println("Ocurrio un error:"+ ex.getMessage());
       }
        return oficiales;
    }
    
    /**
     * Guarda un nuevo registro de Oficial
     * @param nombre Nombre del Oficial
     * @param telefono telefono de Oficial
     * @return Indica si el registro fue guardado correcta mente
     */
    public static boolean guardar(String nombre, String telefono){
        boolean resultado = false;
        
        try{
            
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            
            //se va a generar una intancia de conductor
            Oficial o = new Oficial();
            o.setNombre(nombre);
            o.getTelefono();
            
            session.save(o);
            
            session.getTransaction().commit();
            
            resultado = o.getId()!=0;
        }catch(Exception ex){
            
            System.err.println("Ocurrio un error:"+ ex.getMessage());
            
        }
        return resultado;
    }
    
    /**
     * Es para obtener los datos del Oficial desde su id
     * @param id obtener los datos del Oficial
     * @return retorna Oficial de lo contrario retarna a null
     */
    public Oficial obtenerPorId(int id){
        Oficial oficial = null;
        
        try{
            
          Session session = HibernateUtil.getSessionFactory().openSession();
          oficial = session.get(Oficial.class, id);
            
        }catch(HibernateException ex){
            System.err.println("Ocurrio un error:"+ ex.getMessage());
        }
     return oficial;   
    }
    
    /**
     * Es para poder editar cualquier dato de la tabla Oficial 
     * @param id editar por id
     * @param nombre editar el nombre
     * @param telefono editar telefono
     * @return es para indicar si el registro fue editado de lo contrario retorna false
     */
    
    public boolean editar(int id, String nombre, String telefono){
        
        boolean resultado = false;
        try{
            
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            
            Oficial oficial = obtenerPorId(id);
            
            if(oficial != null){
                oficial.setNombre(nombre);
                oficial.setTelefono(telefono);
                
                session.saveOrUpdate(oficial);
                session.getTransaction().commit();
                
                resultado = true;
            }
            
        }catch(HibernateException ex){
            System.err.println("Ocurrio un error:"+ ex.getMessage());
        }
        return resultado;
    }
    
    /**
     * Es para eliminar una una fila de un dato de oficial desde el ID
     * @param id se eliminara el dato desde el ID
     * @return si el registro se elimina correcta mente entrara; pero si no, retornara flase 
     */
    public boolean eliminar(int id){
        boolean resultado = false;
        
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            
            Oficial oficial = obtenerPorId(id);
            
            if(oficial != null){
                session.delete(oficial);
                session.getTransaction().commit();
                
                resultado = true;
            }
            }catch(HibernateException ex){
            System.err.println("Ocurrio un error:"+ ex.getMessage());
        }
        return resultado;
    }
}


