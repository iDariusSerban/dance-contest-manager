package dcm.dance.dcm.services;

import dcm.dance.dcm.entities.Contest;
import dcm.dance.dcm.entities.Division;
import dcm.dance.dcm.entities.DivisionType;
import dcm.dance.dcm.repositories.AdminRepository;
import dcm.dance.dcm.repositories.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
