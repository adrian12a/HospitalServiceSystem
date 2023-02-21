package app;

import javafx.scene.paint.Color;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Klasa reprezentujaca serwer.
 */
public class Server implements Runnable {
    /**
     * Port na ktorym serwer jest uruchomiony.
     */
    final private Integer port;

    /**
     * Konstruktor.
     * @param port  Numer portu
     */
    public Server(int port) {
        this.port = port;
        Main.getController().serverStatus("Uruchomiony", Color.GREEN);
    }

    /**
     * Serwer - laczenie z klientami.
     */
    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            while(true) {
                Socket client = serverSocket.accept();
                Log.info("Nowy klient podlaczyl sie do serwera");
                Main.executor.execute(new ClientThread(client));
            }
        } catch (IOException e) {
            Main.getController().serverStatus("Wystąpił błąd", Color.RED);
            e.printStackTrace();
            Log.error("Blad serwera " + e.getMessage());
        }
    }
}