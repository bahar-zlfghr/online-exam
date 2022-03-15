package ir.ac.alzahra.onlineexam.service.teacher;

import ir.ac.alzahra.onlineexam.model.data.Teacher;

/**
 * @author : Bahar Zolfaghari
 **/
public interface TeacherService {

    void save(Teacher teacher);
    Teacher findById(Long id);
}
