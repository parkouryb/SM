package Project.Object;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.id.enhanced.SequenceStyleGenerator;

import javax.persistence.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Student")
public class Student {
    @Id
    @GenericGenerator(name = "INST_PK_SEQ_STUDENT",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(
                            name = "optimizer", value = "pooled-lo"),
                    @org.hibernate.annotations.Parameter(
                            name = "initial_value", value = "10000"),
                    @org.hibernate.annotations.Parameter(
                            name = "increment_size", value = "1"),
                    @org.hibernate.annotations.Parameter(
                            name = SequenceStyleGenerator.SEQUENCE_PARAM, value = "INST_PK_SEQ_STUDENT"),
            }
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "INST_PK_SEQ_STUDENT")
    @Column(name = "student_ID")
    private long student_ID = 1000;

    @Column(name="name")
    private String student_name;
    @Column(name="gender")
    private String gender;
    @Column(name="birthday")
    private String birthday;
    @Column(name="andress")
    private String andress;
    @Column(name="email")
    private String email;
    @Column(name="score_I")
    private Integer score_I;
    @Column(name="score_II")
    private Integer score_II;

    @ManyToOne
    @JoinColumn(name = "classroom_ID")
    private Classroom classroom;

    @Override
    public String toString() {
        return "Student{" +
                "student_ID=" + student_ID +
                ", student_name='" + student_name + '\'' +
                ", birthday='" + birthday + '\'' +
                ", classroom=" + classroom +
                '}';
    }
}