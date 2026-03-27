package SmartAirAndHealthMointoring.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TeacherDashBoard {
    private String email;
    @Id
    private String redgno;
    private String department;
    private String age;
    private String mobileno;
    private String alternativemobilenumber;
}
