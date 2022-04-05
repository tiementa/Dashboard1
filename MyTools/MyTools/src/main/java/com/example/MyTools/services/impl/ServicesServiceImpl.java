package com.example.MyTools.services.impl;

import com.example.MyTools.model.Appareil;
import com.example.MyTools.model.Photo;
import com.example.MyTools.model.Services;
import com.example.MyTools.repository.ServicesRepository;
import com.example.MyTools.services.ServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
@Service
public class ServicesServiceImpl implements ServicesService {
    public ServicesServiceImpl() {
        super();
    }

    @Autowired
    ServicesRepository servicesRepository;

    @Override
    public Services ajoutServices(Services services) {
        return this.servicesRepository.save(services);
    }

    @Override
    public Services ajoutePhoto(Integer id, MultipartFile file) throws IOException {
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getName());
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Services services= servicesRepository.findById(id).get();
        services.setPhoto(fileName);

        String uploadDir = "src/main/resources/images/services/"+services.getId();
        Photo.saveFile(uploadDir, fileName, file);
        this.servicesRepository.save(services);
        return services;
    }

    @Override
    public List<Services> afficherListServices() {
        return this.servicesRepository.findAll();
    }

    @Override
    public void modifierServices(Services services, Integer id) {
        Services serviceExistant = this.servicesRepository.findById(id).orElseThrow();
        serviceExistant.setBesoinDuService(services.getBesoinDuService());
        serviceExistant.setLibelle(services.getLibelle());
        serviceExistant.setClient(services.getClient());
    }

    @Override
    public Services afficherById(Integer id) {
        return this.servicesRepository.findById(id).get();
    }

    @Override
    public String supprimerById(Integer id) {
        this.servicesRepository.deleteById(id);
        return "Supprimer avec success";
    }

    @Override
    public byte[] recupPhoto(Integer id) throws IOException {
        Services artcl = servicesRepository.findById(id).get();
        String ServicesPhoto =artcl.getPhoto();
        File file = new File("src/main/resources/images/services/"+artcl.getId()+"/"+ServicesPhoto);
        Path path = Paths.get(file.toURI());
        return  Files.readAllBytes(path);
    }
}
