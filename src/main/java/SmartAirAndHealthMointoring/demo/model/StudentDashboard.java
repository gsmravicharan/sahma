package SmartAirAndHealthMointoring.demo.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDashboard {
    private String email;
    @Id
    private String redgno;
    private String department;
    private String age;
    private String section;
    private String mobileno;
    private String alternativemobilenumber;
}
