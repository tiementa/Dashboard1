package com.example.MyTools.services.impl;

import com.example.MyTools.model.Accessoires;
import com.example.MyTools.model.Appareil;
import com.example.MyTools.model.Photo;
import com.example.MyTools.repository.AccessoiresRepository;
import com.example.MyTools.services.AccessoiresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class AccessoiresServiceImpl implements AccessoiresService {
    @Autowired
    AccessoiresRepository accessoiresRepository;

    @Override
    public Accessoires AjoutAccessoires(Accessoires accessoires) {
        return this.accessoiresRepository.save(accessoires);
    }

    @Override
    public Accessoires ajoutePhoto(Integer id, MultipartFile file) throws IOException {
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getName());
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Accessoires accessoires= accessoiresRepository.findById(id).get();
        accessoires.setPhoto(fileName);

        String uploadDir = "src/main/resources/images/accessoires/"+accessoires.getId();
        Photo.saveFile(uploadDir, fileName, file);
        this.accessoiresRepository.save(accessoires);
        return accessoires;
    }

    @Override
    public List<Accessoires> ListeAccessoires() {
        return this.accessoiresRepository.findAll();
    }

    @Override
    @Transactional
    public void modifierAccoires(Accessoires accessoires, Integer idAccessoires) {
        Accessoires accessoireExistant = this.accessoiresRepository.findById(idAccessoires).orElseThrow();
        accessoireExistant.setNom(accessoires.getNom());
        accessoireExistant.setEtat(accessoires.getEtat());
        accessoireExistant.setPhoto(accessoires.getPhoto());
        accessoireExistant.setPrix(accessoires.getPrix());
        accessoireExistant.setCaracteristique(accessoires.getCaracteristique());
        accessoireExistant.setProduits(accessoires.getProduits());
        accessoiresRepository.save(accessoireExistant);
    }

    @Override
    public String supprimerAccessoires(Integer id) {
        this.accessoiresRepository.deleteById(id);
        return "Supprimer avec success";
    }

    @Override
    public Accessoires afficherAccrssoiresById(Integer id) {
        return this.accessoiresRepository.findById(id).get();
    }

    @Override
    public byte[] recupPhoto(Integer id) throws IllegalStateException,IOException {
        Accessoires artcl = accessoiresRepository.findById(id).get();
        String AccessoiresPhoto =artcl.getPhoto();
        File file = new File("src/main/resources/images/accessoires/"+artcl.getId()+"/"+AccessoiresPhoto);
        Path path = Paths.get(file.toURI());
        return  Files.readAllBytes(path);
    }
}
