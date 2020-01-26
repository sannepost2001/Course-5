package BioTools;

import java.awt.Color;
import java.util.regex.Pattern;

abstract class Sequentie {
//    public static abstract class Seq {
        String sequentie = "";

        public abstract Color getColor(int positie);

        int getLenght() {
            return sequentie.length();
        }
//    }

    static class Dna extends Sequentie {
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


    static class Rna extends Sequentie {
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

    static class Peptide extends Sequentie {
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
}