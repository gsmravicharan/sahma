package SmartAirAndHealthMointoring.demo.service;

import SmartAirAndHealthMointoring.demo.configuration.ResponseDto;
import SmartAirAndHealthMointoring.demo.model.TeacherDashBoard;
import org.springframework.http.ResponseEntity;

public interface TeacherDashBoardService {
    ResponseEntity<ResponseDto<?>> dashBoardcreation(TeacherDashBoard details);

    ResponseEntity<ResponseDto<?>> dashBoardGet(String redgno);
}
