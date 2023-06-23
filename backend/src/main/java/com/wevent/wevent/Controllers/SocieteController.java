package com.wevent.wevent.Controllers;

import com.wevent.wevent.Entities.Societe;
import com.wevent.wevent.Services.ISocieteService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("societies")
public class SocieteController {
    ISocieteService iSocieteService;
    @GetMapping
    public List<Societe> getAllSocieties()
    {
        return iSocieteService.getAllSocieties();
    }

    @PostMapping
    public ResponseEntity<?> addSociety(@RequestBody Societe s)
    {
        return iSocieteService.addSociety(s);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteSociety(@PathVariable Long id)
    {
        return iSocieteService.deleteSociety(id);
    }

    @PutMapping("{societeId}")
    public ResponseEntity<?> updateSociety(@RequestBody Societe s, @PathVariable Long societeId)
    {
        return iSocieteService.updateSociety(s,societeId);
    }

    @GetMapping("{id}")
    Societe getSociety(@PathVariable Long id)
    {
        return iSocieteService.getSociety(id);
    }
}
