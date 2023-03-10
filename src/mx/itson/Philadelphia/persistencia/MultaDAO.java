/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.itson.philadelphia.persistencia;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.criteria.CriteriaQuery;
import mx.itson.Philadelphia.entidades.Conductor;
import mx.itson.Philadelphia.entidades.Multa;
import mx.itson.Philadelphia.entidades.Oficial;
import mx.itson.Philadelphia.utilerias.HibernateUtil;
import org.hibernate.HibernateException;

import org.hibernate.Session;

/**
 * es un DATA ACCESS OBJECT, que acede a los datos de la tabla Multa
 * @author pyatq
 */
public class MultaDAO {
    
    /**
     * es un DATA ACCESS OBJECT, que acede a los datos de la tabla Multa
     * @return 
     */
    public static List <Multa> obtenerTodos(){
        List<Multa> multas = new ArrayList<>();
        try{
            
            Session session = HibernateUtil.getSessionFactory().openSession();
            
            CriteriaQuery<Multa> criteriaQuery = session.getCriteriaBuilder().createQuery(Multa.class);
            
            criteriaQuery.from(Multa.class);
            
            multas = session.createQuery(criteriaQuery).getResultList();

            
        }catch(Exception ex){
          System.err.println("Ocurrio un error:"+ ex.getMessage());
       }
        return multas;
    }
    
    /**
     * Es para guardar los datos de multa
     * @param folio se guardara los datos de folio
     * @param motivo se gurdara los datos de motivo
     * @param fecha se guardara los fecha
     * @param conductor se guardara el nombre de conductor 
     * @param oficial se gurdara el nombre de oficial
     * @return Indica si el registro fue guardado correcta mente
     */
    public static boolean guardar(String folio, String motivo, Date fecha, Conductor  conductor, Oficial oficial){
        boolean resultado = false;
        
        try{
            
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            
            //se va a generar una intancia de multa
            Multa m = new Multa();
            m.setFolio(folio);
            m.setMotivo(motivo);
            m.setFecha(fecha);
            m.setConductor(conductor);
            m.setOficial(oficial);

            session.save(m);
            
            session.getTransaction().commit();
            
            resultado = m.getId()!=0;
        }catch(Exception ex){
            
            System.err.println("Ocurrio un error:"+ ex.getMessage());
            
        }
        return resultado;
    }
    
    /**
     * Es para obtener los datos del Multa desde su id
     * @param id obtendremos los datos desde el ID
     * @return retorna conductor de lo contrario retarna a null
     */
    public static Multa obtenerPorId(int id){
        Multa multa = null;
        
        try{
            
          Session session = HibernateUtil.getSessionFactory().openSession();
          multa = session.get(Multa.class, id);
            
        }catch(HibernateException ex){
            System.err.println("Ocurrio un error:"+ ex.getMessage());
        }
     return multa;   
    }
    
    /**
     * Es para editar los datos de la tabla de multa
     * @param id el ID se quedara igual por el int
     * @param folio se editara folio
     * @param motivo se editara motivo
     * @param fecha se editara la fecha 
     * @param conductor se editara el nombre del conductor
     * @param oficial se editara el nombre de oficial
     * @return es para indicar si el registro fue editado de lo multa retorna false
     */
     public boolean editar(int id, String folio, String motivo, Date fecha, Conductor conductor, Oficial oficial){
        
        boolean resultado = false;
        try{
            
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            
            Multa multa = obtenerPorId(id);
            
            if(multa != null){
                multa.setFolio(folio);
                multa.setMotivo(motivo);
                multa.setFecha(fecha);
                multa.setConductor(conductor);
                multa.setOficial(oficial);
                
                
                session.saveOrUpdate(multa);
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
            
            Multa multa = obtenerPorId(id);
            
            if(multa != null){
                session.delete(multa);
                session.getTransaction().commit();
                
               resultado = true;
            }
            }catch(HibernateException ex){
            System.err.println("Ocurrio un error:"+ ex.getMessage());
        }
        return resultado;
    }
}