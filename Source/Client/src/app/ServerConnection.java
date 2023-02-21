package app;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Polaczenie z serwerem.
 */
public class ServerConnection {
    /**
     * Gniazdo.
     */
    public Socket socket;
    /**
     * Strumien wejsciowy.
     */
    public ObjectInputStream in;
    /**
     * Strumien wyjsciowy.
     */
    private ObjectOutputStream out;
    /**
     * Nazwa hosta.
     */
    final private String host;
    /**
     * Port.
     */
    final private Integer port;

    /**
     * Konstruktor.
     * @param host  Nazwa hosta
     * @param port  Port
     */
    ServerConnection(String host,Integer port) {
        this.host = host;
        this.port = port;
        connect();
    }

    /**
     * Polaczenie z serwerem.
     */
    void connect() {
        try {
            socket = new Socket(host,port);
            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());
            Log.info("Pomyslnie polaczono z serwerem");
        }
        catch (IOException e) {
            e.printStackTrace();
            Log.error("Blad laczenia z serwerem " + e.getMessage());
        }
    }

    /**
     * Zamkniecie polaczenia z serwerem.
     */
    void disconnect() {
        try {
            socket.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Wyssylanie wiadomosci do serwera.
     * @param o Wiadomosc - obiekt dowolnej klasy, ktory moze byc zserializowany
     */
    public void sendMessage(Object o) {
        try {
            out.writeObject(o);
        } catch (IOException e) {
            e.printStackTrace();
            Log.error("Blad wysylania wiadomosci " + e.getMessage());
        }
    }

    /**
     * Odbieranie wiadomosci od serwera.
     * @return Wiadomosc od serwera
     */
    public Object receiveMessage() {
        Object result = null;
        try {
            result = Main.getServer().in.readObject();
            if(result != null)
                Log.info("Otrzymano odpowiedz od serwera: " + result.toString());
            else
                Log.info("Otrzymano pusta wiadomosc od serwera");
            } catch (Exception e) {
                e.printStackTrace();
                Log.error("Błąd odbierania wiadomości z serwera");
            }
            return result;
    }
}
