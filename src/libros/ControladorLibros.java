/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libros;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Yeray
 */
public class ControladorLibros implements ActionListener {

    DTO_Libro libro;
    NegocioLibros negocio;
    VistaLibros vista;

    //Constructor.
    public ControladorLibros() throws SQLException {
        libro = new DTO_Libro();
        negocio = new NegocioLibros();
        vista = new VistaLibros(this);//Recibimos el evento del boton que se ha pulsado en la vista
        vista.setVisible(true);//Hacemos visible la vista.
        vista.setTitle("Libros");
        MeterLibroEnTextField();//Iniciamos la vista con los registros del primer libro cargados
    }

    //Metodos.
    @Override
    public void actionPerformed(ActionEvent e) {

        try {
            if (e.getSource() == vista.getBtnSiguiente()) {
                negocio.Siguiente();//Pasa al siguiente libro y lo mete en los textField
                MeterLibroEnTextField();
            }

            if (e.getSource() == vista.getBtnAnterior()) {
                negocio.Anterior();
                MeterLibroEnTextField();
            }

            if (e.getSource() == vista.getBtnAltas()) {
                negocio.Altas(Integer.parseInt(vista.getTxtCodigo().getText()), vista.getTxtTitulo().getText(), vista.getTxtAutor().getText(), vista.getTxtEditorial().getText(), vista.getTxtAsignatura().getText(), vista.getTxtEstado().getText());
                MeterLibroEnTextField();
            }

            if (e.getSource() == vista.getBtnModificar()) {
                negocio.Modificar(Integer.parseInt(vista.getTxtCodigo().getText()), vista.getTxtTitulo().getText(), vista.getTxtAutor().getText(), vista.getTxtEditorial().getText(), vista.getTxtAsignatura().getText(), vista.getTxtEstado().getText());
                MeterLibroEnTextField();
            }

            if (e.getSource() == vista.getBtnEliminar()) {
                negocio.Eliminar(Integer.parseInt(vista.getTxtCodigo().getText()));
                MeterLibroEnTextField();
            }

        } catch (SQLException ex) {
            Logger.getLogger(ControladorLibros.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
     //Trae el libro creado de la clase libros. Accediendo a la clase DTO para traer solo el codigo de libro
    public DTO_Libro getLibro() throws SQLException{
        return negocio.getLibroCreado();
    }

    public void MeterLibroEnTextField() throws SQLException {
        libro = negocio.getLibroCreado();//Traemos el libro comprimido
        //Descomprimimos en los textField. Cambiamos el texto de los textField.
        vista.getTxtCodigo().setText(libro.getCodigo() + "");
        vista.getTxtTitulo().setText(libro.getTitulo());
        vista.getTxtAutor().setText(libro.getAutor());
        vista.getTxtEditorial().setText(libro.getEditorial());
        vista.getTxtAsignatura().setText(libro.getAsignatura());
        vista.getTxtEstado().setText(libro.getEstado());

    }

}
