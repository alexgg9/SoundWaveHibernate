package accesoadatos.soundwaveproject.controller;

import accesoadatos.soundwaveproject.App;
import accesoadatos.soundwaveproject.model.Cancion;
import accesoadatos.soundwaveproject.model.DAO.DiscoDAO;
import accesoadatos.soundwaveproject.model.Disco;
import accesoadatos.soundwaveproject.utils.Utils;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.*;

public class DiscProfileController implements Initializable {

    @FXML
    private ImageView portadaDisco;
    @FXML
    private TextField searchField;
    @FXML
    private ListView<Cancion> canciones;
    @FXML
    private Label nombreDisco;
    @FXML
    private Label artistaDisco;
    @FXML
    private Label fechaDisco;
    @FXML
    private Label reproduccionesDisco;

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Label songLabel;
    @FXML
    private Button playButton, previousButton, nextButton;
    @FXML
    private Slider volumeSlider;
    @FXML
    private ProgressBar songProgressBar;

    private Media media;
    private MediaPlayer mediaPlayer;

    private File directory;
    private File[] files;
    private ArrayList<File> songs;
    private int songNumber;
    private Timer timer;
    private TimerTask task;
    private boolean running;

    private DiscoDAO discoDAO;
    private boolean isSongPlaying = false;
    private String selectedSongUrl;
    private String selectedSongName;

    public void initialize(URL arg0, ResourceBundle arg1) {
        discoDAO = new DiscoDAO(Disco.class);

        songs = new ArrayList<>();
        directory = new File(getClass().getResource("/songs").getFile());
        files = directory.listFiles();

        if (files != null) {
            songs.addAll(Arrays.asList(files));
            songs.forEach(file -> System.out.println(file));
        }

        volumeSlider.valueProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            if (mediaPlayer != null) {
                mediaPlayer.setVolume(volumeSlider.getValue() / 100.0);
            }
        });

        canciones.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Cancion> observable, Cancion oldValue, Cancion newValue) -> {
            if (newValue != null) {
                selectedSongUrl = newValue.getUrl();
                selectedSongName = newValue.getNombre();
                playSong(selectedSongUrl, selectedSongName);
            }
        });

    }

    private void playSong(String songURL, String songName) {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }

        try {
            Path path = Paths.get(songURL);
            String absolutePath = path.toAbsolutePath().toUri().toString();
            media = new Media(absolutePath);
            mediaPlayer = new MediaPlayer(media);
            songLabel.setText(songName);
            mediaPlayer.setVolume(volumeSlider.getValue() / 100.0); // Inicializar el volumen
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void btSearch() throws SQLException {
        searchDisco();
    }

    @FXML
    public void back() throws IOException {
        App.setRoot("home");
    }

    @FXML
    private void searchDisco() throws SQLException {
        String search = searchField.getText();
        Disco disco = discoDAO.getDiscoByNombre(search);

        if (disco != null) {
            nombreDisco.setText("Nombre: " + disco.getNombre());
            artistaDisco.setText("Artista: " + disco.getArtista().getNombre());
            fechaDisco.setText("Fecha Publicación: " + disco.getFechaPublicacion());
            reproduccionesDisco.setText("Reproducciones: " + disco.getReproduccion());
            Image image = Utils.convertBytesToArray(disco.getFoto());
            portadaDisco.setImage(image);

            List<Cancion> songs = discoDAO.getCancionesByDiscoId(disco.getId());
            canciones.getItems().clear();
            canciones.getItems().setAll(songs);
        } else {
            Utils.showPopUp("Disco", "Error", "No se encontró en la base de datos", Alert.AlertType.ERROR);
        }
    }

    public void previousMedia() {
        if (songNumber > 0) {
            songNumber--;
            mediaPlayer.stop();
            if (running) {
                cancelTimer();
            }
            media = new Media(songs.get(songNumber).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            songLabel.setText(songs.get(songNumber).getName());
            playMedia();
        } else {
            songNumber = songs.size() - 1;
            mediaPlayer.stop();
            if (running) {
                cancelTimer();
            }
            media = new Media(songs.get(songNumber).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            songLabel.setText(songs.get(songNumber).getName());
            playMedia();
        }
    }

    @FXML
    public void playMedia() {
        if (mediaPlayer != null) {
            if (!isSongPlaying) {
                playSong(selectedSongUrl, selectedSongName);
                playButton.setText("Pause");
                mediaPlayer.play();
                beginTimer();
                isSongPlaying = true;
            } else {
                playButton.setText("Play");
                mediaPlayer.pause();
                cancelTimer();
                isSongPlaying = false;
            }
        }
    }

    public void nextMedia() {
        if (songNumber < songs.size() - 1) {
            songNumber++;
            mediaPlayer.stop();
            if (running) {
                cancelTimer();
            }
            media = new Media(songs.get(songNumber).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            songLabel.setText(songs.get(songNumber).getName());
            playMedia();
        } else {
            songNumber = 0;
            mediaPlayer.stop();
            if (running) {
                cancelTimer();
            }
            media = new Media(songs.get(songNumber).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            songLabel.setText(songs.get(songNumber).getName());
            playMedia();
        }
    }

    public void beginTimer() {
        timer = new Timer();
        task = new TimerTask() {
            @Override
            public void run() {
                running = true;
                double current = mediaPlayer.getCurrentTime().toSeconds();
                double end = media.getDuration().toSeconds();
                System.out.println(current / end);
                songProgressBar.setProgress(current / end);
                if (current / end == 1) {
                    cancelTimer();
                }
            }
        };
        timer.scheduleAtFixedRate(task, 1000, 1000);
    }

    public void cancelTimer() {
        running = false;
        timer.cancel();
    }
}
