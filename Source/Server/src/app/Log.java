package app;

import org.apache.log4j.Logger;

/**
 * Klasa sluzaca do tworzenia logow. Logi sa zapisywane do pliku (Server->logs->logs.txt), wyswietlane ne konsoli oraz w gui.
 */
public class Log {
    /**
     * Obiekt logujacy do pliku i do konsoli.
     */
    final private static Logger logger = Logger.getLogger(Log.class);

    /**
     * Stworzenie loga informujacego.
     * @param text Tresc loga
     */
    public static void info(String text) {
        logger.info(text);
        if(Main.getController() != null)
            Main.getController().log("INFO  " + text);
    }

    /**
     * Stworzenie loga o bledzie.
     * @param text Tresc loga
     */
    public static void error(String text) {
        logger.error(text);
        if(Main.getController() != null)
            Main.getController().log("ERROR  " + text);
    }
}