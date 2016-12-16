package prestamos;

import conexion.Conexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 *
 * @author Yeray
 */
public class NegocioPrestamos {
    DTO_Prestamo prestamo;
    Conexion conn;
    ResultSet rs = null;
    
    public NegocioPrestamos() throws SQLException{
        conn = new Conexion();
        conn.Conectar("select * from prestamos");
        rs = conn.getResultSet();
        prestamo = new DTO_Prestamo();
    }
    
    public void executeUpdate(String sql) throws SQLException{
        Statement infoGuardada = conn.getConnection().createStatement();
        infoGuardada.executeUpdate(sql);
        
    }
    
    public void Siguiente() throws SQLException{
        rs.next();
    }
    
    public void Anterior() throws SQLException{
        rs.previous();
    }
    
    public void Altas(int txtId, String txtCodAlumno, String txtCodLibro, String txtFechaPrestamo, String txtFechaDevolucion, String txtEstado) throws SQLException{
        String sql = "insert into prestamos values(null, '" + txtCodAlumno + "', '" + txtCodLibro + "', '" + txtFechaPrestamo + "','"
                + txtFechaDevolucion + "', '" + txtEstado + "')";
        
        executeUpdate(sql);
    }
    
    public void Eliminar(int txtId) throws SQLException{
        String sql = "delete from prestamos where id = " + txtId;
        executeUpdate(sql);
    }
    
    public void Modificar(int txtId, String txtCodAlumno, String txtCodLibro, String txtFechaPrestamo, String txtFechaDevolucion, String txtEstado) throws SQLException{
        String sql = "update prestamos set codAlumno = '" + txtCodAlumno +
                "', codLibros = '" + txtCodLibro + "', FechaPrestamo = '" + txtFechaPrestamo + 
                "', FechaDevolucion = '" + txtFechaDevolucion + "', estado = '" + txtEstado +
                "' where id = " + txtId; 
        
        
          //String sql2 = "update libros set estado = '" + txtEstado + "'"; 
        
        executeUpdate(sql);
    }
    
    
    public DTO_Prestamo getPrestamoCreado() throws SQLException{  /*
        Timestamp fP = rs.getTimestamp("FechaPrestamo");
        Timestamp fD = rs.getTimestamp("FechaDevolucion");*/
         
        prestamo.setId(Integer.parseInt(rs.getString("id")));
        prestamo.setCodAlumno(rs.getString("CodAlumno"));
        prestamo.setCodLibros(rs.getString("codLibros"));
        prestamo.setFechaPrestamo(rs.getDate("FechaPrestamo"));
        prestamo.setFechaDevolucion(rs.getDate("FechaDevolucion"));
        prestamo.setEstado(rs.getString("estado"));
        return prestamo;
    }
    
    //SE PUEDE OBTENER CUALQUIER REGISTRO DEL RESULTSET INDEPENDIENTEMENTE DEL TIPO DE DATO PASANDOLE UN PARAMETRO
    public String getCadena(String campo) throws SQLException{
        return rs.getString(campo);
    }
    
}
