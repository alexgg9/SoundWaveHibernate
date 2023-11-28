package accesoadatos.soundwaveproject.controller;

import accesoadatos.soundwaveproject.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

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
        fileChooser.setTitle("Seleccionar Foto");

        // Filtros para el tipo de archivo (en este caso, imágenes)
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Archivos de Imagen", "*.png", "*.jpg", "*.jpeg", "*.gif")
        );

        // Mostrar el cuadro de diálogo
        Stage stage = (Stage) insertfoto.getScene().getWindow(); // Puedes usar cualquier control en tu escena
        java.io.File selectedFile = fileChooser.showOpenDialog(stage);

        if (selectedFile != null) {
            // Aquí puedes hacer algo con el archivo seleccionado, como mostrar la imagen en tu interfaz gráfica
            System.out.println("Archivo seleccionado: " + selectedFile.getAbsolutePath());
        }
    }
    @FXML
    void update(ActionEvent event) {

    }

}

