import java.sql.*;

public class OperacionesBD {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/soportes";
        String user = "root";
        String password = "";

        try {
            Connection conexion = DriverManager.getConnection(url, user, password);

            if (conexion != null) {
                System.out.println("Conexión exitosa");

                // Inserción en la tabla usuarios
                String insercionUsuariosSQL = "INSERT INTO usuarios (nombre, email, contrasena) VALUES (?, ?, ?)";
                PreparedStatement insercionUsuarios = conexion.prepareStatement(insercionUsuariosSQL);
                insercionUsuarios.setString(1, "NombreUsuario");
                insercionUsuarios.setString(2, "usuario@dominio.com");
                insercionUsuarios.setString(3, "Contraseña123");
                insercionUsuarios.executeUpdate();
                System.out.println("Inserción de usuario exitosa");

                // Inserción en la tabla solicitudes_de_soporte
                String insercionSolicitudesSQL = "INSERT INTO solicitudes_de_soporte (usuario_id, asunto, descripcion) VALUES (?, ?, ?)";
                PreparedStatement insercionSolicitudes = conexion.prepareStatement(insercionSolicitudesSQL);
                insercionSolicitudes.setInt(1, 1); // Aquí asumimos que el ID del usuario recién insertado es 1
                insercionSolicitudes.setString(2, "Asunto de la Solicitud");
                insercionSolicitudes.setString(3, "Descripción detallada de la solicitud");
                insercionSolicitudes.executeUpdate();
                System.out.println("Inserción de solicitud de soporte exitosa");

                // Consulta de usuarios
                String consultaUsuariosSQL = "SELECT * FROM usuarios";
                Statement consultaUsuarios = conexion.createStatement();
                ResultSet resultadosUsuarios = consultaUsuarios.executeQuery(consultaUsuariosSQL);
                while (resultadosUsuarios.next()) {
                    System.out.println("ID: " + resultadosUsuarios.getInt("id") + ", Nombre: " + resultadosUsuarios.getString("nombre") + ", Email: " + resultadosUsuarios.getString("email"));
                }

                // Consulta de solicitudes_de_soporte
                String consultaSolicitudesSQL = "SELECT * FROM solicitudes_de_soporte";
                Statement consultaSolicitudes = conexion.createStatement();
                ResultSet resultadosSolicitudes = consultaSolicitudes.executeQuery(consultaSolicitudesSQL);
                while (resultadosSolicitudes.next()) {
                    System.out.println("ID: " + resultadosSolicitudes.getInt("id") + ", Asunto: " + resultadosSolicitudes.getString("asunto") + ", Descripción: " + resultadosSolicitudes.getString("descripcion"));
                }

                conexion.close();
            }
        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
        }
    }
}
