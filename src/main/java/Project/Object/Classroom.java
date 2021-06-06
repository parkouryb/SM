package Project.Object;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.id.enhanced.SequenceStyleGenerator;

import javax.persistence.*;
import java.util.Collection;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Classroom")
public class Classroom {
    @Id
    @GenericGenerator(name = "INST_PK_SEQ_CLASSROOM",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(
                            name = "optimizer", value = "pooled-lo"),
                    @org.hibernate.annotations.Parameter(
                            name = "initial_value", value = "80000"),
                    @org.hibernate.annotations.Parameter(
                            name = "increment_size", value = "1"),
                    @org.hibernate.annotations.Parameter(
                            name = SequenceStyleGenerator.SEQUENCE_PARAM, value = "INST_PK_SEQ_CLASSROOM"),
            }
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "INST_PK_SEQ_CLASSROOM")
    @Column(name="classroom_ID")
    private long classroom_ID;

    @Column(name="number")
    private Integer number;
    @Column(name="classname")
    private String class_name;

    @OneToMany(mappedBy = "classroom", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Collection<Student> students;

    public Classroom(long classroom_ID) {
        this.classroom_ID = classroom_ID;
    }

    @Override
    public String toString() {
        return "Classroom{" +
                "classroom_ID=" + classroom_ID +
                ", number=" + number +
                ", class_name='" + class_name + '\'' +
                ", current='" + students.size() + '\'' +
                '}';
    }
}
