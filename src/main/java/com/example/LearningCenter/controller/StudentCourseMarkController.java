package com.example.LearningCenter.controller;

import com.example.LearningCenter.dto.CourseDTO;
import com.example.LearningCenter.dto.StudentCourseMarkDTO;
import com.example.LearningCenter.dto.StudentDTO;
import com.example.LearningCenter.service.StudentCourseMarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/studentCourseMark")
public class StudentCourseMarkController {
    @Autowired
    private StudentCourseMarkService studentCourseMarkService;

    ///Create StudentCourseMark
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody StudentCourseMarkDTO dto) {
        return ResponseEntity.ok(studentCourseMarkService.create(dto));
    }

    //Get StudentCourseMark by id
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(studentCourseMarkService.get(id));
    }

    // Get List of StudentCourseMark. Return Al
    @GetMapping("/list")
    public ResponseEntity<List<StudentCourseMarkDTO>> getAll() {
        return ResponseEntity.ok(new LinkedList<>(studentCourseMarkService.getAll()));
    }

    //Update StudentCourseMark
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody StudentCourseMarkDTO dto) {
        return ResponseEntity.ok(studentCourseMarkService.update(id, dto));
    }

    //Delete StudentCourseMark by id.
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(studentCourseMarkService.delete(id));
    }

    //Studentni berilgan kundagi olgan baxosi
    @GetMapping("/getByDay/{day}")
    public ResponseEntity<?> getByDay(@PathVariable("day") String day) {
        return ResponseEntity.ok(studentCourseMarkService.getByDay(day));
    }

    //Studentni berilgan 2kun oralig'idagi olgan baxosi
    @GetMapping("/getByBetweenDay")
    public ResponseEntity<?> getByBetweenDay(@RequestParam("student_id") Integer id,
                                             @RequestParam("fromDate") String fromDate,
                                             @RequestParam("toDate") String toDate) {
        return ResponseEntity.ok(studentCourseMarkService.getByBetweenDay(id, LocalDate.parse(fromDate), LocalDate.parse(toDate)));
    }

    //Studentni olgan barcha baxolari vaqt boyicha kamayish tartibida sord qiling.
    @GetMapping("/getByMark{id}")
    public ResponseEntity<?> getByMark(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(studentCourseMarkService.getByMark(id));
    }

    //  Studentni berilgan curse dan olgan baxolari vaqt boyicha kamayish tartibida sord qiling.
    @GetMapping("/getByCourseMark")
    public ResponseEntity<?> getByCourseMark(@RequestParam("student_id") Integer student_id,
                                             @RequestParam("course_id") Integer course_id) {
        return ResponseEntity.ok(studentCourseMarkService.getByCourseMark(student_id, course_id));
    }

    //Studentni eng oxirda olgan baxosi, va curse nomi.
    @GetMapping("/lastCourseMarker/{id}")
    public ResponseEntity<?> lastCourseMarker(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(studentCourseMarkService.lastCourseMarker(id));
    }

    //    Studentni olgan eng katta 3ta baxosi.
    @GetMapping("/getMaxMarkList/{id}")
    public ResponseEntity<?> getMaxMarkList(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(studentCourseMarkService.lastMaxMarkList(id));
    }

    //Studentni eng birinchi olgan baxosi.
    @GetMapping("/getFirstMark/{id}")
    public ResponseEntity<?> getFirstMark(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(studentCourseMarkService.getFirstMark(id));
    }

    //Studenti eng berilgan course dan olgan birinchi baxosi.
    @GetMapping("getFirstMarkByCourseId/{id}")
    public ResponseEntity<?> getFirstMarkByCourseId(@PathVariable("id") Integer studentId,
                                                    @RequestParam("id") Integer courseId) {
        return ResponseEntity.ok(studentCourseMarkService.getFirstMarkByCourseId(studentId, courseId));
    }

    //Studentni berilgan cuorse dan olgan eng baland baxosi.
    @GetMapping("/getStudentMaxMarkByCourse/{id}")
    public ResponseEntity<?> getStudentMaxMarkByCourse(@PathVariable("id") Integer studentId,
                                                       @RequestParam("id") Integer courseId) {
        return ResponseEntity.ok(studentCourseMarkService.getStudentMaxMarkByCourse(studentId, courseId));
    }

    //Studentni o'rtacha olgan baxolari.
    @GetMapping("/getAVGMarkList/{id}")
    public ResponseEntity<?> getAVGMarkList(@PathVariable("id") Integer studentId) {
        return ResponseEntity.ok(studentCourseMarkService.getAVGMarkList(studentId));
    }

    //    Studentni berilgan curse dan olgan o'rtacha baxolari.
    @GetMapping("/getAVGByCourseMark/{id}")
    public ResponseEntity<?> getAVGByCourseMark(@PathVariable("id") Integer studentId,
                                                @RequestParam("course_id") Integer courseId) {
        return ResponseEntity.ok(studentCourseMarkService.getAVGByCourseMark(studentId, courseId));
    }

    //    Studentni berilgan baxodan katta bo'lgan baxolari soni.
    @GetMapping("/getCountByMark/{id}")
    public ResponseEntity<?> getCountByMark(@PathVariable("id") Integer studentId,
                                            @RequestParam("mark") Integer mark) {
        return ResponseEntity.ok(studentCourseMarkService.getCountByMark(studentId, mark));
    }

    //Berilgan Cursdan eng baland baxo.
    @GetMapping("/getMaxMarkByCourse")
    public ResponseEntity<?> getMaxMarkByCourse(@RequestParam("course_id") Integer course_id) {
        return ResponseEntity.ok(studentCourseMarkService.getMaxMarkByCourse(course_id));
    }


    //    Berilgan Cursdan o'lingan o'rtacha baxo.
    @GetMapping("/getAVGMarkByCourse/{id}")
    public ResponseEntity<?> getAVGMarkByCourse(@PathVariable("id") Integer course_id) {
        return ResponseEntity.ok(studentCourseMarkService.getAVGMarkByCourse(course_id));
    }

    //Berilgan Course dan olingna baxolar soni.
    @GetMapping("/getCountByCourse/{id}")
    public ResponseEntity<?> getCountByCourse(@PathVariable("id") Integer courseId) {
        return ResponseEntity.ok(studentCourseMarkService.getCountByCourse(courseId));
    }


    //StudentCourseMark pagination.
    @GetMapping("/paging")
    public ResponseEntity<?> paging(@RequestParam(value = "page", defaultValue = "1") int page,
                                    @RequestParam(value = "size", defaultValue = "30") int size) {
        return ResponseEntity.ok(studentCourseMarkService.paging(page, size));
    }

    //StudentCourseMark pagination by given studentId. List should be sorted by createdDate.
    @GetMapping ("/pagingWithStudentId")
    public ResponseEntity<?> pagingWithStudentId(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "30") int size,
            @RequestParam(value = "studentId")Integer studentId) {
        return ResponseEntity.ok(studentCourseMarkService.pagingWithStudentId(page, size,studentId));
    }

    // StudentCourseMark pagination by given courseId.  List should be sorted by createdDate.
    @GetMapping ("/pagingWithCoursetId")
    public ResponseEntity<?> pagingWithCoursetId(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "30") int size,
            @RequestParam(value = "courseId")Integer courseId) {
        return ResponseEntity.ok(studentCourseMarkService.pagingWithCourseId(page, size,courseId));
    }
}
