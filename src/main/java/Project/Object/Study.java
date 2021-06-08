package Project.Object;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.id.enhanced.SequenceStyleGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Getter @Setter
@AllArgsConstructor
@Entity
@Table(name="Study")
public class Study {
    @EmbeddedId
    private StudyPK studyPK;
    @Column(name = "score_15")
    private Double score_15;
    @Column(name = "score_45")
    private Double score_45;
    @Column(name = "score_mean")
    private Double score_mean;

    public Study() {
        studyPK = new StudyPK();
    }
}

