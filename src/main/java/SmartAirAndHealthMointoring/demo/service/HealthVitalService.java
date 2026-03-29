package SmartAirAndHealthMointoring.demo.service;

import SmartAirAndHealthMointoring.demo.configuration.ResponseDto;
import org.springframework.http.ResponseEntity;

public interface HealthVitalService {
    ResponseEntity<ResponseDto<?>> vitals();

    ResponseEntity<ResponseDto<?>> vital(String redgno);
}
