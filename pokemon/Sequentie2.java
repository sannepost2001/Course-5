package pokemon;

import java.awt.Color;
import java.util.regex.Pattern;

abstract class Sequentie2 {
    String sequentie = "";

    public abstract Color getColor(int positie);

    int getLenght() {
        return sequentie.length();
    }

    static class Dna extends Sequentie2 {
        Dna(String Sequentietext) {
            super.sequentie = Sequentietext;
        }

        @Override
        public Color getColor(int positie) {
            char one = super.sequentie.charAt(positie);
            if (Pattern.matches("[GC]+", String.valueOf(one))) {
                return new Color(255, 0, 0);
            } else {
                return new Color(255, 255, 0);

            }
        }
    }


    static class Rna extends Sequentie2 {
        Rna(String Sequentietext) {
            super.sequentie = Sequentietext;
        }

        @Override
        public Color getColor(int positie) {
            char one = super.sequentie.charAt(positie);
            if (Pattern.matches("[GC]+", String.valueOf(one))) {
                return new Color(255, 0, 0);
            } else {
                return new Color(0, 0, 255);

            }
        }
    }

    static class Peptide extends Sequentie2 {
        Peptide(String Sequentietext) {
            super.sequentie = Sequentietext;
        }

        @Override
        public Color getColor(int positie) {
            char one = super.sequentie.charAt(positie);
            // Negatief
            if (Pattern.matches("[NQDCE]+", String.valueOf(one))) {
                return new Color(0, 0, 255);
            }
            // Apolaire
            else if (Pattern.matches("[AFGILMPWYV]+", String.valueOf(one))) {
                return new Color(143, 143, 143);
            }
            // Positief
            else if (Pattern.matches("[RHKST]+", String.valueOf(one))) {
                return new Color(255, 0, 0);
            } else {
                return null;
            }
        }
    }

    static class Pokemon extends Sequentie2{

        Pokemon(String Sequentietext) {
            super.sequentie = Sequentietext;
        }

        @Override
        public Color getColor(int positie) {
            return null;
        }
    }
}