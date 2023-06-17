package com.wevent.wevent.Services;

import com.wevent.wevent.Entities.Lieu;
import com.wevent.wevent.Response.MessageResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ILieuService {
    List<Lieu> getAllLieux();

    Lieu addLieu(Lieu l);

    void deleteLieu(Long id);

    ResponseEntity<MessageResponse> updateLieu(Long id, Lieu newl);

    Lieu getLieu(Long id);
}
