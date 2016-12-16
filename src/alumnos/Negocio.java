package alumnos;
import conexion.Conexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Yeray
 */
//EN ESTA CLASE METEMOS TODAS LAS CONSULTAS SQL.
public class Negocio {
//Instaciamos las clases
    DTO_Alumnos alumno;
    Conexion conn;
    ResultSet rs = null;
    
    
    //Inicializamos las clases
    public Negocio() throws SQLException {
        conn = new Conexion();
        conn.Conectar("select * from alumnos");
        rs = conn.getResultSet();//Cargamos el resultset
        alumno = new DTO_Alumnos();
    }

    //METODOS
     public void executeUpdate(String sql) throws SQLException {
        Statement infoGuardada = conn.getConnection().createStatement();
        infoGuardada.executeUpdate(sql);//Recibe la String de la consulta para realizar los cambios
    }
    
    public void Siguiente() throws SQLException {
        rs.next();
        //conn.Siguiente();//LLamo al metodo que esta en la clase conexion, allí es donde esta el resutset señalando al siguiente  
    }
    

    public void Anterior() throws SQLException {
        rs.previous();
    }

    //Los parametros de los metodos son los JtextField que vienen del frame
    //Pasamos la consulta por parametro a la clase conexion que es la que tiene el Statement
    public void Altas(String txtDni, String txtNombre, String txtApellido1, String txtApellido2) throws SQLException {
        String sql = "insert into alumnos values (null, '" + txtDni + "',"
                + " '" + txtNombre + "', '" + txtApellido1 + "',"
                + " '" + txtApellido2 + "')";

        //conn.Altas(sql);//le paso el parametro de la consulta a la conexion para que me haga la actualizacion desde la misma
        executeUpdate(sql);
    }

    //Los parametros de los metodos son los JtextField que vienen del frame
    public void Eliminar(int txtRegistro) throws SQLException {
        String sql = "delete from alumnos where registro = " + txtRegistro;
        executeUpdate(sql);
    }

    //Los parametros de los metodos son los JtextField que vienen de la clase evento
    public void Modificar(int txtRegistro, String txtDni, String txtNombre, String txtApellido1, String txtApellido2) throws SQLException {
        //dni, nombre, apellido1 y apellido2 son los nombres de los campos de la base de datos
        String sql = "update alumnos set dni = '" + txtDni + "',"
                + " nombre = '" + txtNombre + "', apellido1 = '" + txtApellido1 + "',"
                + " apellido2 = '" + txtApellido2 + "'"
                + " where registro = " + txtRegistro;
        
        
        //Le pasamos por parametro a la clase conexion, la consulta que tenemos en la String sql. 
        //Para que la conexion haga la modificacion en la base de datos con el executeUdate
        executeUpdate(sql);
    }
    
    //crea un metodo que me devuelva un alumno creado
    public DTO_Alumnos getAlumnoCreado() throws SQLException {
        //Crea el alumno con los datos que le pasamos por parametro.
        //Mete en los campos del alumno los datos que tengamos en la base de datos con el resultset,
        //para poder mandarselo luego al textField correspondiente
        alumno.setRegistro(Integer.parseInt(rs.getString("registro")));
        alumno.setDni(rs.getString("dni"));
        alumno.setNombre(rs.getString("nombre"));
        alumno.setApellido1(rs.getString("apellido1"));
        alumno.setApellido2(rs.getString("apellido2"));
        return alumno;
    }
    
    //GETTERS para los metodos de los botonoes (siguiente, anterior) de la vista.
    //Para que regresen la informacion que tienen en ese momento en la Base de Datos.
    //SE PUEDE OBTENER CUALQUIER REGISTRO DEL RESULTSET INDEPENDIENTEMENTE DEL TIPO DE DATO PASANDOLE UN PARAMETRO
    public String getCadena(String campo) throws SQLException {
        return rs.getString(campo);
    }
    
    //SE PUEDE OBTENER LOS REGISTROS DEL RESULTSET ASI INDIVIDUALES CADA METODO DEVUELVE SU REGISTRO CON SU TIPO DE DATO
//    public String getTxtApellido1() throws SQLException {
//        return rs.getString("apellido1");
//    }
//
//    public String getTxtApellido2() throws SQLException {
//        return rs.getString("apellido2");
//    }
//
//    public String getTxtDni() throws SQLException {
//        return rs.getString("dni");
//    }
//
//    public String getTxtNombre() throws SQLException {
//        return rs.getString("nombre");
//    }
//
//    public int getTxtRegistro() throws SQLException {
//        return rs.getInt("registro");
//    }

}
