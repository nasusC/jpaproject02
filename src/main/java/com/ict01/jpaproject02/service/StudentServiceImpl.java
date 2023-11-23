package com.ict01.jpaproject02.service;

import com.ict01.jpaproject02.ResourceNotFoundExeption;
import com.ict01.jpaproject02.model.Student;
import com.ict01.jpaproject02.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StudentServiceImpl implements StudentService {
    // Constructor Dependency Injection
    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> lists() {
        return studentRepository.findAll();
    }

    @Override
    public void saveStudent(Student student) {
        studentRepository.save(student);

    }

    @Override
    public Student getStudent(int id) throws ResourceNotFoundExeption {
        return studentRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundExeption(id));
    }

    @Override
    public void deleteStudent(int id) throws ResourceNotFoundExeption {
        studentRepository.deleteById(id);

    }
}
