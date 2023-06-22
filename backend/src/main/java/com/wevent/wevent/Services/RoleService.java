package com.wevent.wevent.Services;

import com.wevent.wevent.Entities.Role;
import com.wevent.wevent.Repositories.RoleRepo;
import com.wevent.wevent.Response.MessageResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RoleService implements IRoleService{
    RoleRepo roleRepo;
    @Override
    public List<Role> getAllRoles() {
        return roleRepo.findAll();
    }

    @Override
    public Role getRole(Long roleId) {
        return roleRepo.findById(roleId).orElse(null);
    }

    @Override
    public ResponseEntity<?> addRole(Role role) {
        Role r = roleRepo.findByNomRole(role.getNomRole());
        if(r==null)
        {
            roleRepo.save(r);
            return ResponseEntity.ok().body(new MessageResponse("Reservation ajouté avec succès"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new MessageResponse("Rôle existe déjà"));
    }

    @Override
    public ResponseEntity<?> updateRole(Long roleId, Role role) {
        Role r = roleRepo.findById(roleId).orElse(null);
        if(r==null)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new MessageResponse("Role introuvable"));
        }
        Role roleToBeSaved = new Role(roleId, role.getNomRole());
        roleRepo.save(roleToBeSaved);
        return ResponseEntity.ok().body(new MessageResponse("Rôle mis à jour avec succès"));
    }

    @Override
    public ResponseEntity<?> deleteRole(Long roleId) {
        Role r = roleRepo.findById(roleId).orElse(null);
        if(r==null)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new MessageResponse("Role introuvable"));
        }
        roleRepo.deleteById(roleId);
        return ResponseEntity.ok().body(new MessageResponse("Rôle supprimé avec succès"));
    }
}
