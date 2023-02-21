package gui;

import app.Main;
import app.Server;
import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;

/**
 * Kontroler gui.
 */
public class MainController {

    /**
     * Obszar tekstowy sluzacy do wyswietlania logow.
     */
    public TextArea textArea;
    /**
     * Przycisk uruchamiajacy serwer.
     */
    public JFXButton startButton;
    /**
     * Przycisk wylaczajacy serwer.
     */
    public JFXButton stopButton;
    /**
     * Pole tekstowe - aktualny status serwera.
     */
    public Label serverStatus;
    /**
     * Pole tekstowe - status polaczenia z baza danych.
     */
    public Label databaseStatus;

    /**
     * Logi.
     */
    private String input = "";

    /**
     * Gdy nacisnieto przycisk startButton, uruchomienie serwera.
     */
    public void startButtonPressed() {
        startButton.setDisable(true);
        stopButton.setDisable(false);
        Main.getExecutor().execute(new Server(44444));
        Main.getDatabase().connect();
    }

    /**
     * Gdy nacisnieto przycisk stopButton, wylaczenie serwera.
     */
    public void stopButtonPressed() {
        stopButton.setDisable(true);
        serverStatus("Wyłączony",Color.ORANGE);
        Main.getDatabase().disconnect();
        Main.getExecutor().shutdown();
    }

    /**
     * Ustawienie statusu serwera.
     * @param status    Status.
     * @param color     Kolor statusu.
     */
    public void serverStatus(String status, Color color) {
        serverStatus.setText(status);
        serverStatus.setTextFill(color);
    }

    /**
     * Ustawienie statusu polaczenia z baza danych.
     * @param status    Status.
     * @param color     Kolor statusu.
     */
    public void databaseStatus(String status, Color color) {
        databaseStatus.setText(status);
        databaseStatus.setTextFill(color);
    }

    /**
     * Dodanie logu do wyswietlenia.
     * @param text  Tresc logu
     */
    public void log(String text) {
        input = input + text + "\n";
        Platform.runLater(() -> textArea.setText(input));
    }

}
