package SmartAirAndHealthMointoring.demo.serviceImpl;

import SmartAirAndHealthMointoring.demo.Repository.StudentDashBoardRepo;
import SmartAirAndHealthMointoring.demo.configuration.ResponseDto;
import SmartAirAndHealthMointoring.demo.model.StudentDashboard;
import SmartAirAndHealthMointoring.demo.service.StudentDashBoardService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentDashboardServiceImpl implements StudentDashBoardService {

    @Autowired
    StudentDashBoardRepo repository;

    @Override
    public ResponseEntity<ResponseDto<?>> dashBoardCreation(StudentDashboard details) {

        if (details.getRedgno() == null || details.getRedgno().trim().isEmpty()) {
            return ResponseEntity.badRequest().body(ResponseDto.error("Registration number is required"));
        }

        if (details.getEmail() == null || details.getEmail().trim().isEmpty()) {
            return ResponseEntity.badRequest().body(ResponseDto.error("Email is required"));
        }

        if (details.getDepartment() == null || details.getDepartment().trim().isEmpty()) {
            return ResponseEntity.badRequest().body(ResponseDto.error("Department is required"));
        }

        if (details.getAge() == null || details.getAge().trim().isEmpty()) {
            return ResponseEntity.badRequest().body(ResponseDto.error("Age expected"));
        }

        if (details.getMobileno() == null || details.getMobileno().trim().isEmpty()) {
            return ResponseEntity.badRequest().body(ResponseDto.error("Mobile number is required"));
        }

        if (details.getMobileno().trim().length() != 10) {
            return ResponseEntity.badRequest().body(ResponseDto.error("Mobile number must be exactly 10 digits"));
        }

        try {
            if (repository.existsById(details.getRedgno())) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(ResponseDto.error("Dashboard already exists"));
            }

            details.setRedgno(details.getRedgno().toLowerCase());
            StudentDashboard saved = repository.save(details);
            return ResponseEntity.ok(ResponseDto.success(saved,"Dashboard Created Successfully..!"));

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ResponseDto.error("Database Error"));
        }
    }

    @Override
    public ResponseEntity<ResponseDto<?>> dashBoardGet(String redgno) {

        try{

            redgno = redgno.toLowerCase();
            Optional<StudentDashboard> details = repository.findById(redgno);
            if(details.isEmpty())
            {
                return ResponseEntity.badRequest().body(
                        ResponseDto.error("No User registered using this Registered number")
                );
            }
            return ResponseEntity.ok(ResponseDto.success(details,"DashBoard Details"));

        }catch(Exception e)
        {
            return ResponseEntity.badRequest().body(
                    ResponseDto.error("No User registered using this Registered number")
            );
        }
    }
}
