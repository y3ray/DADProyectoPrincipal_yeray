package Usuarios;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Yeray
 */
public class ControladorUsuarios implements ActionListener {

    //Instanciamos las clases que vamos a usar.
    DTO_Usuario usuario;
    NegocioUsuarios negocio;
    VistaUsuarios vista;

    public ControladorUsuarios() throws SQLException {
        usuario = new DTO_Usuario();
        negocio = new NegocioUsuarios();
        vista = new VistaUsuarios(this);//Recibimos el evento del boton que se ha pulsado en la vista
        vista.setVisible(true);//Hacemos visible la vista.
        Meter_Usuario_En_TextField();//Iniciamos la vista con los registros del primer libro cargados
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        //getSource me dice que boton se ha pulsado
        try {
            if (e.getSource() == vista.getBtnSiguiente()) {         
                negocio.Siguiente();
                Meter_Usuario_En_TextField();
            }
            
            if (e.getSource() == vista.getBtnAnterior()) {
                negocio.Anterior();
                Meter_Usuario_En_TextField();
            }

            if (e.getSource() == vista.getBtnAltas()) {
                //Pasamos el contenido del los textField por parametros a la clase Negocio, al metodo Alta.
                negocio.Altas(vista.getTxtUsuario().getText(), vista.getTxtClave().getText());
                Meter_Usuario_En_TextField();
            }

            if (e.getSource() == vista.getBtnEliminar()) {
                negocio.Eliminar(vista.getTxtUsuario().getText());
                Meter_Usuario_En_TextField();
            }

            if (e.getSource() == vista.getBtnModificar()) {
                negocio.Modificar(vista.getTxtUsuario().getText(), vista.getTxtClave().getText());
                Meter_Usuario_En_TextField();
            }

        } catch (SQLException ex) {
            Logger.getLogger(ControladorUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void Meter_Usuario_En_TextField() throws SQLException {
        usuario = negocio.getUsuarioCreado();//Trae el usuario comprimido
        //Descomprime el usuario almacenandolo en los textfield
        vista.getTxtUsuario().setText(usuario.getUsuario());
        vista.getTxtClave().setText(usuario.getClave());
    }
}
