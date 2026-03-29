package SmartAirAndHealthMointoring.demo.serviceImpl;

import SmartAirAndHealthMointoring.demo.Repository.HealthVitalRepo;
import SmartAirAndHealthMointoring.demo.configuration.ResponseDto;
import SmartAirAndHealthMointoring.demo.model.HealthVital;
import SmartAirAndHealthMointoring.demo.service.HealthVitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public  class HealthVitalImpl implements HealthVitalService {

    @Autowired
    HealthVitalRepo healthVitalRepo;

    @Override
    public ResponseEntity<ResponseDto<?>> vitals() {
        try
        {
            List<HealthVital> allDocs = healthVitalRepo.findAll();

            return ResponseEntity.ok(
                    ResponseDto.success(allDocs,"All users Health Vitals")
            );

        }catch(Exception e)
        {
            return ResponseEntity.internalServerError().body(
                    ResponseDto.error("Internal Server error plese try After Some time")
            );
        }

    }

    @Override
    public ResponseEntity<ResponseDto<?>> vital(String redgno) {
        try
        {
            List<HealthVital> vital = healthVitalRepo.findByRegisterno(redgno);
            return ResponseEntity.ok(
                    ResponseDto.success(vital,"Vitals of the user with that register number")
            );
        }
        catch(Exception e)
        {
            return ResponseEntity.internalServerError().body(
                    ResponseDto.error("Plese try again later..!")
            );
        }


    }
}
