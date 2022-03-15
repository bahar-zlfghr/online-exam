package ir.ac.alzahra.onlineexam.service.teacher;

import ir.ac.alzahra.onlineexam.model.data.Teacher;
import ir.ac.alzahra.onlineexam.model.repository.TeacherRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author : Bahar Zolfaghari
 **/
@Service
@Transactional
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;
    private final PasswordEncoder passwordEncoder;

    public TeacherServiceImpl(TeacherRepository teacherRepository, PasswordEncoder passwordEncoder) {
        this.teacherRepository = teacherRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void save(Teacher teacher) {
        teacher.setPassword(passwordEncoder.encode(teacher.getPassword()));
        teacherRepository.save(teacher);
    }

    @Override
    public Teacher findById(Long id) {
        return teacherRepository.getById(id);
    }
}
