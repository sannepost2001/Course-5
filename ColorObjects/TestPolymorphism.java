package ColorObjects;

public class TestPolymorphism
{

    public static void main(String[] args)
    {
        GeometricObject[] figure = new GeometricObject[5];

        figure[0] = new Square(3);
        figure[1] = new Circle(3);
        figure[2] = new Square(4);
        figure[3] = new Circle(4);
        figure[4] = new Square(5);

        //Invoking howToColor() method
        ((Square) figure[0]).howToColor();
        ((Square) figure[2]).howToColor();
        ((Square) figure[4]).howToColor();

        for(int i=0;i<5;i++)
        {
            System.out.println("Figure - "+(i+1));
            System.out.println("Area : "+figure[i].getArea());
            System.out.println("Perimeter : "+figure[i].getPerimeter());
        }

    }

}
