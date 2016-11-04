package dadproyectoprincipal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexion {

    //DECLARAMOS LAS INSTANCIAS DE LA CONEXIÓN Y DEL RESULTSET
    Connection conn = null;
    ResultSet rs = null;
    DTO_Alumnos alumno = new DTO_Alumnos();

    //CREO VARIABLES PARA ALMACENAR LOS REGISTROS DEL METODO ALTAS
    private String txtApellido1;
    private String txtApellido2;
    private String txtDni;
    private String txtNombre;
    private int txtRegistro;
    //private String txtEstado;

    public Conexion() {

    }

    //ESTABLECEMOS LA CONEXIÓN
    public void Conectar() throws SQLException {
        try {
            //CREAR LA CONEXION
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteca", "root", "");
            Statement informacionGuardada = conn.createStatement();
            rs = informacionGuardada.executeQuery("select * from alumnos");
            rs.next();
            System.out.println("Conectado");
            //System.out.println(rs.getString("dni"));

        } catch (SQLException ex) {
            System.out.println("La conexion no está establecida");
        }
    }

    //CREAMOS LOS METODOS SIGUIENTE, ANTERIOR, ALTAS, BAJAS, MODIFICAR ETC. CON SUS GETTER
    public void Siguiente() throws SQLException {
      
        rs.next();//Me pasa la consulta del primer campo de la posicion donde esta el puntero actual
//        alumno.setRegistro(Integer.parseInt(rs.getString("registro")));
//        alumno.setDni(rs.getString("dni"));
//        alumno.setNombre(rs.getString("nombre"));
//        alumno.setApellido1(rs.getString("apellido1"));
//        alumno.setApellido2(rs.getString("apellido2"));
    }

    public void Anterior() throws SQLException {
        rs.previous();
    }

    //Usamos la informacion que nos pasa la clase negocio por parametro, 
    //para tenerlos aquí y hacer uso del Stamento y el metodo executeUpdate
    public void Altas(String sql) throws SQLException {
        Statement infoGuardada = conn.createStatement();
        infoGuardada.executeUpdate(sql);
    }

    //Usamos el numero de registro que nos pasan desde el textField de Registro de la clase frmPrincipal
    //y aquí lo eliminamos
    public void Eliminar(String sql) throws SQLException {
        Statement infoGuardada = conn.createStatement();
        infoGuardada.executeUpdate(sql);
    }

    //Usamos la informacion que nos pasan desde los textField para modificarlos en este metodo
    //Los parametros de los metodos son los JtextField que vienen del frame a Negocio y de Negocio a Conexion la String sql
    public void Modificar(String sql) throws SQLException {
        Statement infoGuardada = conn.createStatement();
        //dni, nombre, apellido1 y apellido2 son los nombres de los campos de la base de datos
        infoGuardada.executeUpdate(sql);

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
//
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
}
