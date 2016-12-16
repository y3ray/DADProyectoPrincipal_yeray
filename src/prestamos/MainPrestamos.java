package prestamos;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Yeray
 */
public class MainPrestamos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            ControladorPrestamos iniciar = new ControladorPrestamos();
        } catch (SQLException ex) {
            Logger.getLogger(MainPrestamos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
