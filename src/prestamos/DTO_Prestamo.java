/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prestamos;

import java.sql.Date;

/**
 *
 * @author Yeray
 */
public class DTO_Prestamo {

    int id;
    String codAlumno;
    String codLibros;
    Date fechaPrestamo;
    Date fechaDevolucion;
    String estado;

    public DTO_Prestamo() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodAlumno() {
        return codAlumno;
    }

    public void setCodAlumno(String codAlumno) {
        this.codAlumno = codAlumno;
    }

    public String getCodLibros() {
        return codLibros;
    }

    public void setCodLibros(String codLibros) {
        this.codLibros = codLibros;
    }

    //Podemos cambiar el tipo de dato a String para que nos de la string,
    //el incombeniente es que al modificarlo si ponemos un texto no devuelve un texto osea, no el formato fecha
    public Date getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(Date fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}
