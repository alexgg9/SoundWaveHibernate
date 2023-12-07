package accesoadatos.soundwaveproject.controller;

import accesoadatos.soundwaveproject.App;
import accesoadatos.soundwaveproject.model.DAO.UsuarioDAO;
import accesoadatos.soundwaveproject.model.Usuario;
import accesoadatos.soundwaveproject.model.singleton.UserSession;
import accesoadatos.soundwaveproject.utils.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
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
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;


public class ControllerEditUser {


    @FXML
    private Button insertfoto;

    @FXML
    private ImageView photo;


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

    private String imagePath = null;
    @FXML
    void buscafoto(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar una imagen");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Archivos de imagen (*.png, *.jpg, *.jpeg)", "*.png", "*.jpg", "*.jpeg");
        fileChooser.getExtensionFilters().add(extFilter);
        Stage stage = (Stage) insertfoto.getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(stage);

        if (selectedFile != null) {
            imagePath = selectedFile.getAbsolutePath();
            Image image = new Image(selectedFile.toURI().toString());
            photo.setImage(image);
            System.out.println("Imagen seleccionada: " + imagePath);
        } else {
            System.out.println("Selección de imagen cancelada");
        }
    }

    @FXML
    void update(ActionEvent event) {
        String userDni = UserSession.getInstance().getUsuarioActual().getDni();
        String newName = txtName.getText();
        String newMail = txtMail.getText();
        String newPassword = txtPassword.getText();
        newPassword = Utils.encryptSHA256(newPassword);

        if (newName.isEmpty() || newMail.isEmpty() || newPassword.isEmpty()) {
            Utils.showPopUp("Error", "Campos incompletos", "Por favor, complete todos los campos.", Alert.AlertType.WARNING);
            return;
        }

        Usuario usuarioActualizado;

        if (imagePath != null) {
            File selectedFile = new File(imagePath);
            byte[] fileBytes = null;
            try {
                fileBytes = Files.readAllBytes(selectedFile.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }

            usuarioActualizado = new Usuario(userDni, newName, newMail, newPassword, fileBytes);
            Image image = Utils.convertBytesToArray(fileBytes);
            if (image != null) {
                photo.setImage(image);
            } else {
                photo.setImage(null);
                System.out.println("No se pudo cargar la imagen.");
            }
        } else {
            usuarioActualizado = new Usuario(userDni, newName, newMail, newPassword, null /*foto*/);
        }

        UsuarioDAO usuarioDAO = new UsuarioDAO(Usuario.class);
        boolean actualizacionExitosa = usuarioDAO.update(usuarioActualizado);

        if (actualizacionExitosa) {
            Utils.showPopUp("Éxito", "Se ha realizado correctamente", "¡Cuenta actualizada con éxito!", Alert.AlertType.INFORMATION);
        } else {
            Utils.showPopUp("Error", "Ha fallado la actualización", "Por favor, inténtelo de nuevo.", Alert.AlertType.ERROR);
        }
    }


    @FXML
    private void back() throws IOException {
        App.setRoot("home");
    }


}


