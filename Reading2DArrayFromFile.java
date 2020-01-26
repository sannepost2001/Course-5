//import java.io.BufferedReader;
//import java.io.FileReader;
//
//import java.util.Arrays;
//import java.util.Scanner;
//
//public class Reading2DArrayFromFile {
//    public static void main(String args[]) throws Exception {
//        Scanner sc = new Scanner(new BufferedReader(new FileReader("C:\\Users\\SannePost\\Documents\\pokedb")));
//        int rows = 4;
//        int columns = 4;
//        int [][] myArray = new int[rows][columns];
//        while(sc.hasNextLine()) {
//            for (int i=0; i<myArray.length; i++) {
//                String[] line = sc.nextLine().trim().split(" ");
//                for (int j=0; j<line.length; j++) {
//                    myArray[i][j] = Integer.parseInt(line[j]);
//                }
//            }
//        }
//        System.out.println(Arrays.deepToString(myArray));
//    }
//}
//
//import java.util.Arrays;
//        import java.util.Collections;
//
//        import org.apache.commons.lang.ArrayUtils;
//
//public class MinMaxValue {
//
//    public static void main(String[] args) {
//        char[] a = {'3', '5', '1', '4', '2'};
//
//        List b = Arrays.asList(ArrayUtils.toObject(a));
//
//        System.out.println(Collections.min(b));
//        System.out.println(Collections.max(b));
//    }
//}