package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD implements IConexionBD {

    final String CADENA_CONEXION = "jdbc:mysql://localhost/club_nautico_130pm";
    final String USUARIO = "root";
    final String CONTRASENIA = "1234";
    
    @Override
    public Connection crearConexion() throws SQLException {
        // ESTABLECEMOS UNA CONEXIÓN A MYSQL CON LOS PARÁMETROS PROPORCIONADOS
        Connection conexion = DriverManager.getConnection(CADENA_CONEXION, USUARIO, CONTRASENIA);
        return conexion;
    }
    
}
