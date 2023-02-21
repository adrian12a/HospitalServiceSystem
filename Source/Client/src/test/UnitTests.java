package test;

import app.Main;
import app.ServerConnection;
import gui.ControllerLogin;
import javafx.embed.swing.JFXPanel;
import javafx.scene.control.TextField;
import org.junit.Test;

import static java.util.Objects.isNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Testy jednostkowe aplikacji klienta
 */
public class UnitTests {

    /**
     * Testowanie polaczenia z serwerem
     */
    @Test
    public void serverConnection() {
        ServerConnection server = Main.getServer();
        assertFalse(isNull(server.socket));
    }

    /**
     * Testowanie pobierania liczby typu double z gui
     */
    @Test
    public void getDoubleTest() {
        ControllerLogin controller = new ControllerLogin();
        JFXPanel fxPanel = new JFXPanel();

        TextField text = new TextField("5.0");
        assertEquals(5.0,controller.getDouble(text),0.0);

        text = new TextField("abc");
        assertEquals(0.0,controller.getDouble(text),0.0);

        text = new TextField();
        assertEquals(0.0,controller.getDouble(text),0.0);

        text = new TextField("a4.0");
        assertEquals(0.0,controller.getDouble(text),0.0);
    }

    /**
     * Testowanie pobierania liczby typu int z gui
     */
    @Test
    public void getIntTest() {
        ControllerLogin controller = new ControllerLogin();
        JFXPanel fxPanel = new JFXPanel();

        TextField text = new TextField("5");
        assertEquals(5,controller.getInt(text));

        text = new TextField("abc");
        assertEquals(0,controller.getInt(text));

        text = new TextField();
        assertEquals(0,controller.getInt(text));

        text = new TextField("a4");
        assertEquals(0,controller.getInt(text));
    }
}
