package Object;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Classroom")
public class Classroom {
    @Id
    @Column(name="classroom_ID")
    private String classroom_ID;
//    private Integer number;
//    private String class_name;
//
//    private List<Student> students;
}
