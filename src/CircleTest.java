/*
 * TCSS 305 - Winter 2016
 * Assignment 1 - Testing
 */
import static org.junit.Assert.*;

import java.awt.Color;
import java.awt.Point;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * A class for unit testing the circle class in order to find buggy behavior.
 * 
 * @author Matthew Wu
 * @version 1.8
 */
public class CircleTest {
    
    /** A tolerance used when comparing double values for equality. */
    private static final double TOLERANCE = .000001;
    
    /** A set radius used when comparing double values for equality. */
    private static final double RADIUS = 1.0;

    /** A point used when comparing points for equality. */
    private static final Point CENTER = new Point(0, 0);
    
    /** A set color used when colors for equality. */
    private static final Color COLOR = Color.BLACK;
    
    /** For testing exceptions. */
    @Rule
    public ExpectedException myThrown;
    
    // The test fixture. The Objects I will use in the tests.
    /** A circle to use in the tests. */
    private Circle myCircle;
    
    /**A larger circle used in the tests. */
    private Circle myLargeCircle;
       
    /**
     * @throws java.lang.Exception
     */
    @Before
    // This method runs before each test method.
    public void setUp() {
        myCircle = new Circle();
        myLargeCircle = new Circle(300.5, CENTER, Color.RED);
        myThrown = ExpectedException.none();
    }

    /**
     * Test method for default constructor.
     */
    @Test
    public void testDefaultConstructor() { // change before submit 
        assertEquals(RADIUS, myCircle.getRadius(), TOLERANCE);
        assertEquals(CENTER, myCircle.getCenter());
        assertEquals(Color.BLACK, myCircle.getColor());    
    }
    
    /**
     * Test method for overloaded constructor.
     */
    @Test
    public void testOverloadedConstructor() { 
        final Point p1 = new Point(10, 10);
        final Circle c1 = new Circle(25, p1, Color.BLUE);
        assertEquals(25, c1.getRadius(), TOLERANCE);
        assertEquals(p1, c1.getCenter());
        assertEquals(Color.BLUE, c1.getColor());
    }

    /**
     * Test method to check Illegal Argument Exception for negative radius.
     */    
    @Test
    public void testOverloadedConstructorNegativeException() { 
        myThrown.expect(IllegalArgumentException.class);
        myThrown.expectMessage("The radius can't be negative!");
        myCircle = new Circle(-100.0, CENTER, COLOR);
    }
    
    /**
     * Test method to check Illegal Argument Exception for a zero radius.
     */    
    @Test
    public void testOverloadedConstructorZeroRadius() { 
        myThrown.expect(IllegalArgumentException.class);
        myThrown.expectMessage("The radius can't be zero!");
        myCircle = new Circle(0, CENTER, COLOR);
    }
    
    /**
     * Test method to check Null Pointer Exception for null center value.
     */
    @Test
    public void testOverloadedConstructorNullException1() { 
        myThrown.expect(NullPointerException.class);
        myThrown.expectMessage("Center can't be null!");
        myCircle = new Circle(100.0, null, COLOR);
    }
    
    /**
     * Test method to check Null Pointer Exception for null color value.
     */
    @Test
    public void testOverloadedConstructorNullException2() { 
        myThrown.expect(NullPointerException.class);
        myThrown.expectMessage("Color can't be null!");
        myCircle = new Circle(100.0, CENTER, null);
    }  
    
    /**
     * Test method to check positive case for overloaded constructor.
     */
    @Test
    public void testOverloadedConstructorPositiveCase() { 
        assertEquals(1.0, myCircle.getRadius(), TOLERANCE);
        assertEquals(CENTER, myCircle.getCenter());
        assertEquals(COLOR, myCircle.getColor());
    }
    
    /**
     * Test method for setRadius.
     */  
    @Test
    public void testSetRadius() {
        myCircle.setRadius(5.0);
        assertEquals(5.0, myCircle.getRadius(), TOLERANCE);
    }
    
    /**
     * Test method for setRadius's Illegal Argument exception.
     */  
    @Test
    public void testSetRadiusException() {     
        myThrown.expect(IllegalArgumentException.class);
        myThrown.expectMessage("The radius can't be negative!");
        myCircle.setRadius(-100.0);
    }

    /**
     * Test method for setCenter.
     */
    @Test
    public void testSetCenter() {
        final Point randomPoint = new Point(10, 10);
        myCircle.setCenter(randomPoint);
        assertEquals(randomPoint, myCircle.getCenter());      
    }
    
    /**
     * Test method for setCenter's Null Pointer Exception.
     */  
    @Test
    public void testSetCenterException() {
        myThrown.expect(NullPointerException.class);
        myThrown.expectMessage("The center can't be null!");
        myCircle.setCenter(null);
    }
    
    /**
     * Test method for setColor.
     */
    @Test
    public void testSetColor() {
        myCircle.setColor(Color.BLUE);
        assertEquals(Color.BLUE, myCircle.getColor());  
    }
    
    /**
     * Test method for setColor's Null Pointer Exception.
     */  
    @Test
    public void testSetColorException() {       
        myThrown.expect(NullPointerException.class);
        myThrown.expectMessage("The color can't be null!");
        myCircle.setColor(null);     
    }

    /**
     * Test method for getRadius.
     */
    @Test
    public void testGetRadius() {
        assertEquals(RADIUS, myCircle.getRadius(), TOLERANCE);
        assertNotEquals(30, myLargeCircle.getRadius(), TOLERANCE);
        assertNotSame(myCircle.getRadius(), myLargeCircle.getRadius());
    }

    /**
     * Test method for getCenter.
     */
    @Test
    public void testGetCenter() {
        final Point offCenter = new Point(100, 15);
        assertEquals(CENTER, myCircle.getCenter());
        assertNotEquals(offCenter, myLargeCircle.getCenter());
        assertNotSame(myCircle.getCenter(), myLargeCircle.getCenter());
    }

    /**
     * Test method for getColor.
     */
    @Test
    public void testGetColor() {
        assertEquals(COLOR, myCircle.getColor());
    }

    /**
     * Test method for calculateDiameter.
     */
    @Test
    public void testCalculateDiameter() {
        assertEquals(2.0, myCircle.calculateDiameter(), TOLERANCE);
        assertNotEquals(600.0000000004, myLargeCircle.calculateDiameter(), TOLERANCE);
        assertEquals(601, myLargeCircle.calculateDiameter(), TOLERANCE);
    }

    /**
     * Test method for calculateCircumference.
     */
    @Test
    public void testCalculateCircumference() {
        assertEquals(2 * Math.PI, myCircle.calculateCircumference(), TOLERANCE);
        assertEquals(1888.097185, myLargeCircle.calculateCircumference(), TOLERANCE);
        assertNotEquals(1888.097186, myLargeCircle.calculateCircumference(), TOLERANCE);
    }

    /**
     * Test method for calculateArea.
     */
    @Test
    public void testCalculateArea() {
        assertEquals(Math.PI, myCircle.calculateArea(), TOLERANCE);
        assertEquals(90300.25 * Math.PI, myLargeCircle.calculateArea(), TOLERANCE);
        assertNotEquals(283686.602, myLargeCircle.calculateArea(), TOLERANCE);
    }

    /**
     * Test method for toString.
     */
    @Test
    public void testToString() {
        final String testString = "Circle [radius=1.00, center=java.awt.Point[x=0,y=0], "
                        + "color=java.awt.Color[r=0,g=0,b=0]]";
        assertEquals(testString, myCircle.toString());
    }

}
