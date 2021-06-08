package Project.Object;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Embeddable
public class StudyPK implements Serializable {
    @ManyToOne
    @JoinColumn(name = "student_ID")
    protected Student student;

    @ManyToOne
    @JoinColumn(name = "subject_ID")
    protected Subject subject;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudyPK studyPK = (StudyPK) o;
        return Objects.equals(student, studyPK.student) && Objects.equals(subject, studyPK.subject);
    }

    @Override
    public int hashCode() {
        return Objects.hash(student, subject);
    }
}