package com.rakib.collegeERPsystem.service;
import com.rakib.collegeERPsystem.dto.CourseDTO;
import com.rakib.collegeERPsystem.entity.Course;
import com.rakib.collegeERPsystem.entity.Department;
import com.rakib.collegeERPsystem.repository.CourseRepository;
import com.rakib.collegeERPsystem.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final DepartmentRepository departmentRepository;

    private CourseDTO toDTO(Course course) {
        return CourseDTO.builder()
                .courseId(course.getId())
                .courseCode(course.getCourseCode())
                .courseName(course.getCourseName())
                .credits(course.getCredits())
                .deptId(course.getDepartment() != null ? course.getDepartment().getId() : null)
                .deptName(course.getDepartment() != null ? course.getDepartment().getDeptName() : null)
                .build();
    }

    private Course toEntity(CourseDTO dto) {
        Course course = new Course();
        course.setId(dto.getCourseId());
        course.setCourseCode(dto.getCourseCode());
        course.setCourseName(dto.getCourseName());
        course.setCredits(dto.getCredits());

        if (dto.getDeptId() != null) {
            Department dept = departmentRepository.findById(dto.getDeptId())
                    .orElseThrow(() -> new RuntimeException("Department not found"));
            course.setDepartment(dept);
        }
        return course;
    }

    public CourseDTO createCourse(CourseDTO dto) {
        return toDTO(courseRepository.save(toEntity(dto)));
    }

    public List<CourseDTO> getAllCourses() {
        return courseRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public CourseDTO getCourseById(Long id) {
        return courseRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new RuntimeException("Course not found"));
    }

    public CourseDTO updateCourse(Long id, CourseDTO dto) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        course.setCourseCode(dto.getCourseCode());
        course.setCourseName(dto.getCourseName());
        course.setCredits(dto.getCredits());

        if (dto.getDeptId() != null) {
            Department dept = departmentRepository.findById(dto.getDeptId())
                    .orElseThrow(() -> new RuntimeException("Department not found"));
            course.setDepartment(dept);
        }

        return toDTO(courseRepository.save(course));
    }

    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

    public List<CourseDTO> getCoursesByDepartment(Long deptId) {
        return courseRepository.findByDepartmentId(deptId)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

}
