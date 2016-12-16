/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alumnos;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Yeray
 */
public class inicio {
    
    public static void main(String[] args){
        try {
            Controlador iniciar = new Controlador();
        } catch (SQLException ex) {
            Logger.getLogger(inicio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
