//package tentame;
//
//public class Chromosoom {
//    private int totalLength = 0;
//    private int longest = 0;
//    private int shortest = 0;
//    private int geneCount = 0;
//    private int chrNumber = 0;
//
//    public Chromosoom(int i) {
//    }
//
//    public int addGene(int lengtechr) {
//        geneCount++;
//        totalLength += lengtechr;
//        return totalLength;
//    }
//
//    public int getLongest() {
//        return longest;}
//
//    public int getShortest(){return shortest;}
//
//    public float getAverageGenLength(){ return 0;}
//
//    public String getChromosoomNumber(){return "nummer";}
//
//    }

package tentame;

public class Chromosoom {
    private int totalLength = 0;
    private int longest = 0;
    private int shortest = 99999999;
    private int geneCount = 0;
    private int chrNumber = 0;

    public Chromosoom(int i) {
        chrNumber=i;
    }

    public int addGene(int lengtechr) {
        geneCount++;
        totalLength += lengtechr;
        System.out.println(geneCount + "hoi "+ totalLength);
        if (lengtechr < shortest){
            shortest=lengtechr;
        }
        if (lengtechr> longest){
            longest=lengtechr;
        }
        return totalLength;

    }
    public int getLongest(){return longest;}

    public int getShortest(){return shortest;}

    public float getAverageGenLength(){
        return totalLength/geneCount ;
    }

    public String getChromosoomNumber(){return "nummer";}
    public String _print(){
        return ("Chromosoom :" + chrNumber +" Lengte :" + totalLength + " Kortste:" + shortest + " Langste :"+longest + " Aantal :"+geneCount + " Gemiddelde :"+getAverageGenLength());
    }
}