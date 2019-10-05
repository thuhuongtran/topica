package topica.itlab.java8.entity;

import java.util.Arrays;
import java.util.List;

public enum SubjectDomain {
    MATHEMATICS, LITERATURE, PHYSICS, CHEMISTRY, BIOLOGY, HISTORY, GEOGRAPHY;

    public static SubjectDomain getDomain(String domain) {
        if (domain.equals(MATHEMATICS.name())) return MATHEMATICS;
        else if (domain.equals(LITERATURE.name())) return LITERATURE;
        else if (domain.equals(PHYSICS.name())) return PHYSICS;
        else if (domain.equals(CHEMISTRY.name())) return CHEMISTRY;
        else if (domain.equals(BIOLOGY.name())) return BIOLOGY;
        else if (domain.equals(HISTORY.name())) return HISTORY;
        else if (domain.equals(GEOGRAPHY.name())) return GEOGRAPHY;
        else return null;
    }

    public static List<SubjectDomain> getListDomain() {
        return Arrays.asList(
                MATHEMATICS,
                LITERATURE,
                PHYSICS,
                CHEMISTRY,
                BIOLOGY,
                HISTORY,
                GEOGRAPHY);
    }
}