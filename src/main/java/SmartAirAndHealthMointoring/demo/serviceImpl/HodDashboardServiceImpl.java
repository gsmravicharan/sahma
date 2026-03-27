package SmartAirAndHealthMointoring.demo.serviceImpl;

import SmartAirAndHealthMointoring.demo.Repository.HodDashboardRepo;
import SmartAirAndHealthMointoring.demo.Repository.UserRepo;
import SmartAirAndHealthMointoring.demo.configuration.ResponseDto;
import SmartAirAndHealthMointoring.demo.constants.Roles;
import SmartAirAndHealthMointoring.demo.model.HodDashboard;
import SmartAirAndHealthMointoring.demo.model.User;
import SmartAirAndHealthMointoring.demo.service.HodDashBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class HodDashboardServiceImpl implements HodDashBoardService {

    @Autowired
    HodDashboardRepo hodDashboardRepo;

    @Autowired
    UserRepo userRepo;

    private boolean isInvalid(String value) {
        return value == null || value.trim().isEmpty();
    }

    private ResponseEntity<ResponseDto<?>> errorResponse(String message) {
        return ResponseEntity.badRequest().body(ResponseDto.error(message));
    }

    @Override
    public ResponseEntity<ResponseDto<?>> dashBoardCreate(HodDashboard details) {
        if (isInvalid(details.getRedgno())) return errorResponse("Registration number is required");
        if (isInvalid(details.getEmail())) return errorResponse("Email is required");
        if (isInvalid(details.getDepartment())) return errorResponse("Department is required");
        if (isInvalid(details.getAge())) return errorResponse("Age expected");
        if (details.getMobileno() == null || details.getMobileno().trim().length() != 10) {
            return errorResponse("Mobile number must be exactly 10 digits");
        }

        try {
            String normalizedRegNo = details.getRedgno().trim().toLowerCase();

            if (hodDashboardRepo.existsById(normalizedRegNo)) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(
                        ResponseDto.error("Dashboard already exists for this Registration Number")
                );
            }

            details.setRedgno(normalizedRegNo);
            hodDashboardRepo.save(details);

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
        try
        {
            Optional<HodDashboard> details = hodDashboardRepo.findById(redgno);
            if(details.isEmpty())
            {
                ResponseEntity.badRequest().body(
                        ResponseDto.error("Plese register with  this register number and try again after Some time")
                );
            }

            return ResponseEntity.ok(
                    ResponseDto.success(details,"Sucessfully fetched the Dashboard")
            );

        }catch(Exception e){
            return ResponseEntity.internalServerError().body(
                    ResponseDto.error("Internal ServerError Plese try Again after Some time")
            );
        }
    }

    @Override
    public ResponseEntity<ResponseDto<?>> allStudents() {
        try {
            List<User> students = userRepo.findAllByRole(Roles.ROLE_STUDENT);


            if (students.isEmpty()) {
                return ResponseEntity.ok(
                        ResponseDto.success(students, "No students found")
                );
            }

            return ResponseEntity.ok(
                    ResponseDto.success(students, "Student list retrieved successfully")
            );

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    ResponseDto.error("An error occurred while fetching students")
            );
        }
    }

    @Override
    public ResponseEntity<ResponseDto<?>> allFaculty() {


        try {
            List<User> teachers = userRepo.findAllByRole(Roles.ROLE_FACULTY);

            if (teachers.isEmpty()) {
                return ResponseEntity.ok(
                        ResponseDto.success(teachers, "No students found")
                );
            }

            return ResponseEntity.ok(
                    ResponseDto.success(teachers, "Student list retrieved successfully")
            );

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    ResponseDto.error("An error occurred while fetching students")
            );
        }

    }


}
