package com.example.MyTools.services;

import com.example.MyTools.model.Accessoires;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface AccessoiresService {
    Accessoires AjoutAccessoires(Accessoires accessoires);
    Accessoires ajoutePhoto(Integer id, MultipartFile file) throws IOException;
    List<Accessoires> ListeAccessoires();
    void modifierAccoires(Accessoires accessoires, Integer idAccessoires);
    String supprimerAccessoires(Integer id);
    Accessoires afficherAccrssoiresById(Integer id);
    byte[] recupPhoto(Integer id) throws IOException;

}
