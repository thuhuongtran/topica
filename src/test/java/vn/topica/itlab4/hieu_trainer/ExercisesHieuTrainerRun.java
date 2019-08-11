package vn.topica.itlab4.hieu_trainer;

import org.junit.Test;
import vn.topica.itlab4.exercises.hieu_trainer.Exercises1_17;
import vn.topica.itlab4.exercises.hieu_trainer.exercises18_36.ExerciseRun18_36;

public class ExercisesHieuTrainerRun {
    private Exercises1_17 run1_17 = Exercises1_17.getInstance();
    private ExerciseRun18_36 run18_36 = ExerciseRun18_36.getInstance();
    /**
     * Run exercise 1
     */
    @Test
    public void testEx1() {
        run1_17.arverageNumber();
    }

    /**
     * Run exercise 2
     */
    @Test
    public void testEx2() {
        run1_17.oddEven();
    }

    /**
     * Run exercise 3
     */
    @Test
    public void testEx3() {
        run1_17.prime();
    }

    /**
     * Run exercise 4
     */
    @Test
    public void testEx4() {
        run1_17.checkPositiveNegative();
    }

    /**
     * Run exercise 5
     */
    @Test
    public void testEx5() {
        run1_17.reverseString();
    }

    /**
     * Run exercise 6
     */
    @Test
    public void testEx6() {
        run1_17.reverseNumber();
    }

    /**
     * Run exercise 1
     */
    @Test
    public void testEx7() {
        run1_17.checkLeapYear();
    }

    /**
     * Run exercise 8
     */
    @Test
    public void testEx8() {
        run1_17.toHexaNumber();
    }

    /**
     * Run exercise 9
     */
    @Test
    public void testEx9() {
        run1_17.toBinaryNumber();
    }

    /**
     * Run exercise 10
     */
    @Test
    public void testEx10() {
        run1_17.getIP();
    }

    /**
     * Run exercise 11
     */
    @Test
    public void testEx11() {
        run1_17.getSameCharacter();
    }

    /**
     * Run exercise 12
     */
    @Test
    public void testEx12() {
        run1_17.createNumber();
    }

    /**
     * Run exercise 13
     */
    @Test
    public void testEx13() {
        run1_17.checkSymmetry();
    }

    /**
     * Run exercise 14
     */
    @Test
    public void testEx14() {
        run1_17.bubbleSortFor();
    }

    /**
     * Run exercise 15
     */
    @Test
    public void testEx15() {
        run1_17.bubbleSortWhile();
    }

    /**
     * Run exercise 16
     */
    @Test
    public void testEx16() {
        run1_17.convertNumber();
    }

    /**
     * Run exercise 17
     */
    @Test
    public void testEx17() {
        run1_17.arverageNumber();
    }

    /**
     * Run exercise 25
     */
    @Test
    public void testEx25() {
        run18_36.writeStudentFile();
        run18_36.writeTeacherFile();
        run18_36.writeSubjectFile();
    }

    /**
     * Run exercise 27
     */
    @Test
    public void testEx27() {
        run18_36.sortPeopleByFirstName();
    }

    /**
     * Run exercise 28
     */
    @Test
    public void testEx28() {
        run18_36.sortByFirstName();
    }

    /**
     * Run exercise 29
     */
    @Test
    public void testEx29() {
        run18_36.sortStudentByName();
    }

    /**
     * Run exercise 32
     */
    @Test
    public void testEx32() {
        run18_36.getAllDBdata();
    }

    /**
     * Run exercise 33
     */
    @Test
    public void testEx33() {
        run18_36.addData();
    }

    /**
     * Run exercise 34
     */
    @Test
    public void testEx34() {
        run18_36.inputSQL();
    }

    /**
     * Run exercise 36
     */
    @Test
    public void testEx36() {
        run18_36.updateStudent();
    }
}

