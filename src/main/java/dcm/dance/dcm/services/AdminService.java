package dcm.dance.dcm.services;

import dcm.dance.dcm.repositories.AdminRepository;
import dcm.dance.dcm.repositories.ParticipantRepository;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    private AdminRepository adminRepository;
    private ParticipantRepository participantRepository;

    public AdminService(AdminRepository adminRepository, ParticipantRepository participantRepository) {
        this.adminRepository = adminRepository;
        this.participantRepository = participantRepository;
    }
}
