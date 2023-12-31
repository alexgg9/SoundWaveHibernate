module SoundWaveProject {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires java.sql;
    requires java.xml.bind;
    requires java.persistence;
    requires org.hibernate.orm.core;
    requires javafx.media;


    opens accesoadatos.soundwaveproject.controller to javafx.fxml;
    opens accesoadatos.soundwaveproject.model.Connection to java.xml.bind;
    opens accesoadatos.soundwaveproject.model;
    exports accesoadatos.soundwaveproject;
    exports accesoadatos.soundwaveproject.model;
    exports accesoadatos.soundwaveproject.controller;
}