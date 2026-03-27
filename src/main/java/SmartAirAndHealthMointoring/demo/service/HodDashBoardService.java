package SmartAirAndHealthMointoring.demo.service;

import SmartAirAndHealthMointoring.demo.configuration.ResponseDto;
import SmartAirAndHealthMointoring.demo.model.HodDashboard;
import org.springframework.http.ResponseEntity;

public interface HodDashBoardService {
    ResponseEntity<ResponseDto<?>> dashBoardCreate(HodDashboard details);

    ResponseEntity<ResponseDto<?>> dashBoardGet(String redgno);


    ResponseEntity<ResponseDto<?>> allStudents();

    ResponseEntity<ResponseDto<?>> allFaculty();
}
