package ir.proprog.enrollassist.domain.course;

import ir.proprog.enrollassist.Exception.ExceptionList;
import ir.proprog.enrollassist.controller.course.CourseMajorView;
import ir.proprog.enrollassist.domain.GraduateLevel;
import ir.proprog.enrollassist.repository.CourseRepository;
import ir.proprog.enrollassist.repository.ProgramRepository;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.*;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class AddCourseServiceTest {
    private Course math1Course;
    private Course math2Course;
    private Course differentialEquationsCourse;
    private Set<Long> prerequisite;
    private Set<Course> prerequisite_math1;
    private Set<Course> prerequisite_math2;
    private AddCourseService addCourseService;
    private CourseMajorView courseMajorView;
    private CourseRepository courseRepository;
    private ProgramRepository programRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        math1Course = Mockito.mock(Course.class);
        math2Course = Mockito.mock(Course.class);
        differentialEquationsCourse = Mockito.mock(Course.class);
        courseRepository = Mockito.mock(CourseRepository.class);
        when(courseRepository.findById(0L)).thenReturn(Optional.ofNullable(math1Course));
        when(courseRepository.findById(1L)).thenReturn(Optional.ofNullable(math2Course));
        when(courseRepository.findById(2L)).thenReturn(Optional.ofNullable(differentialEquationsCourse));
        programRepository = Mockito.mock(ProgramRepository.class);
        Set<Course> pre_c = new HashSet<>() {{add(math2Course);}{add(differentialEquationsCourse);}};
        when(math1Course.getPrerequisites()).thenReturn(pre_c);
        when(math1Course.getCourseNumber()).thenReturn(new CourseNumber());
        when(math1Course.getTitle()).thenReturn("Mathematica 1");
        when(math1Course.getId()).thenReturn(0L);
        when(math2Course.getTitle()).thenReturn("Mathematica 2");
        when(math2Course.getId()).thenReturn(1L);
        when(differentialEquationsCourse.getTitle()).thenReturn("Differential Equations");
        when(differentialEquationsCourse.getId()).thenReturn(2L);
        when(math1Course.getGraduateLevel()).thenReturn(GraduateLevel.Masters);
    }

    @AfterEach
    void tearDown() {
        math1Course = null;
        math2Course = null;
        differentialEquationsCourse = null;
    }

    @Test
    public void check_with_no_loop() throws ExceptionList {
        Set<Long> dummy = new HashSet<>();
        prerequisite = new HashSet<>();
        courseMajorView = new CourseMajorView(math1Course, prerequisite, dummy);
        addCourseService = new AddCourseService(courseRepository, programRepository);
        addCourseService.addCourse(courseMajorView);
    }
    @Test
    public void check_with_loop() {
        prerequisite_math1 = new HashSet<>(){{add(math1Course);}};
        when(math1Course.getPrerequisites()).thenReturn(prerequisite_math1);
        prerequisite_math2 = new  HashSet<>(){};
        when(math2Course.getPrerequisites()).thenReturn(prerequisite_math2);

        Set<Long> dummy = new HashSet<>();
        prerequisite = new HashSet<>();
        courseMajorView = new CourseMajorView(math1Course, prerequisite, dummy);
        addCourseService = new AddCourseService(courseRepository, programRepository);
        ExceptionList e_expected = new ExceptionList();
        List<Exception> es = new ArrayList<>(){{
            add(new Exception(String.format("%s has made a loop in prerequisites.", "Mathematica 1")));
        }};
        e_expected.addExceptions(es);

        ExceptionList exception = assertThrows(ExceptionList.class, () -> addCourseService.addCourse(courseMajorView));
        Assertions.assertEquals(e_expected.getMessage(), exception.getMessage());
    }
}