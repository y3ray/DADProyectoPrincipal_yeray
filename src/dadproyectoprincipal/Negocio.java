package dadproyectoprincipal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Yeray
 */
public class Negocio {
//Instaciamos las clases
    Conexion conn;
    ResultSet rs = null;
    
    
    //Inicializamos las clases
    public Negocio() throws SQLException {
        conn = new Conexion();
        conn.Conectar();
    }

    //METODOS
    public void Siguiente() throws SQLException {
        conn.Siguiente();//LLamo al metodo que esta en la clase conexion, allí es donde esta el resutset señalando al siguiente  
    }
    
    //crea un metodo que me devuelva un alumno creado ya de la clase conexion
    public DTO_Alumnos Alumno_creado() throws SQLException{
        
    return conn.getAlumnoCreado();
    }

    public void Anterior() throws SQLException {
        conn.Anterior();
    }

    //Los parametros de los metodos son los JtextField que vienen del frame
    //Pasamos la consulta por parametro a la clase conexion que es la que tiene el Statement
    public void Altas(String txtDni, String txtNombre, String txtApellido1, String txtApellido2) throws SQLException {
        String sql = "insert into alumnos values (null, '" + txtDni + "',"
                + " '" + txtNombre + "', '" + txtApellido1 + "',"
                + " '" + txtApellido2 + "')";

        conn.Altas(sql);

    }

    //Los parametros de los metodos son los JtextField que vienen del frame
    public void Eliminar(int txtRegistro) throws SQLException {
        String sql = "delete from alumnos where registro = " + txtRegistro;
    }

    //Los parametros de los metodos son los JtextField que vienen del frame
    public void Modificar(int txtRegistro, String txtDni, String txtNombre, String txtApellido1, String txtApellido2) throws SQLException {
        //dni, nombre, apellido1 y apellido2 son los nombres de los campos de la base de datos
        String sql = "update alumnos set dni = '" + txtDni + "',"
                + " nombre = '" + txtNombre + "', apellido1 = '" + txtApellido1 + "',"
                + " apellido2 = '" + txtApellido2 + "'"
                + " where registro = " + txtRegistro;

    }
    
//        public DTO_Alumnos getAlumnoCreado() throws SQLException {
//        //Crea el alumno con los datos que le pasamos por parametro.
//        //Mete en los campos del alumno los datos que tengamos en la base de datos con el resultset,
//        //para poder mandarselo luego al textField correspondiente
//        alumno.setRegistro(Integer.parseInt(rs.getString("registro")));
//        alumno.setDni(rs.getString("dni"));
//        alumno.setNombre(rs.getString("nombre"));
//        alumno.setApellido1(rs.getString("apellido1"));
//        alumno.setApellido2(rs.getString("apellido2"));
//        return alumno;
//    }
    
    

}
