package Project.Object;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Bieumau5 {
    private String classroom_name;
    private int number;
    private int pass;

    @Override
    public String toString() {
        return "Bieumau5{" +
                "classroom_name='" + classroom_name + '\'' +
                ", number=" + number +
                ", pass=" + pass +
                '}';
    }
}
