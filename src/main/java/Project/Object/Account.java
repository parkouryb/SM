package Project.Object;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.id.enhanced.SequenceStyleGenerator;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="account")
public class Account {
    @Id
    @GenericGenerator(name = "INST_PK_SEQ_ACCOUNT",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(
                            name = "optimizer", value = "pooled-lo"),
                    @org.hibernate.annotations.Parameter(
                            name = "initial_value", value = "100000"),
                    @org.hibernate.annotations.Parameter(
                            name = "increment_size", value = "1"),
                    @org.hibernate.annotations.Parameter(
                            name = SequenceStyleGenerator.SEQUENCE_PARAM, value = "INST_PK_SEQ_ACCOUNT"),
            }
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "INST_PK_SEQ_ACCOUNT")
    private long id;
    @Column(name="username")
    private String username;
    @Column(name="password")
    private String password;
    @Column(name="role")
    private Role role;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Student student;
}
