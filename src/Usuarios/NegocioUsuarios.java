package Usuarios;

import conexion.Conexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Yeray
 */
public class NegocioUsuarios {
    //Instaciamos las clases
    DTO_Usuario usuario;
    Conexion conn;
    ResultSet rs = null;
    
    //Inicializamos las clases
    public NegocioUsuarios() throws SQLException{
        conn = new Conexion();
        conn.Conectar("select * from usuarios");//Pasamos la String de la tabla que vamos a consultar a la conexion
        rs = conn.getResultSet();//Cargamos el resultset
        usuario = new DTO_Usuario();
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
    public void Altas(String txtUsuario, String txtClave) throws SQLException {
        String sql = "insert into usuarios values ('" + txtUsuario + "', " + " '" + txtClave + "')";
        
        //conn.Altas(sql);//le paso el parametro de la consulta a la conexion para que me haga la actualizacion desde la misma
        executeUpdate(sql);
    }

    //Los parametros de los metodos son los JtextField que vienen del frame
    public void Eliminar(String txtUsuario) throws SQLException {
        String sql = "delete from usuarios where usuario = '" + txtUsuario + "'";
        //delete from usuarios where usuario = 'pepe'
        executeUpdate(sql);
    }

    //Los parametros de los metodos son los JtextField que vienen de la clase evento
    public void Modificar(String txtUsuario, String txtClave) throws SQLException {
        //dni, nombre, apellido1 y apellido2 son los nombres de los campos de la base de datos
        String sql = "update usuarios set  clave = '" + txtClave + "' "
        + " where usuario = '" + txtUsuario + "'";
        
        //Le pasamos por parametro a la clase conexion, la consulta que tenemos en la String sql. 
        //Para que la conexion haga la modificacion en la base de datos con el executeUdate
        executeUpdate(sql);
    }
    
    //crea un metodo que me devuelva un alumno creado
    public DTO_Usuario getUsuarioCreado() throws SQLException {
        usuario.setUsuario(rs.getString("usuario"));
        usuario.setClave(rs.getString("clave"));
        return usuario;
    }
    
    
    //SE PUEDE OBTENER CUALQUIER REGISTRO DEL RESULTSET INDEPENDIENTEMENTE DEL TIPO DE DATO PASANDOLE UN PARAMETRO
    public String getCadena(String campo) throws SQLException {
        return rs.getString(campo);
    }
}
