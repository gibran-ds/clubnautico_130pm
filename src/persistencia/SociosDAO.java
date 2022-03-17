package persistencia;

import entidades.Socio;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

public class SociosDAO implements ISociosDAO {

    private IConexionBD conexion;

    public SociosDAO(IConexionBD conexion) {
        this.conexion = conexion;
    }        
    
    @Override
    public boolean agregar(Socio socio) {
        try{
            // ESTABLECEMOS UNA CONEXIÓN A MYSQL CON LOS PARÁMETROS PROPORCIONADOS
            Connection conexion = this.conexion.crearConexion();
            
            // PERMITE ENVIAR CODIGO SQL AL SERVIDOR DE BD
            Statement comandoSQL = conexion.createStatement();
            
            String codigoSQL = String.format("INSERT INTO socios (nombre, curp) "
                    + "VALUES ('%s', '%s');",
                    socio.getNombre(),
                    socio.getCurp());
            
            // SIRVE PARA REALIZAR MODIFICACIONES DE DATOS (INSERT, DELETE, UPDATE)
            int numeroRegistrosModificados = comandoSQL.executeUpdate(codigoSQL);
            
            // CERRAMOS LA CONEXION PARA LIBERAR RECURSOS DEL SERVIDOR DE BD
            conexion.close();
            
            //return numeroRegistrosModificados == 1 ? true : false;
            return (numeroRegistrosModificados == 1);
                       
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
            return false;
        } 
    }

    @Override
    public boolean actualizar(Socio socio) {
        try{
            // ESTABLECEMOS UNA CONEXIÓN A MYSQL CON LOS PARÁMETROS PROPORCIONADOS
            Connection conexion = this.conexion.crearConexion();
            
            // PERMITE ENVIAR CODIGO SQL AL SERVIDOR DE BD
            Statement comandoSQL = conexion.createStatement();
            
            String codigoSQL = String.format(
                    "UPDATE socios SET nombre='%s',curp='%s' WHERE id_socio = %d",
                    socio.getNombre(),
                    socio.getCurp(),
                    socio.getId());
            
            // SIRVE PARA REALIZAR MODIFICACIONES DE DATOS (INSERT, DELETE, UPDATE)
            int numeroRegistrosModificados = comandoSQL.executeUpdate(codigoSQL);
            
            // CERRAMOS LA CONEXION PARA LIBERAR RECURSOS DEL SERVIDOR DE BD
            conexion.close();
            
            //return numeroRegistrosModificados == 1 ? true : false;
            return (numeroRegistrosModificados == 1);
                       
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminar(Long idSocio) {
        try{
            // ESTABLECEMOS UNA CONEXIÓN A MYSQL CON LOS PARÁMETROS PROPORCIONADOS
            Connection conexion = this.conexion.crearConexion();
            
            // PERMITE ENVIAR CODIGO SQL AL SERVIDOR DE BD
            Statement comandoSQL = conexion.createStatement();
            
            String codigoSQL = String.format(
                    "DELETE FROM socios WHERE id_socio = %d;",
                    idSocio);
            
            // SIRVE PARA REALIZAR MODIFICACIONES DE DATOS (INSERT, DELETE, UPDATE)
            int numeroRegistrosEliminados = comandoSQL.executeUpdate(codigoSQL);
            
            // CERRAMOS LA CONEXION PARA LIBERAR RECURSOS DEL SERVIDOR DE BD
            conexion.close();
            
            return (numeroRegistrosEliminados == 1);
                     
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public Socio consultar(Long idSocio) {
        Socio socio = null;
        try{
            Connection conexion = this.conexion.crearConexion();
            
            Statement comandoSQL = conexion.createStatement();
            
            String codigoSQL = String.format("SELECT id_socio, nombre, curp "
                    + "FROM socios WHERE id_socio = %d;",
                    idSocio);
            
            ResultSet resultado = comandoSQL.executeQuery(codigoSQL);
            
            if(resultado.next()){
                Long id = resultado.getLong("id_socio");
                String nombre = resultado.getString("nombre");
                String curp = resultado.getString("curp");
                socio = new Socio(id, nombre, curp);
            }
            
            conexion.close();
            return socio;
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
            return socio;
        }
    }

    @Override
    public List<Socio> consultarTodos() {
        List<Socio> listaSocios = new ArrayList<>();
        try{
            Connection conexion = this.conexion.crearConexion();
            
            Statement comandoSQL = conexion.createStatement();
            
            String codigoSQL = String.format("SELECT id_socio, nombre, curp FROM socios;");
            
            ResultSet resultado = comandoSQL.executeQuery(codigoSQL);
            
            while(resultado.next()){
                Long idSocio = resultado.getLong("id_socio");
                String nombre = resultado.getString("nombre");
                String curp = resultado.getString("curp");
                Socio socio = new Socio(idSocio, nombre, curp);
                listaSocios.add(socio);
            }
            
            conexion.close();
            return listaSocios;
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
            return listaSocios;
        }
    }
    
}
