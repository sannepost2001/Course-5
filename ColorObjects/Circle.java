package ColorObjects;

public class Circle extends GeometricObject
{
    private double radius;

    public Circle(double radius)
    {
        this.radius = radius;
    }

    double getArea()
    {
        return 3.14*radius*radius;
    }

    double getPerimeter()
    {
        return 2*3.14*radius;
    }


}