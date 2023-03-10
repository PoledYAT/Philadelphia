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
     * 
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
    
    
    public Multa obtenerPorId(int id){
        Multa multa = null;
        
        try{
            
          Session session = HibernateUtil.getSessionFactory().openSession();
          multa = session.get(Multa.class, id);
            
        }catch(HibernateException ex){
            System.err.println("Ocurrio un error:"+ ex.getMessage());
        }
     return multa;   
    }
    
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