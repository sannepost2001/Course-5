package translate;

/** Translator voor de opdracht One2Three
 * Gebruik deze code ongewijzigd om een
 * vertaler te maken van de eenletterige code
 * voor een aminozuur naar de drieletterige
 * code van een aminozuur.
 * @Author Martijn van der Bruggen
 * @Date  1-september-2019
 */


public class Translator {

    static final String[] ONE = {"A", "R", "N", "D", "C", "Q", "E", "G", "H", "I", "L", "K"
            , "M", "F", "P", "S", "T", "W", "Y", "V"};
    static final String[] THREE = {"ALA", "ARG", "ASN", "ASP", "CYS", "GLN", "GLU", "GLY"
            ,"HIS", "ILE", "LEU", "LYS", "MET", "PHE", "PRO", "SER"
            ,"THR", "TRP", "TYR", "VAL"
    };

    public static StringBuilder one2three(String symbol) throws NotAnAA {
        String threeCode = "";
        StringBuilder translation = new StringBuilder();
        for(int i = 0; i < symbol.length(); i++) {
            char one = symbol.charAt(i);
            String s = String.valueOf(one);

            for (int y = 0; y < ONE.length; y++) {
                if (ONE[y].equals(s)) {
                    threeCode = THREE[y];
                    translation.append("-").append(threeCode);
                }

            }
            if (threeCode.equals("")) {
                throw new NotAnAA("Dit is een niet bestaand aminozuur: " + symbol);
            }
        }
        return translation;
    }
}

class NotAnAA extends Exception {

    public NotAnAA() {
        super();
    }

    public NotAnAA(String err) {
        super(err);
    }
}