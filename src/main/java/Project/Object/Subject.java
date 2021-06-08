package Project.Object;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.id.enhanced.SequenceStyleGenerator;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Transactional
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Subject")
public class Subject {
    @Id
    @GenericGenerator(name = "INST_PK_SEQ_SUBJECT",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(
                            name = "optimizer", value = "pooled-lo"),
                    @org.hibernate.annotations.Parameter(
                            name = "initial_value", value = "90000"),
                    @org.hibernate.annotations.Parameter(
                            name = "increment_size", value = "1"),
                    @org.hibernate.annotations.Parameter(
                            name = SequenceStyleGenerator.SEQUENCE_PARAM, value = "INST_PK_SEQ_SUBJECT"),
            }
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "INST_PK_SEQ_SUBJECT")
    @Column(name="subject_ID")
    private long subject_ID;

    @Column(name="subject_name")
    private String subject_name;

    @Column(name="semester")
    private Integer semester;

    @OneToMany(mappedBy = "studyPK.subject", fetch = FetchType.EAGER, cascade = CascadeType.MERGE, orphanRemoval = true)
    private Set<Study> studies = new HashSet<>();

    @Override
    public String toString() {
        return "Subject{" +
                "subject_ID='" + subject_ID + '\'' +
                ", subject_name='" + subject_name + '\'' +
                '}';
    }
}
