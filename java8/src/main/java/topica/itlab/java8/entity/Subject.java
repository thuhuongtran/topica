package topica.itlab.java8.entity;

public class Subject {
    private String subjectName;
    private Integer subjectId;
    private String subjectDesc;
    private SubjectDomain domain;

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectDesc() {
        return subjectDesc;
    }

    public void setSubjectDesc(String subjectDesc) {
        this.subjectDesc = subjectDesc;
    }

    public SubjectDomain getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = SubjectDomain.getDomain(domain);
    }
}
