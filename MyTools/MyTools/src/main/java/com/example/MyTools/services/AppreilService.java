package com.example.MyTools.services;

import com.example.MyTools.model.Appareil;
import com.example.MyTools.model.Produits;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface AppreilService {
    Appareil ajouterAppareil(Appareil appareil);
    Appareil ajoutePhoto(Integer id, MultipartFile file) throws IOException;
    List<Appareil> listeAppareil();
    String supprimerAppareil(Integer id);
    void modifierAppareil(Appareil appareil, Integer idAppareil);
    Appareil afficherAppareilById(Integer id);
    List<Appareil> listeTypeAppareil(Produits produits);
    byte[] recupPhoto(Integer id) throws IOException;
}
