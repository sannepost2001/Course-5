// GeometricObject.java: The abstract GeometricObject class
package ColorObjects;
public abstract class GeometricObject {
    private String color = "white";
    private boolean filled;
    /**Default constructor*/
    protected GeometricObject() {
    }
    /**Construct a geometric object*/
    protected GeometricObject(String color, boolean filled) {
        this.color = color;
        this.filled = filled;
    }
    /**Get method for color*/
    public String getColor() {
        return color;
    }
    /**Set method for color*/
    public void setColor(String color) {
        this.color = color;
    }
    /**Get method for filled. Since filled is boolean,
     so, the get method name is isFilled*/
    public boolean isFilled() {
        return filled;
    }
    /**Set method for filled*/
    public void setFilled(boolean filled) {
        this.filled = filled;
    }

    /**
     * Abstract methods getArea()
     * @return
     */
    abstract double getArea();
    /***
     * Abstract method getPerimeter();
     */
    abstract double getPerimeter();
}