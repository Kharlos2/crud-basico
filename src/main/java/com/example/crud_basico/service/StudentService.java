package com.example.crud_basico.service;

import com.example.crud_basico.entity.Student;
import com.example.crud_basico.exception.NotFound;
import com.example.crud_basico.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    public void saveStudent(Student student) {
        studentRepository.save(student);
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Integer id) {


        Optional<Student> student = studentRepository.findById(id);

        if (student.isPresent()){
            return student.get();
        }else {
            throw new NotFound();
        }

    }

    public Student updateStudent(Integer id, Student newStudent){


        Optional<Student> student = studentRepository.findById(id);

        if (student.isPresent()){

            Student oldStudent = student.get();
            oldStudent.setName(newStudent.getName());
            oldStudent.setEmail(newStudent.getEmail());
            oldStudent.setPhone(newStudent.getPhone());

            Student updateStudent = studentRepository.save(oldStudent);

            return updateStudent;

        }else {
            throw new NotFound();
        }

    }

    public boolean deleteStudent (Integer id){


        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()){
            studentRepository.deleteById(id);
            return true;
        }
        else {
            throw new NotFound();
        }


    }

}
