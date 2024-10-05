package com.javanet.student.service;

import com.javanet.student.model.Student;
import com.javanet.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService{
    @Autowired
    private StudentRepository studentRepository;


    public List<Student> getAllStudents() {
        return  studentRepository.findAll();
    }

    @Override
    public Student saveStudent(Student student) {
        return  studentRepository.save(student);
    }

    @Override
    public Student getStudentById(Long id) {
        return studentRepository.findById(id).get();
    }

    @Override
    public Student updateStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudentById(Long id) {
           studentRepository.deleteById(id);
    }
}
