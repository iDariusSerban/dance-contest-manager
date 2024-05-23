package DanceContestManager.services;



import DanceContestManager.dtos.AuthRequestDTO;
import DanceContestManager.entities.Admin;
import DanceContestManager.repositories.AdminRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
@AllArgsConstructor
@Service
public class AdminService {

    private AdminRepository adminRepository;
    private Keycloak keycloak;



    @Transactional
    public Admin register (AuthRequestDTO authRequestDTO){
        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setUsername(authRequestDTO.getUsername());
        userRepresentation.setCredentials(Collections.singletonList(createPasswordCredentials(authRequestDTO.getPassword())));
        userRepresentation.setEnabled(true);
        keycloak.realm("master").users().create(userRepresentation);

        // Fetch Keycloak ID
        String keycloakId = keycloak.realm("master").users().search(authRequestDTO.getUsername()).get(0).getId();

        // Create user in local DB
        Admin admin = new Admin();
        admin.setUserName(authRequestDTO.getUsername());
//        Role role = roleRepository.findByRoleType(RoleType.ROLE_USER);
//        user.getRoles().add(role);
//        role.getUsers().add(user);
        return adminRepository.save(admin);
    }

    private CredentialRepresentation createPasswordCredentials(String password) {
        CredentialRepresentation passwordCredentials = new CredentialRepresentation();
        passwordCredentials.setType(CredentialRepresentation.PASSWORD);
        passwordCredentials.setValue(password);
        passwordCredentials.setTemporary(false);
        return passwordCredentials;
    }

}