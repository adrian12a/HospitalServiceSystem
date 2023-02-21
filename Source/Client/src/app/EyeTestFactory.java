package app;

/**
 * Klasa tworzaca badanie oczu.
 */
public class EyeTestFactory implements TestFactory {
    /**
     * Badanie.
     */
    private Test test;

    /**
     * Konstruktor.
     * @param patient               Pacjent
     * @param visionDefectLeft      Wada lewego oka
     * @param visionDefectRight     Wada prawego oka
     * @param pressureLeft          Cisnienie w lewym oku
     * @param pressureRight         Cisnienie w prawym oku
     * @param astigmatismLeft       Astygmatyzm w lewym oku (false - brak astygmatyzmu w lewym oku)
     * @param astigmatismRight      Astygmatyzm w prawym oku (false - brak astygmatyzmu w prawym oku)
     */
    public EyeTestFactory(Patient patient, double visionDefectLeft, double visionDefectRight, double pressureLeft, double pressureRight, boolean astigmatismLeft, boolean astigmatismRight) {
        String description = makeDescription(true,visionDefectLeft,pressureLeft,astigmatismLeft);
        description += " " + makeDescription(false,visionDefectRight,pressureRight,astigmatismRight);
        test = new Test(patient, description, "Badanie wzroku");
    }

    /**
     * Tworzenie opisu badania.
     * @param left          true - dla lewego oka, false - dla prawego oka
     * @param visionDefect  Wada wzroku
     * @param pressure      Cisnienie w oku
     * @param astigmatism   Astygmatyzm (false - brak astygmatyzmu)
     * @return Opis badania
     */
    String makeDescription(boolean left, double visionDefect, double pressure, boolean astigmatism) {
        String description = "";
        if(left)
            description += "Lewe oko: ";
        else
            description += "Prawe oko: ";
        if(visionDefect != 0.0)
            description += "wada " + visionDefect;
        else
            description += "brak wady";
        description += ", ciśnienie " + pressure + " mmHG";
        if(astigmatism)
            description += ", astygmatyzm";
        return description;
    }

    /**
     * Pobranie badania.
     * @return Badanie
     */
    @Override
    public Test makeTest() {
        return test;
    }
}