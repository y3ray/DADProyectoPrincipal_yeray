package libros;

import alumnos.DTO_Alumnos;
import conexion.Conexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Yeray
 */
public class NegocioLibros {

   //Instaciamos las clases
    DTO_Libro libro;
    Conexion conn;
    ResultSet rs = null;
    
    
    //Inicializamos las clases
    public NegocioLibros() throws SQLException {
        conn = new Conexion();
        conn.Conectar("select * from libros");
        rs = conn.getResultSet();//Cargamos el resultset
        libro = new DTO_Libro();
    }

    //METODOS traemos el getConnection (conn) de la clase conexion para hacer uso de los metodos de la clase connection
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
    public void Altas(int txtCodigo, String txtTitulo, String txtAutor, String txtEditorial, String txtAsignatura, String txtEstado) throws SQLException {
        String sql = "insert into libros values ('" + txtCodigo + "', '" + txtTitulo + "', '"
                + txtAutor + "', '" + txtEditorial + "', '" + txtAsignatura + "', '" + txtEstado +"')";

        //conn.Altas(sql);//le paso el parametro de la consulta a la conexion para que me haga la actualizacion desde la misma
        executeUpdate(sql);
    }

    //Los parametros de los metodos son los JtextField que vienen del frame
    public void Eliminar(int txtCodigo) throws SQLException {
        String sql = "delete from libros where codigo = " + txtCodigo;
        executeUpdate(sql);
    }

    //Los parametros de los metodos son los JtextField que vienen de la clase evento
    public void Modificar(int txtCodigo, String txtTitulo, String txtAutor, String txtEditorial, String txtAsignatura, String txtEstado) throws SQLException {
        //codigo, titulo, autor, editorail... son los nombres de los campos de la base de datos
        String sql = "update libros set codigo = '" + txtCodigo + "',"
                + " Titulo = '" + txtTitulo + "', Autor = '" + txtAutor + "', Editorial = '" + txtEditorial + "', "
                + " Asignatura = '" + txtAsignatura + "', estado = '" + txtEstado +"' " 
                + " where codigo = '" + txtCodigo +"'";
        
        //Le pasamos por parametro a la clase conexion, la consulta que tenemos en la String sql. 
        //Para que la conexion haga la modificacion en la base de datos con el executeUdate
        executeUpdate(sql);
    }
    
    //crea un metodo que me devuelva un alumno creado
    public DTO_Libro getLibroCreado() throws SQLException {
        //Crea el libro con los datos que le pasamos por parametro.
        //Mete en los campos del libro los datos que tengamos en la base de datos con el resultset,
        //para poder mandarselo luego al textField correspondiente
        libro.setCodigo(Integer.parseInt(rs.getString("codigo")));
        libro.setTitulo(rs.getString("Titulo"));
        libro.setAutor(rs.getString("Autor"));
        libro.setEditorial(rs.getString("Editorial"));
        libro.setAsignatura(rs.getString("Asignatura"));
        libro.setEstado(rs.getString("estado"));
        return libro;
    }
    
     //GETTERS para los metodos de los botonoes (siguiente, anterior) de la vista.
    //Para que regresen la informacion que tienen en ese momento en la Base de Datos.
    //SE PUEDE OBTENER CUALQUIER REGISTRO DEL RESULTSET INDEPENDIENTEMENTE DEL TIPO DE DATO PASANDOLE UN PARAMETRO
    public String getCadena(String campo) throws SQLException {
        return rs.getString(campo);
    }


   
}


