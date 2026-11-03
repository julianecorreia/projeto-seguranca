package br.unipar.seguranca.service;

import org.springframework.stereotype.Service;

@Service
public class AulaService {
    public String publico() {
        return "Esta rota é pública";
    }

    public String privado(String nome) {
        return "Olá, " + nome + ". Token válido!";
    }
}
