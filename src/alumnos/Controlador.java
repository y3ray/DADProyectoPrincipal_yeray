package alumnos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Yeray
 */
//AQUÍ METEMOS TODOS LOS EVENTOS DE LA VISTA.
public class Controlador implements ActionListener {

    //Instanciamos las clases que vamos a usar.
    DTO_Alumnos alumno;
    Negocio negocio;
    frmPrincipal vista;

    public Controlador() throws SQLException {
        alumno = new DTO_Alumnos();
        negocio = new Negocio();
        vista = new frmPrincipal(this);//(this)Le pasamos el controlador por parametro para que haga los eventos.
        vista.setVisible(true);
        vista.setTitle("Alumnos");
        Meter_Alumno_En_TextField();//Iniciamos la vista con los registros del primer alumno cargados
    }
   
   
    
    @Override
    public void actionPerformed(ActionEvent e) {

        try {
            
            //getSource me dice que boton se ha pulsado

            if (e.getSource() == vista.getBtnSiguiente()) {
                negocio.Siguiente();
                 Meter_Alumno_En_TextField();
                 
            }

            if (e.getSource() == vista.getBtnAnterior()) {
                Meter_Alumno_En_TextField();
                negocio.Anterior();
            }

            if (e.getSource() == vista.getBtnAltas()) {
                //Pasamos el contenido del los textField por parametros a la clase Negocio, al metodo Alta.
                negocio.Altas(vista.getTxtDni().getText(), vista.getTxtNombre().getText(), vista.getTxtApellido1().getText(), vista.getTxtApellido2().getText());
                Meter_Alumno_En_TextField();
            }

            if (e.getSource() == vista.getBtnEliminar()) {
                negocio.Eliminar(Integer.parseInt(vista.getTxtRegistro().getText()));
                 Meter_Alumno_En_TextField();
            }

            if (e.getSource() == vista.getBtnModificar()) {
                negocio.Modificar(Integer.parseInt(vista.getTxtRegistro().getText()), vista.getTxtDni().getText(), vista.getTxtNombre().getText(), vista.getTxtApellido1().getText(), vista.getTxtApellido2().getText());
                 Meter_Alumno_En_TextField();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     //Trae el alumno creado de la clase alumnos. Accediendo a la clase DTO para traer solo el codigo de alumno
    public alumnos.DTO_Alumnos getAlumno() throws SQLException{
        return negocio.getAlumnoCreado();
    }

    //meto en los textfield los datos del alumno
    //para ello creo una instancia de la clase DTO_Alumnos y la variable donde quiero meter el alumno comprimido (todos sus datos)
    //Despues tengo que descomprimir los atributos del alumno almacenandolos en los txtNombre, txtApellido1 etc...
    //Para ellos necesito instanciar la clase frame para hacer uso de sus metodos getter y setter.
    public void Meter_Alumno_En_TextField() throws SQLException {
        alumno = negocio.getAlumnoCreado();//Trae el alumno comprimido
        //Descomprime el alumno almacenandolo en los textfield
        vista.getTxtRegistro().setText(alumno.getRegistro() + "");
        vista.getTxtDni().setText(alumno.getDni());
        vista.getTxtNombre().setText(alumno.getNombre());
        vista.getTxtApellido1().setText(alumno.getApellido1());
        vista.getTxtApellido2().setText(alumno.getApellido2());
        
    }

}
