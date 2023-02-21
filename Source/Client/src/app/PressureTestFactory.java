package app;

/**
 * Klasa tworzaca badanie - pomiar cisnienia.
 */
public class PressureTestFactory implements TestFactory {
    /**
     * Badanie.
     */
    private Test test;

    /**
     * Konstruktor.
     * @param patient                   Pacjent
     * @param systolicPressureValue     Cisnienie skurczowe
     * @param diastolicPressureValue    Cisnienie rozkurczowe
     * @param pulseValue                Puls
     */
    public PressureTestFactory(Patient patient, double systolicPressureValue, double diastolicPressureValue, double pulseValue) {
        String description = makeDescription(systolicPressureValue, diastolicPressureValue, pulseValue);
        test = new Test(patient,description,"Pomiar ciśnienia");
    }

    /**
     * Tworzenie opisu badania.
     * @param systolicPressureValue     Cisnienie skurczowe
     * @param diastolicPressureValue    Cisnienie rozkurczowe
     * @param pulseValue                Puls
     * @return  Opis badania
     */
    private String makeDescription(double systolicPressureValue, double diastolicPressureValue, double pulseValue) {
        String result = "Brak danych";
        if(systolicPressureValue != 0.0 && diastolicPressureValue != 0.0) {
            result = "Wartość ciśnienia: " + systolicPressureValue + " na " + diastolicPressureValue;
            if (systolicPressureValue > 140 || diastolicPressureValue > 90)
                result += ", ciśnienie powyżej normy";
            else
                result += ", ciśnienie w normie";
        }
        if(pulseValue != 0.0) {
            result += ", puls: " + pulseValue;
            if (pulseValue > 90)
                result += ", puls powyżej normy";
            else
                result += ", puls w normie";
        }
        return result;
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
