package accesoadatos.soundwaveproject.controller;

import accesoadatos.soundwaveproject.App;
import accesoadatos.soundwaveproject.model.DAO.UsuarioDAO;
import accesoadatos.soundwaveproject.model.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;


public class ControllerEditUser {

    @FXML
    private ImageView backimg;

    @FXML
    private Button insertfoto;

    @FXML
    private ImageView photo;

    @FXML
    private TextField txtDni;

    @FXML
    private TextField txtMail;

    @FXML
    private TextField txtName;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Button updateAncout;

    @FXML
    void back(MouseEvent event) throws IOException {
        App.setRoot("userProfile");
    }

    @FXML
    void buscafoto(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar una imagen");

        // Configurar el filtro de extensión para imágenes
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Archivos de imagen (*.png, *.jpg, *.jpeg)", "*.png", "*.jpg", "*.jpeg");
        fileChooser.getExtensionFilters().add(extFilter);

        // Mostrar el diálogo de selección de archivo
        Stage stage = (Stage) insertfoto.getScene().getWindow(); // Obtener la ventana actual
        File selectedFile = fileChooser.showOpenDialog(stage);

        if (selectedFile != null) {
            // Actualizar la imagen en la interfaz gráfica
            Image image = new Image(selectedFile.toURI().toString());
            photo.setImage(image);

            // Puedes guardar la ruta del archivo seleccionado si es necesario
            String imagePath = selectedFile.getAbsolutePath();
            System.out.println("Imagen seleccionada: " + imagePath);
        } else {
            System.out.println("Selección de imagen cancelada");
        }
    }

    @FXML
    void update(ActionEvent event) {
        String newDni = txtDni.getText();
        String newName = txtName.getText();
        String newMail = txtMail.getText();
        String newPassword = txtPassword.getText();

        // Validar que los campos necesarios no estén vacíos
        if (newDni.isEmpty() || newName.isEmpty() || newMail.isEmpty() || newPassword.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Campos incompletos", "Por favor, complete todos los campos.");
            return;
        }

        // Crear un nuevo objeto Usuario con los datos actualizados
        Usuario usuarioActualizado = new Usuario(newDni, newName, newMail, newPassword, null /*foto*/);
        UsuarioDAO usuarioDAO = new UsuarioDAO(Usuario.class);
     boolean actualizacionExitosa = usuarioDAO.update(usuarioActualizado);

        if (actualizacionExitosa) {
            showAlert(Alert.AlertType.INFORMATION, "Éxito", "¡Cuenta actualizada con éxito!");
        } else {
            showAlert(Alert.AlertType.ERROR, "Error", "Error al actualizar la cuenta. Por favor, inténtelo de nuevo.");
        }
        // Puedes mostrar un mensaje de éxito al usuario
        System.out.println("¡Cuenta actualizada con éxito!");
        System.out.println("Botón de retroceso clicado");

    }

    @FXML
    private void back() throws IOException {
        App.setRoot("home");

    }

    private void showAlert(Alert.AlertType alertType, String éxito, String s) {
        Alert alert = new Alert(alertType);
        alert.setTitle(éxito);
        alert.setHeaderText(null);
        alert.setContentText(s);
        alert.showAndWait();
    }

}

