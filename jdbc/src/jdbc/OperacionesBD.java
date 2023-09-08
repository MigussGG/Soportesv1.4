package jdbc;

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
                PreparedStatement insercionUsuarios = conexion.prepareStatement(insercionUsuariosSQL, Statement.RETURN_GENERATED_KEYS);
                insercionUsuarios.setString(1, "Juan Perez");
                insercionUsuarios.setString(2, "juanperez@example.com");
                insercionUsuarios.setString(3, "clave123");
                insercionUsuarios.executeUpdate();
                System.out.println("Inserción de usuario exitosa");

                // Obtener el ID del usuario recién insertado
                ResultSet generatedKeys = insercionUsuarios.getGeneratedKeys();
                int usuarioId = -1;
                if (generatedKeys.next()) {
                    usuarioId = generatedKeys.getInt(1);
                }

                // Inserción en la tabla solicitudes_de_soporte
                String insercionSolicitudesSQL = "INSERT INTO solicitudes_de_soporte (usuario_id, asunto, descripcion) VALUES (?, ?, ?)";
                PreparedStatement insercionSolicitudes = conexion.prepareStatement(insercionSolicitudesSQL);
                insercionSolicitudes.setInt(1, usuarioId);
                insercionSolicitudes.setString(2, "Problema con la impresora");
                insercionSolicitudes.setString(3, "La impresora no imprime correctamente");
                insercionSolicitudes.executeUpdate();
                System.out.println("Inserción de solicitud de soporte exitosa");

                // Consulta de usuarios
                String consultaUsuariosSQL = "SELECT * FROM usuarios";
                Statement consultaUsuarios = conexion.createStatement();
                ResultSet resultadosUsuarios = consultaUsuarios.executeQuery(consultaUsuariosSQL);
                while (resultadosUsuarios.next()) {
                    System.out.println("ID: " + resultadosUsuarios.getInt("id") + ", Nombre: " + resultadosUsuarios.getString("nombre") + ", Email: " + resultadosUsuarios.getString("email"));
                }
                System.out.println("Consulta de usuarios exitosa");

                // Consulta de solicitudes_de_soporte
                String consultaSolicitudesSQL = "SELECT * FROM solicitudes_de_soporte";
                Statement consultaSolicitudes = conexion.createStatement();
                ResultSet resultadosSolicitudes = consultaSolicitudes.executeQuery(consultaSolicitudesSQL);
                while (resultadosSolicitudes.next()) {
                    System.out.println("ID: " + resultadosSolicitudes.getInt("id") + ", Asunto: " + resultadosSolicitudes.getString("asunto") + ", Descripción: " + resultadosSolicitudes.getString("descripcion"));
                }
                System.out.println("Consulta de solicitudes de soporte exitosa");

                // Actualización de usuarios
                String actualizacionUsuariosSQL = "UPDATE usuarios SET nombre = ? WHERE id = ?";
                PreparedStatement actualizacionUsuarios = conexion.prepareStatement(actualizacionUsuariosSQL);
                actualizacionUsuarios.setString(1, "Juanito Pérez");
                actualizacionUsuarios.setInt(2, 1);
                actualizacionUsuarios.executeUpdate();
                System.out.println("Actualización de usuario exitosa");

                // Actualización de solicitudes_de_soporte
                String actualizacionSolicitudesSQL = "UPDATE solicitudes_de_soporte SET asunto = ? WHERE id = ?";
                PreparedStatement actualizacionSolicitudes = conexion.prepareStatement(actualizacionSolicitudesSQL);
                actualizacionSolicitudes.setString(1, "Nuevo asunto");
                actualizacionSolicitudes.setInt(2, 1);
                actualizacionSolicitudes.executeUpdate();
                System.out.println("Actualización de solicitud de soporte exitosa");

                // Eliminación de usuarios
                String eliminacionUsuariosSQL = "DELETE FROM usuarios WHERE id = ?";
                PreparedStatement eliminacionUsuarios = conexion.prepareStatement(eliminacionUsuariosSQL);
                eliminacionUsuarios.setInt(1, 1); // Supongamos que quieres eliminar el usuario con ID 1
                eliminacionUsuarios.executeUpdate();
                System.out.println("Eliminación de usuario exitosa");

                // Eliminación de solicitudes_de_soporte
                String eliminacionSolicitudesSQL = "DELETE FROM solicitudes_de_soporte WHERE id = ?";
                PreparedStatement eliminacionSolicitudes = conexion.prepareStatement(eliminacionSolicitudesSQL);
                eliminacionSolicitudes.setInt(1, 1); // Supongamos que quieres eliminar la solicitud con ID 1
                eliminacionSolicitudes.executeUpdate();
                System.out.println("Eliminación de solicitud de soporte exitosa");

                conexion.close();
            }
        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
        }
    }
}
