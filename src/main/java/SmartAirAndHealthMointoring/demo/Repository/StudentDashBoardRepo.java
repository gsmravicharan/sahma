package SmartAirAndHealthMointoring.demo.Repository;

import SmartAirAndHealthMointoring.demo.model.StudentDashboard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentDashBoardRepo extends JpaRepository<StudentDashboard,String> {
}
