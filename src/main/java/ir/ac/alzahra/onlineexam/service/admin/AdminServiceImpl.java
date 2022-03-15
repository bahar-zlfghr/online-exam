package ir.ac.alzahra.onlineexam.service.admin;

import ir.ac.alzahra.onlineexam.model.data.Admin;
import ir.ac.alzahra.onlineexam.model.repository.AdminRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author : Bahar Zolfaghari
 **/
@Service
@Transactional
public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;

    public AdminServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public Admin findById(Long id) {
        return adminRepository.findById(id).orElse(null);
    }
}
