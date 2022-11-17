package ir.proprog.enrollassist.domain.studyRecord;
import ir.proprog.enrollassist.Exception.ExceptionList;
import ir.proprog.enrollassist.domain.GraduateLevel;
import ir.proprog.enrollassist.domain.course.Course;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.*;
/*
 JUnit runs a parameterized test with a special runner, Parameterized and we need to declare it with the @RuntWith annotation.
 In a parameterized test class, we declare instance variables corresponding to the number of inputs to the test and the output.
 */
@RunWith(Parameterized.class)
public class StudyRecordTest {
    public int grade;
    public boolean expectedIsPassed;
    public String courseGraduateLevel;
    public GraduateLevel graduateLevel;
    /*
     For a parameterized test, we need to provide a constructor, which will initialize the variables.
     */
    public StudyRecordTest(int grade, GraduateLevel graduateLevel, String courseGraduateLevel, boolean expectedIsPassed){
        this.grade = grade;
        this.expectedIsPassed = expectedIsPassed;
        this.courseGraduateLevel = courseGraduateLevel;
        this.graduateLevel = graduateLevel;
    }
    /*
    We also need to provide a public static method annotated with @Parameters annotation.
     This method will be used by the test runner to feed data into our tests.
     */
    @Parameterized.Parameters
    public static Collection<Object[]> parameters(){
        return Arrays.asList(new Object[][] {
                {9, GraduateLevel.Undergraduate, "Undergraduate", false},
                {11, GraduateLevel.Undergraduate, "Undergraduate", true},
                {13, GraduateLevel.Undergraduate, "Undergraduate", true},
                {15, GraduateLevel.Undergraduate, "Undergraduate", true},
                {9, GraduateLevel.Undergraduate, "PHD", false},
                {11, GraduateLevel.Undergraduate, "PHD", true},
                {13, GraduateLevel.Undergraduate, "PHD", true},
                {15, GraduateLevel.Undergraduate, "PHD", true},
                {9, GraduateLevel.Undergraduate, "Masters", false},
                {11, GraduateLevel.Undergraduate, "Masters", true},
                {13, GraduateLevel.Undergraduate, "Masters", true},
                {15, GraduateLevel.Undergraduate, "Masters", true},
                {9, GraduateLevel.Masters, "PHD", false},
                {11, GraduateLevel.Masters, "PHD", false},
                {13, GraduateLevel.Masters, "PHD", true},
                {15, GraduateLevel.Masters, "PHD", true},
                {9, GraduateLevel.Masters, "Undergraduate", false},
                {11, GraduateLevel.Masters, "Undergraduate", true},
                {13, GraduateLevel.Masters, "Undergraduate", true},
                {15, GraduateLevel.Masters, "Undergraduate", true},
                {9, GraduateLevel.Masters, "Masters", false},
                {11, GraduateLevel.Masters, "Masters", false},
                {13, GraduateLevel.Masters, "Masters", true},
                {15, GraduateLevel.Masters, "Masters", true},
                {9, GraduateLevel.PHD, "Undergraduate", false},
                {11, GraduateLevel.PHD, "Undergraduate", true},
                {13, GraduateLevel.PHD, "Undergraduate", true},
                {15, GraduateLevel.PHD, "Undergraduate", true},
                {9, GraduateLevel.PHD, "Masters", false},
                {11, GraduateLevel.PHD, "Masters", false},
                {13, GraduateLevel.PHD, "Masters", true},
                {15, GraduateLevel.PHD, "Masters", true},
                {13, GraduateLevel.PHD, "PHD", false},
                {15, GraduateLevel.PHD, "PHD", true}
                {9, GraduateLevel.PHD, "PHD", false},
                {11, GraduateLevel.PHD, "PHD", false},
        });
        /*
        The @Parametersannotated method above returns a collection of test data elements (which in turn are stored in an array).
         Test data elements are the different variations of the data, including the input as well as expected output needed by the test.
         The number of test data elements in each array must be the same with the number of parameters we declared in the constructor.
         */
    }
    /*
    Finally, we write the test method annotated with @Test. The complete code of the parameterized test is this.
     */
    @Test
    public void testOfStudyRecord() throws ExceptionList {
        StudyRecord studyRecord = new StudyRecord("1656", new Course("2", "Internet Engineering", 3, courseGraduateLevel), grade);
        assertEquals(expectedIsPassed, studyRecord.isPassed(graduateLevel));
    }
}