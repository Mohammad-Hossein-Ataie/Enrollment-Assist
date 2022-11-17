package ir.proprog.enrollassist.domain.section;

import ir.proprog.enrollassist.Exception.ExceptionList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.*;

public class PresentationScheduleTest {

    public PresentationSchedule presentationSchedule;

    @Before
    public void setUp() throws ExceptionList{
        System.out.println("Setting it up!");
        presentationSchedule = new PresentationSchedule("Friday", "10:00", "11:30");
    }
    @Test
    public void checkifStartAndEndOfPresentationIsBetweenStartAndEndOfOtherShouldHasConflict() throws ExceptionList{
        PresentationSchedule otherPresentationSchedule = new PresentationSchedule("Friday", "9:30", "12:00");
        presentationSchedule.hasConflict(otherPresentationSchedule);
        Assert.assertTrue(presentationSchedule.hasConflict(otherPresentationSchedule));
    }
    @Test
    public void checkifStartOfOtherIsAfterEndOfPresentationShouldNotHaveConflict() throws ExceptionList{
        PresentationSchedule otherPresentationSchedule = new PresentationSchedule("Friday", "12:00", "13:30");
        presentationSchedule.hasConflict(otherPresentationSchedule);
        Assert.assertFalse(presentationSchedule.hasConflict(otherPresentationSchedule));
    }

    @Test
    public void checkifEndOfOtherIsAfterStartBeforeEndOfPresentationShouldHaveConflict() throws ExceptionList{
        PresentationSchedule otherPresentationSchedule = new PresentationSchedule("Friday", "09:30", "11:00");
        presentationSchedule.hasConflict(otherPresentationSchedule);
        Assert.assertTrue(presentationSchedule.hasConflict(otherPresentationSchedule));
    }

    @Test
    public void checkifEndOfOtherIsEqualToStartOfOfPresentationShouldNotHasConflict() throws ExceptionList{
        PresentationSchedule otherPresentationSchedule = new PresentationSchedule("Friday", "09:00", "10:00");
        presentationSchedule.hasConflict(otherPresentationSchedule);
        Assert.assertFalse(presentationSchedule.hasConflict(otherPresentationSchedule));
    }
    @Test
    public void checkifStartAndEndOfOtherIsBetweenStartAndEndOfPresentationShouldHasConflict() throws ExceptionList{
        PresentationSchedule otherPresentationSchedule = new PresentationSchedule("Friday", "10:30", "11:00");
        presentationSchedule.hasConflict(otherPresentationSchedule);
        Assert.assertTrue(presentationSchedule.hasConflict(otherPresentationSchedule));
    }

    @Test
    public void checkifEndOfOtherAndEndOfPresentationIsEqualButStartsNotEqualShouldHasConflict() throws ExceptionList{
        PresentationSchedule otherPresentationSchedule = new PresentationSchedule("Friday", "10:30", "11:30");
        presentationSchedule.hasConflict(otherPresentationSchedule);
        Assert.assertTrue(presentationSchedule.hasConflict(otherPresentationSchedule));
    }
    @Test
    public void checkifEndOfOtherIsBeforeStartOfPresentationShouldNotHaveConflict() throws ExceptionList{
        PresentationSchedule otherPresentationSchedule = new PresentationSchedule("Friday", "08:00", "9:30");
        presentationSchedule.hasConflict(otherPresentationSchedule);
        Assert.assertFalse(presentationSchedule.hasConflict(otherPresentationSchedule));
    }

    @Test
    public void checkifStartOfOtherAndStartOfPresentationIsEqualButEndsNotEqualShouldHasConflict() throws ExceptionList{
        PresentationSchedule otherPresentationSchedule = new PresentationSchedule("Friday", "10:00", "11:00");
        presentationSchedule.hasConflict(otherPresentationSchedule);
        Assert.assertTrue(presentationSchedule.hasConflict(otherPresentationSchedule));
    }

    @Test
    public void checkcheckifStartOfOtherIsBeforeEndAndAfterStartOfPresentationShouldHaveConflict() throws ExceptionList{
        PresentationSchedule otherPresentationSchedule = new PresentationSchedule("Friday", "10:30", "12:00");
        presentationSchedule.hasConflict(otherPresentationSchedule);
        Assert.assertTrue(presentationSchedule.hasConflict(otherPresentationSchedule));
    }

    @Test
    public void checkifStartOfOtherIsEqualToEndOfOfPresentationShouldNotHasConflict() throws ExceptionList{
        PresentationSchedule otherPresentationSchedule = new PresentationSchedule("Friday", "11:30", "13:00");
        presentationSchedule.hasConflict(otherPresentationSchedule);
        Assert.assertFalse(presentationSchedule.hasConflict(otherPresentationSchedule));
    }

    @Test
    public void checkIfTwoEqualPresentationsShouldHaveConflict() throws ExceptionList{
        PresentationSchedule otherPresentationSchedule = new PresentationSchedule("Friday", "10:00", "11:30");
        presentationSchedule.hasConflict(otherPresentationSchedule);
        Assert.assertTrue(presentationSchedule.hasConflict(otherPresentationSchedule));
    }
    @Test
    public void checkIfTwoPresentationWithDifferentDayOfWeakShouldNotHaveConflict() throws ExceptionList{
        PresentationSchedule otherPresentationSchedule = new PresentationSchedule("Monday", "10:00", "11:30");
        presentationSchedule.hasConflict(otherPresentationSchedule);
        Assert.assertFalse(presentationSchedule.hasConflict(otherPresentationSchedule));
    }
    /*
    I also wanted to test argument of PresentationSchedule but refer to below link, it is not a appropriate test!"
    https://stackoverflow.com/questions/34719142/junit-test-case-for-checking-arguments
     */
    @After
    public void after() throws Exception {
        System.out.println("Running: tearDown");
        presentationSchedule = null;
        assertNull(presentationSchedule);
    }
}