package Object;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Subject")
public class Subject {
    @Id
    @Column(name="subject_ID")
    private String subject_ID;
//    private String subject_name;

//    private List<Study> studies;
}
