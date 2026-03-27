package SmartAirAndHealthMointoring.demo.service;

import SmartAirAndHealthMointoring.demo.configuration.ResponseDto;
import SmartAirAndHealthMointoring.demo.model.StudentDashboard;
import org.springframework.http.ResponseEntity;

public interface StudentDashBoardService {

    ResponseEntity<ResponseDto<?>> dashBoardCreation(StudentDashboard details);

    ResponseEntity<ResponseDto<?>> dashBoardGet(String redgno);
}
