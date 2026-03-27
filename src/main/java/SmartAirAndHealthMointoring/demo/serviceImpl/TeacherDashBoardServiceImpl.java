package SmartAirAndHealthMointoring.demo.serviceImpl;

import SmartAirAndHealthMointoring.demo.Repository.TeacherDashBoardRepo;
import SmartAirAndHealthMointoring.demo.configuration.ResponseDto;
import SmartAirAndHealthMointoring.demo.model.TeacherDashBoard;
import SmartAirAndHealthMointoring.demo.service.TeacherDashBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TeacherDashBoardServiceImpl implements TeacherDashBoardService {

    @Autowired
    private TeacherDashBoardRepo teacherDashBoardRepo;

    @Override
    public ResponseEntity<ResponseDto<?>> dashBoardcreation(TeacherDashBoard details) {

        if (isInvalid(details.getRedgno())) return errorResponse("Registration number is required");
        if (isInvalid(details.getEmail())) return errorResponse("Email is required");
        if (isInvalid(details.getDepartment())) return errorResponse("Department is required");
        if (isInvalid(details.getAge())) return errorResponse("Age expected");
        if (details.getMobileno() == null || details.getMobileno().trim().length() != 10) {
            return errorResponse("Mobile number must be exactly 10 digits");
        }

        try {
            String normalizedRegNo = details.getRedgno().trim().toLowerCase();

            if (teacherDashBoardRepo.existsById(normalizedRegNo)) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(
                        ResponseDto.error("Dashboard already exists for this Registration Number")
                );
            }

            details.setRedgno(normalizedRegNo);
            teacherDashBoardRepo.save(details);

            return ResponseEntity.ok(ResponseDto.success(details, "Dashboard Created Successfully"));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    ResponseDto.error("Internal Server Error. Please try again later.")
            );
        }
    }

    @Override
    public ResponseEntity<ResponseDto<?>> dashBoardGet(String redgno) {

        redgno = redgno.toLowerCase();
        Optional<TeacherDashBoard> details = teacherDashBoardRepo.findById(redgno);

        try{
            if(details.isEmpty())
            {
                return ResponseEntity.badRequest().body(
                        ResponseDto.error("Invalid Registered number plese registerd..!")
                );
            }

            return ResponseEntity.ok(
                    ResponseDto.success(details,"Dashboard Retrived Successfully..!")
            );
        }
        catch(Exception e)
        {
            return  ResponseEntity.internalServerError().body(
                    ResponseDto.error("Internal server error Pls try again later..!")
            );
        }
    }

    private boolean isInvalid(String value) {
        return value == null || value.trim().isEmpty();
    }

    private ResponseEntity<ResponseDto<?>> errorResponse(String message) {
        return ResponseEntity.badRequest().body(ResponseDto.error(message));
    }
}