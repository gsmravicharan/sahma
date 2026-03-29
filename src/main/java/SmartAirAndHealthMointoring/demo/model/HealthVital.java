package SmartAirAndHealthMointoring.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "reports") // Matches your database table name
@Data
public class HealthVital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer air;
    private String status;
    private Integer bpm;
    private Float spo2;
    private Float temp;

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    private String email;

    @Column(name = "registerno")
    private String registerno;
}