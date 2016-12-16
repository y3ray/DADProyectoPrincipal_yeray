package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexion {

    //DECLARAMOS LAS INSTANCIAS DE LA CONEXIÓN Y DEL RESULTSET
    Connection conn = null;
    ResultSet rs = null;

    //CREO VARIABLES PARA ALMACENAR LOS REGISTROS DEL METODO ALTAS
    private String txtApellido1;
    private String txtApellido2;
    private String txtDni;
    private String txtNombre;
    private int txtRegistro;

    public Conexion() {

    }

    //ESTABLECEMOS LA CONEXIÓN
    public void Conectar(String sql) throws SQLException {
        try {
            //CREAR LA CONEXION
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteca", "root", "");
            Statement informacionGuardada = conn.createStatement();
            rs = informacionGuardada.executeQuery(sql);//recibimos la tabla a la que queremos acceder desde cada constructor de negocio
            rs.next();
            System.out.println("Conectado");

          
            

        } catch (SQLException ex) {
            System.out.println("La conexion no está establecida");
        }
    }

    public ResultSet getResultSet() {
        return rs;
    }

    public Connection getConnection() {
        return conn;
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
