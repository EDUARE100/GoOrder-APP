
package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    //Conexion local
    //Metodo
    public static Connection conectar() {

        try {
            Connection cn = DriverManager.getConnection(
            "jdbc:mysql://127.0.0.1:3306/bd_sistema_ventas?useSSL=false&serverTimezone=UTC", 
            "root", 
            "120104edu"
        );
            return cn;
        } catch (SQLException e) {
            System.out.println("Error en la conexion local " + e);
        }
        return null;
    }

}
