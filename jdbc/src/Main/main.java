package jdbc;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/soportes";
        String user = "root";
        String password = "";

        try {
            OperacionesBD operacionesBD = new OperacionesBD(url, user, password);

            if (operacionesBD != null) {
                System.out.println("Conexión exitosa");

                operacionesBD.insertarUsuario("Juan Perez", "juanperez@example.com", "clave123");
                operacionesBD.consultarUsuarios();
                operacionesBD.actualizarUsuario(1, "Juanito Pérez");
                operacionesBD.eliminarUsuario(1);

                operacionesBD.cerrarConexion();
            }
        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
        }
    }
}
