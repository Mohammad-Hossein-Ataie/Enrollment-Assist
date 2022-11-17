package ir.proprog.enrollassist.domain.enrollmentList;
import ir.proprog.enrollassist.domain.EnrollmentRules.EnrollmentRuleViolation;
import ir.proprog.enrollassist.domain.course.Course;
import ir.proprog.enrollassist.domain.major.Major;
import ir.proprog.enrollassist.domain.program.Program;
import ir.proprog.enrollassist.domain.section.ExamTime;
import ir.proprog.enrollassist.domain.section.PresentationSchedule;
import ir.proprog.enrollassist.domain.section.Section;
import ir.proprog.enrollassist.domain.student.Student;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import java.util.*;
import org.junit.*;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(Parameterized.class)
public class EnrollmentListTest {
    public EnrollmentList enrollmentList;
    public int expectedViolationsSize;
    public String expectedViolationMessage;
    public static Program program;

    public static Course DesignAlgorithmtaStructure;
    public static Course InternetEngineering;
    public static Course AdvancedProgramming;
    public static Course OperatingSystem;
    public static Course DesignAlgorithm;
    public static Course Parallel;
    
    public static Section DesignAlgorithmtaStructureSection;
    public static Section InternetEngineeringSection;
    public static Section AdvancedProgrammingSection;
    public static Section OperatingSystemSection;
    public static Section DesignAlgorithmSection;
    public static Section ParallelSection;

    public static Student student;

    public EnrollmentListTest(EnrollmentList enrollmentList, int expectedViolationsSize, String expectedViolationMessage) {
        this.enrollmentList = enrollmentList;
        this.expectedViolationsSize = expectedViolationsSize;
        this.expectedViolationMessage = expectedViolationMessage;
    }

    public static void setUp() throws Exception {
        program = new Program(new Major("1", "Parallel", "Software"), "Undergraduate", 1, 140, "Minor");
        DesignAlgorithmtaStructure = new Course("1", "DesignAlgorithmtaStructure", 3, "Undergraduate");
        program.addCourse(DesignAlgorithmtaStructure);
        InternetEngineering = new Course("2", "InternetEngineering", 3, "Undergraduate");
        program.addCourse(InternetEngineering);
        AdvancedProgramming = new Course("4", "AdvancedProgramming", 3, "Undergraduate");
        program.addCourse(AdvancedProgramming);
        OperatingSystem = new Course("5", "OperatingSystem", 3, "Undergraduate");
        program.addCourse(OperatingSystem);
        DesignAlgorithm = new Course("6", "DesignAlgorithm", 3, "Undergraduate");
        program.addCourse(DesignAlgorithm);
        CA = new Course("7", "CA", 3, "Undergraduate").withPre(InternetEngineering);
        program.addCourse(CA);
        CAD = new Course("8", "CAD", 3, "Undergraduate");
        program.addCourse(CAD);
        MATH = new Course("9", "MATH", 3, "Undergraduate");
        program.addCourse(MATH);
        FLAT = new Course("0000010", "FLAT", 3, "Undergraduate");
        program.addCourse(FLAT);
        student = new Student("000", "Undergraduate");
        student.addProgram(program);
        student.setGrade("00001", DesignAlgorithmtaStructure, 11);
        DesignAlgorithmtaStructureSection = new Section(DesignAlgorithmtaStructure, "0", new ExamTime("2022-11-10T09:00", "2022-11-10T11:00"),
                Collections.singleton(new PresentationSchedule("Saturday", "9:00", "11:00")));
        InternetEngineeringSection = new Section(InternetEngineering, "1", new ExamTime("2022-11-11T09:00", "2022-11-11T11:00"),
                Collections.singleton(new PresentationSchedule("Sunday", "09:00", "11:00")));
        AdvancedProgrammingSection = new Section(AdvancedProgramming, "3", new ExamTime("2022-11-13T09:00", "2022-11-13T11:00"),
                Collections.singleton(new PresentationSchedule("Tuesday", "09:00", "11:00")));
        OperatingSystemSection = new Section(OperatingSystem, "4", new ExamTime("2022-11-12T09:00", "2022-11-12T11:00"),
                Collections.singleton(new PresentationSchedule("Wednesday", "09:00", "11:00")));
        DesignAlgorithmSection = new Section(DesignAlgorithm, "5", new ExamTime("2022-11-30T09:00", "2022-11-30T11:00"),
                Collections.singleton(new PresentationSchedule("Sunday", "09:00", "11:00")));
        CaSection = new Section(CA, "6", new ExamTime("2022-11-20T09:00", "2022-11-20T11:00"),
                Collections.singleton(new PresentationSchedule("Thursday", "09:00", "11:00")));
        CadSection = new Section(CAD, "7", new ExamTime("2022-11-01T09:00", "2022-11-01T11:00"),
                Collections.singleton(new PresentationSchedule("Saturday", "014:00", "15:00")));
        MathSection = new Section(MATH, "8", new ExamTime("2022-11-01T01:00", "2022-11-01T02:00"),
                Collections.singleton(new PresentationSchedule("Saturday", "02:00", "3:00")));
        FlatSection = new Section(FLAT, "9", new ExamTime("2022-11-11T09:00", "2022-11-11T11:00"),
                Collections.singleton(new PresentationSchedule("Saturday", "02:00", "3:00")));
    }

    static {
        try{
            setUp();
        }catch (Exception e){}
    }

    @Parameters
    public static Collection<Object[]> parameters() throws Exception {
        EnrollmentList enrollmentListTrue = new EnrollmentList("EnrollmentListTest", student);
        enrollmentListTrue.addSections(CadSection);
        enrollmentListTrue.addSections(InternetEngineeringSection);
        enrollmentListTrue.addSections(AdvancedProgrammingSection);
        enrollmentListTrue.addSections(OperatingSystemSection);

        EnrollmentList PrerequisiteNotTaken = new EnrollmentList("EnrollmentListTest", student);
        PrerequisiteNotTaken.addSections(CadSection);
        PrerequisiteNotTaken.addSections(InternetEngineeringSection);
        PrerequisiteNotTaken.addSections(CaSection);
        PrerequisiteNotTaken.addSections(OperatingSystemSection);

        EnrollmentList RequestedCourseAlreadyPassed = new EnrollmentList("EnrollmentListTest", student);
        RequestedCourseAlreadyPassed.addSections(DesignAlgorithmtaStructureSection);
        RequestedCourseAlreadyPassed.addSections(CadSection);
        RequestedCourseAlreadyPassed.addSections(InternetEngineeringSection);
        RequestedCourseAlreadyPassed.addSections(AdvancedProgrammingSection);

        EnrollmentList CourseRequestedTwice = new EnrollmentList("EnrollmentListTest", student);
        CourseRequestedTwice.addSections(CadSection);
        CourseRequestedTwice.addSections(InternetEngineeringSection);
        CourseRequestedTwice.addSections(OperatingSystemSection);
        CourseRequestedTwice.addSections(OperatingSystemSection);

        EnrollmentList MaxCreditsLimitExceeded = new EnrollmentList("EnrollmentListTest", student);
        MaxCreditsLimitExceeded.addSections(CadSection);
        MaxCreditsLimitExceeded.addSections(InternetEngineeringSection);
        MaxCreditsLimitExceeded.addSections(AdvancedProgrammingSection);
        MaxCreditsLimitExceeded.addSections(OperatingSystemSection);
        MaxCreditsLimitExceeded.addSections(MathSection);

        EnrollmentList ConflictOfClassSchedule = new EnrollmentList("EnrollmentListTest", student);
        ConflictOfClassSchedule.addSections(CadSection);
        ConflictOfClassSchedule.addSections(InternetEngineeringSection);
        ConflictOfClassSchedule.addSections(AdvancedProgrammingSection);
        ConflictOfClassSchedule.addSections(DesignAlgorithmSection);

        EnrollmentList ExamTimeCollision = new EnrollmentList("EnrollmentListTest", student);
        ExamTimeCollision.addSections(CadSection);
        ExamTimeCollision.addSections(InternetEngineeringSection);
        ExamTimeCollision.addSections(AdvancedProgrammingSection);
        ExamTimeCollision.addSections(FlatSection);

        EnrollmentList MinCreditsRequiredNotMet = new EnrollmentList("EnrollmentListTest", student);

        return Arrays.asList(new Object[][]{
                {enrollmentListTrue, 0, ""},
                {PrerequisiteNotTaken, 1, "[2] InternetEngineering is not passed as a prerequisite of [7] CA"},
                {RequestedCourseAlreadyPassed, 1, "[1] DesignAlgorithmtaStructure has been already passed"},
                {CourseRequestedTwice, 1, "[5] OperatingSystem is requested to be taken twice"},
                {MaxCreditsLimitExceeded, 1, "Maximum number of credits(14) exceeded."},
                {MinCreditsRequiredNotMet, 1, "Minimum number of credits(12) is not met."},
                {ConflictOfClassSchedule, 1,
                        "ir.proprog.enrollassist.domain.section.Section@ba071fc1 course and ir.proprog.enrollassist.domain.section.Section@ba072041 course have conflict in schedule."},
                {ExamTimeCollision, 1,
                        "ir.proprog.enrollassist.domain.section.Section@ba071fc1 is not passed as a prerequisite of ir.proprog.enrollassist.domain.section.Section@ba07234c"},
        });
    }

    @Test
    public void testOfEnrollmentRules() {
        List<EnrollmentRuleViolation> ruleViolations = enrollmentList.checkEnrollmentRules();
        if (ruleViolations.size() != 0)
            assertEquals(this.expectedViolationMessage, ruleViolations.get(0).toString());
        assertEquals(this.expectedViolationsSize, ruleViolations.size());
    }
}