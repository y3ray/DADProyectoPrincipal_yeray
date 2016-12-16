package prestamos;

import alumnos.Controlador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import libros.ControladorLibros;

/**
 *
 * @author Yeray
 */
public class ControladorPrestamos implements ActionListener {

    DTO_Prestamo prestamo;
    NegocioPrestamos negocio;
    VistaPrestamos vista;
    Controlador alumno = new Controlador();
    ControladorLibros libros = new ControladorLibros();

    public ControladorPrestamos() throws SQLException {
        prestamo = new DTO_Prestamo();
        negocio = new NegocioPrestamos();
        vista = new VistaPrestamos(this);
        vista.setVisible(true);
        vista.setTitle("Prestamos");
        MeterPrestamoEnTextField();
    }

    public void MeterPrestamoEnTextField() throws SQLException {
        prestamo = negocio.getPrestamoCreado();//Prestamo comprimido
        //Descomprimimos en los textfield
        vista.getTxtId().setText(prestamo.getId() + "");
        vista.getTxtCodAlumno().setText(prestamo.getCodAlumno());
        vista.getTxtCodLibro().setText(prestamo.getCodLibros());
        vista.getTxtFechaPrestamo().setText(prestamo.getFechaPrestamo() + "");
        vista.getTxtFechaDevolucion().setText(prestamo.getFechaDevolucion().toString());
        vista.getTxtEstado().setText(prestamo.getEstado());
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        try {
            if (e.getSource() == vista.getBtnSiguiente()) {
                negocio.Siguiente();//Pasa al siguiente libro y lo mete en los textField
                MeterPrestamoEnTextField();
            }
            if (e.getSource() == vista.getBtnAnterior()) {
                negocio.Anterior();
                MeterPrestamoEnTextField();
            }

            if (e.getSource() == vista.getBtnAltas()) {
                negocio.Altas(Integer.parseInt(vista.getTxtId().getText()), vista.getTxtCodAlumno().getText(), vista.getTxtCodLibro().getText(), vista.getTxtFechaPrestamo().getText(), vista.getTxtFechaDevolucion().getText(), vista.getTxtEstado().getText());
                MeterPrestamoEnTextField();
            }

            if (e.getSource() == vista.getBtnModificar()) {
                negocio.Modificar(Integer.parseInt(vista.getTxtId().getText()), vista.getTxtCodAlumno().getText(), vista.getTxtCodLibro().getText(), vista.getTxtFechaPrestamo().getText(), vista.getTxtFechaDevolucion().getText(), vista.getTxtEstado().getText());
                MeterPrestamoEnTextField();
            }

            if (e.getSource() == vista.getBtnEliminar()) {
                negocio.Eliminar(Integer.parseInt(vista.getTxtId().getText()));
                MeterPrestamoEnTextField();
            }
            
            if (e.getSource() == vista.getBtnTraerAlumno()){
                String id = String.valueOf(alumno.getAlumno().getRegistro());
                vista.getTxtCodAlumno().setText(id);
            }
            
            if (e.getSource() == vista.getBtnTraerLibro()){
                String id = String.valueOf(libros.getLibro().getCodigo());
                vista.getTxtCodLibro().setText(id);
                vista.getTxtEstado().setText(libros.getLibro().getEstado());
            }

        } catch (SQLException ex) {
            Logger.getLogger(ControladorPrestamos.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
