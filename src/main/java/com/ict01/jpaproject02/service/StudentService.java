package com.ict01.jpaproject02.service;

import com.ict01.jpaproject02.ResourceNotFoundExeption;
import com.ict01.jpaproject02.model.Student;

import java.util.List;

public interface StudentService {
    //(1) 전체 학생 조회 (Read)
    public List<Student> lists(); //lists <- 네이밍 룰

    //(2) 학생 등록 (Create)
    public void saveStudent(Student student);

    //(3) 학생정보 수정 (Update)
    public Student getStudent(int id) throws ResourceNotFoundExeption;

    //(4) 학생정보 삭제 (Delete)
    public void deleteStudent(int id) throws ResourceNotFoundExeption;

}
