package com.wevent.wevent.Services;

import com.wevent.wevent.Entities.Avis;

import java.util.List;

public interface IAvisService {
    List<Avis> getAllAvis();

    Avis addAvis(Avis rs);

    void deleteAvis(Long id);

    Avis updateAvis(Avis rs);

    Avis getAvis(Long id);
}
