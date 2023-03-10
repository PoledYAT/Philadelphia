/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.itson.Philadelphia.entidades;

import java.util.Date;
//import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
//import javax.persistence.Table;

/**
 *
 * @author pyatq
 */
@Entity
//Esto es configuración
//@Table(name="Conductor")
public class Conductor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    //Esto es configuración
    //@Column(name="nombre")
    private String nombre;
    private String numeroLicencia;
    
    @Temporal(TemporalType.DATE)
    private Date fechaAlta;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNumeroLicencia() {
        return numeroLicencia;
    }

    public void setNumeroLicencia(String numeroLicencia) {
        this.numeroLicencia = numeroLicencia;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }
    
    @Override
    public String toString(){
        return this.nombre;
    }

}
