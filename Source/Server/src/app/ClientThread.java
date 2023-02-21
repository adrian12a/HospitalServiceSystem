package app;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Watek klienta.
 */
public class ClientThread implements Runnable {
    /**
     * Gniazdo
     */
    private Socket socket;
    /**
     *  Strumien wyjsciowy
     */
    private ObjectOutputStream out;
    /**
     * Strumien wejsciowy
     */
    private ObjectInputStream in;
    /**
     * Identyfikator uzytkownika
     */
    private int userId;
    /**
     * Obiekt obslugujacy wiadomosci (wzorzec Strategia)
     */
    public Receiver receiver;

    /**
     * Konstruktor
     * @param socket Gniazdo
     */
    public ClientThread(Socket socket) {
        userId = -1;
        this.socket = socket;
        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
        }
        catch(IOException e) {
            e.printStackTrace();
            Log.error("Błąd wątku klienta");
        }
    }

    /**
     * Odbieranie wiadomosci od klienta w petli.
     */
    @Override
    public void run() {
        try {
            while (true) {
                Object response = receiveMessage(in.readObject());
                out.writeObject(response);
            }
        } catch (ClassNotFoundException | IOException e) {
        } finally {
            Log.info("Klient rozlaczyl sie z serwerem");
            try {
                if (socket != null) {
                    socket.close();
                }
                if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.close();
                }
            }
            catch(Exception e) {
                Log.error("Błąd wątku klienta");
                e.printStackTrace();
            }
        }
    }

    /**
     * Ustawienie obiektu obslugujacego wiadomosci.
     * @param r Obiekt klasy implementujacej interfejs Receiver
     */
    public void setReceiver(Receiver r) {
        receiver = r;
    }

    /**
     * Odebranie wiadomosci.
     * @param o Wiadomosc
     * @return  Wiadomosc zwrotna
     */
    public Object receiveMessage(Object o) {
        Log.info("Otrzymano wiadomosc od klienta - obiekt " + o.getClass().toString());
        if(o instanceof Account) {
            setReceiver(new AccountReceiver());
            int id = (int) receiver.receive(o,getUserId());
            String result = "";
            if(id != -1) {
                setUserId(id);
                result = Main.getDatabase().selectOne("SELECT typ FROM Pracownicy WHERE id = '" + id + "';","typ");
            }
            Log.info("Wysylanie wiadomosci zwrotnej do klienta " + result);
            return result;
        }
        if(o instanceof Data) {
            setReceiver(new DataReceiver());
            Object result = receiver.receive(o,getUserId());
            Log.info("Wysylanie wiadomosci zwrotnej do klienta " + result.toString());
            return result;
        }
        if(o instanceof Test) {
            setReceiver(new TestReceiver());
            Object result = receiver.receive(o,getUserId());
            Log.info("Wysylanie wiadomosci zwrotnej do klienta " + result.toString());
            return result;
        }
        if(o instanceof Archive) {
            setReceiver(new ArchiveReceiver());
            Object result = receiver.receive(o,getUserId());
            Log.info("Wysylanie wiadomosci zwrotnej do klienta " + result.toString());
            return result;
        }
        if(o instanceof Prescription) {
            setReceiver(new PrescriptionReceiver());
            Object result = receiver.receive(o,getUserId());
            Log.info("Wysylanie wiadomosci zwrotnej do klienta " + result.toString());
            return result;
        }
        Log.error("Otrzymano niezidentyfikowana wiadomosc od klienta");
        return null;
    }

    /**
     * Ustawienie indentyfikatora aktualnie zalogowanego uzytkownika.
     * @param id    Identyfikator uzytkownika
     */
    void setUserId(int id) {
        userId = id;
    }

    /**
     * Pobranie indentyfikatora aktualnie zalogowanego uzytkownika.
     * @return  Identyfikator uzytkownika
     */
    int getUserId() {
        return userId;
    }
}
