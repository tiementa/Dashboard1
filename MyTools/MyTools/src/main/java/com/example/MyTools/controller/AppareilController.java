package com.example.MyTools.controller;

import com.example.MyTools.model.Appareil;
import com.example.MyTools.model.Photo;
import com.example.MyTools.model.Produits;
import com.example.MyTools.services.AppreilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/mytools/appareil")
@CrossOrigin("*")
public class AppareilController {
    @Autowired
    AppreilService appreilService;

    @PostMapping("/ajouter")
    public Appareil ajouterAppareil(@RequestBody Appareil appareil){
        return this.appreilService.ajouterAppareil(appareil);
    }

    @PutMapping("/Ajoutphoto/{id}")
    @ResponseBody
    public Appareil ajouterPhoto(@PathVariable Integer id, @RequestParam("file") MultipartFile file)
            throws IOException{
        return this.appreilService.ajoutePhoto(id, file);
    }
    @GetMapping("/lister")
    public List<Appareil> listeAppareil(){
        return this.appreilService.listeAppareil();
    }

    @GetMapping(value="/photo/{id}", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE, MediaType.ALL_VALUE})
    byte[] getPhoto(@PathVariable Integer id) throws IOException{
        return appreilService.recupPhoto(id);
    }
    @GetMapping("/{id}")
    public Appareil afficherAppareilById(@PathVariable("id") Integer id){
        return this.appreilService.afficherAppareilById(id);
    }
    @PutMapping("/modifier/{id}")
    public String modifierAppareil(@RequestBody Appareil appareil, @PathVariable("id") Integer id){
        this.appreilService.modifierAppareil(appareil, id);
        return "Mise de l'appareil Valider";
    }
    @DeleteMapping("/supprimer/{id}")
    public String supprimerAppareil(@PathVariable("id") Integer id){
        this.appreilService.supprimerAppareil(id);
        return "Appareil Supprimer avec Success";
    }
    @GetMapping("/typeAppareil/{appareil}")
    public List<Appareil> listAppareilParType(@PathVariable("appareil") Produits produits){
        return this.appreilService.listeTypeAppareil(produits);
    }
}
