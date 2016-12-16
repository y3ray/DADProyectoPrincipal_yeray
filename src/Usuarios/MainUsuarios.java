package Usuarios;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Yeray
 */
public class MainUsuarios {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            ControladorUsuarios iniciar = new ControladorUsuarios();
        } catch (SQLException ex) {
            Logger.getLogger(MainUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
