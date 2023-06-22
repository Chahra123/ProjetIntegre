package com.wevent.wevent.Services;

import com.wevent.wevent.Entities.Role;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IRoleService {
    List<Role> getAllRoles();
    Role getRole(Long roleId);
    ResponseEntity<?> addRole(Role role);
    ResponseEntity<?> updateRole(Long roleId,Role role);
    ResponseEntity<?> deleteRole(Long roleId);
}
