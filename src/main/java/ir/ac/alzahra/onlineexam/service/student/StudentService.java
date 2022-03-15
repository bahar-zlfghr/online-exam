package ir.ac.alzahra.onlineexam.service.student;

import ir.ac.alzahra.onlineexam.model.data.Student;

/**
 * @author : Bahar Zolfaghari
 **/
public interface StudentService {

    void save(Student student);
    Student findById(Long id);
}
