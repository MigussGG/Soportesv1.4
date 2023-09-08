import java.sql.*;

public class VerificarConexion {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/soportesdb";
        String user = "root";
        String password = "Sistemas1996*";

        try {
            Connection conexion = DriverManager.getConnection(url, user, password);

            if (conexion != null) {
                System.out.println("Conexión exitosa");

                // Puedes continuar con tus operaciones en la base de datos aquí

                conexion.close(); // No olvides cerrar la conexión cuando hayas terminado
            }
        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
        }
    }
}
