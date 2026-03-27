package SmartAirAndHealthMointoring.demo.Repository;

import SmartAirAndHealthMointoring.demo.constants.Roles;
import SmartAirAndHealthMointoring.demo.model.HodDashboard;
import SmartAirAndHealthMointoring.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HodDashboardRepo extends JpaRepository<HodDashboard,String> {

}
