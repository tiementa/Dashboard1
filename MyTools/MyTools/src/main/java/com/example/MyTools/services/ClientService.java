package com.example.MyTools.services;

import com.example.MyTools.model.Client;

import java.util.List;

public interface ClientService {
    String ajouterClient(Client client);
    List<Client> listeClient();
    void modifierClient(Client client, Integer id);
    Client afficherClientById(Integer id);
    String supprimerClient(Integer id);
}
