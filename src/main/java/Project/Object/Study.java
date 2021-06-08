package Project.Object;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.id.enhanced.SequenceStyleGenerator;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Objects;

@Transactional
@Getter @Setter
@Entity
@Table(name="Study")
public class Study implements Serializable{
    @Getter
    @Setter
    @Embeddable
    public static class StudyPK implements Serializable {
        @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.MERGE)
        @JoinColumn(name = "student_ID", insertable = false, updatable = false)
        protected Student student;

        @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.MERGE)
        @JoinColumn(name = "subject_ID", insertable = false, updatable = false)
        protected Subject subject;

        public StudyPK() {
        }

        public StudyPK(Student student, Subject subject) {
            this.student = student;
            this.subject = subject;
        }

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

        @Override
        public String toString() {
            return "StudyPK{" +
                    "student=" + student +
                    ", subject=" + subject +
                    '}';
        }
    }

    @EmbeddedId
    private StudyPK studyPK;
    @Column(name = "score_15")
    private Double score_15;
    @Column(name = "score_45")
    private Double score_45;
    @Column(name = "score_mean")
    private Double score_mean;

    public Study() {
    }

    public Study(StudyPK studyPK) {
        this.studyPK = studyPK;
    }

    public Study(StudyPK studyPK, Double score_15, Double score_45, Double score_mean) {
        this.studyPK = studyPK;
        this.score_15 = score_15;
        this.score_45 = score_45;
        this.score_mean = score_mean;
    }

    @Override
    public String toString() {
        return "Study{" +
                ", score_15=" + score_15 +
                ", score_45=" + score_45 +
                ", score_mean=" + score_mean +
                '}';
    }
}

