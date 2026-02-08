package joufuniversitygym;

public class Student {
    private String universityId;
    private String name;
    private String academicYear;
    private String major;
    private String club;

    public Student() {
    }

    public Student(String universityId, String name, String academicYear, String major, String club) {
        this.universityId = universityId;
        this.name = name;
        this.academicYear = academicYear;
        this.major = major;
        this.club = club;
    }

    public String getUniversityId() {
        return universityId;
    }

    public void setUniversityId(String universityId) {
        this.universityId = universityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = club;
    }

    @Override
    public String toString() {
        return "Student{" +
                "universityId='" + universityId + '\'' +
                ", name='" + name + '\'' +
                ", academicYear='" + academicYear + '\'' +
                ", major='" + major + '\'' +
                ", club='" + club + '\'' +
                '}';
    }
}
