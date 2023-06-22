package com.wevent.wevent.Controllers;

import com.wevent.wevent.Entities.Role;
import com.wevent.wevent.Services.IRoleService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("roles")
public class RoleController {
    IRoleService iRoleService;
    @GetMapping
    public List<Role> getAllRoles() {
        return iRoleService.getAllRoles();
    }
    @GetMapping("{roleId}")
    public Role getRole(@PathVariable Long roleId){
        return iRoleService.getRole(roleId);
        }
        @PostMapping
        public ResponseEntity<?> addRole(@RequestBody Role role)
        {
            return iRoleService.addRole(role);}
    @PutMapping("{roleId}")
    public ResponseEntity<?> updateRole(@PathVariable Long roleId,@RequestBody Role role)
            {
                return iRoleService.updateRole(roleId,role);
            }
 @DeleteMapping("{roleId}")
        ResponseEntity<?> deleteRole(@PathVariable Long roleId)
        {
            return iRoleService.deleteRole(roleId);}
}
