package com.ict01.jpaproject02.controller;

import com.ict01.jpaproject02.ResourceNotFoundExeption;
import com.ict01.jpaproject02.model.Student;
import com.ict01.jpaproject02.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

// 해당 프로젝트의 전체적인 jsp를 관리하기 위한 Controller
// CRUD 작업을 수행하게 해주는 class
@Controller
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {
    // Service Constructor Dependency Injection
    private final StudentService studentService;

//    @Autowired
//    public StudentController(StudentService studentService) {
//        this.studentService = studentService;
//    }

    // http://localhost:8080/student/lists/
    // [1] 전체 학생정보 조회 (Read)
    @GetMapping("/lists")
    public String lists(Model model){

        // (1) 모든 학생들을 가져온다.
        List<Student> students = studentService.lists();

        // (2) 가져온 Students Collection을 view에 전달한다.
        model.addAttribute("students",students);

        // http://localhost:8080/student/lists/WEB-INF/views/listStudents.jsp
        return "listStudent";
    }

//    [1]-2
//    @ResponseBody
//    public List<Student> list() throws ClassNotFoundException, SQLException {
//        List<Student> students = studentService.lists();
//
//        return students;
//    }


    // [2] 학생정보 등록 (Create)
    // [2-1] 학생정보 등록 Form
    @GetMapping("/showForm") // URI
    public String showFormAdd(Model model){
        Student student = new Student(); // 새로운 객체 생성
        model.addAttribute("student",student); // 모델 addAttribute를 통해 생성된 객체에서 받아온 정보를 view에 전달.
        // WEB-INF/views/studentForm.jsp 로 찾아간다.
        return "studentForm";
    }

    // [2-2] 학생정보 Action
    @PostMapping("/saveStudent")
    public String saveStudent(@ModelAttribute("student")Student student){
        studentService.saveStudent(student);
        return "redirect:/student/lists";
    }

    // [3] 학생정보 수정 (Update)
    // [3-1] 학생정보 수정 Form
    @GetMapping("/updateForm")
    // @RequestParam : 클릭한 이벤트의 정보를 가져온다. View의 이름과 DB의 이름이 다를경우 꼭 써야한다.
    public String showFormUpdate(@RequestParam("studentId")int id, Model model)throws ResourceNotFoundExeption{
        Student student = studentService.getStudent(id); // DB에서 id를 모두 다 가져와서 담은것
        model.addAttribute("student", student);
        return "updateForm";
    }
    // [3-2] 학생정보 수정 Action
    @PostMapping("/updateStudent")
    // @ModelAttribute 바뀐 object를 통채로 담는다.
    public String updateStudent(@ModelAttribute("student")Student student){
        // 수정된 학생 정보를 저장.
        studentService.saveStudent(student);

        // 학생 정보 수정 후, 학생 목록 화면으로 리다이렉트함.
        return "redirect:/student/lists";
    }

    // [4] 학생정보 삭제 (Delete)
    @GetMapping("/delete")
    public String deleteStudent(@RequestParam("studentId")int id)throws ResourceNotFoundExeption {
        // 학생id에 해당하는 학생 정보를 삭제함.
        studentService.deleteStudent(id);

        // 학생 정보 삭제 후, 학생 목록 화면으로 리다이렉트.
        return "redirect:/student/lists";
    }
}
