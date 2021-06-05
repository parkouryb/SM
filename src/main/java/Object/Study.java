package Object;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Study")
public class Study {
    @Id
    @Column(name="study_ID")
    private String study_ID;
//    private String student_ID;
//    private String subject_ID;
//    private Double score_15;
//    private Double score_45;
//    private Double score_Mean;
}
