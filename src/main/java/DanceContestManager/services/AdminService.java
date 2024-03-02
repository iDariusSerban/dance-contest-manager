package DanceContestManager.services;

import DanceContestManager.repositories.AdminRepository;
import DanceContestManager.repositories.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    private AdminRepository adminRepository;
    private ParticipantRepository participantRepository;

    @Autowired
    public AdminService(AdminRepository adminRepository, ParticipantRepository participantRepository) {
        this.adminRepository = adminRepository;
        this.participantRepository = participantRepository;
    }


}
