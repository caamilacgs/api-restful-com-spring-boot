package com.camila.myapi.services;

import java.util.List;
import java.util.Optional;

import com.camila.myapi.domain.Usuario;
import com.camila.myapi.services.exceptions.*;
import com.camila.myapi.repositories.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public Usuario findById(Integer id) {
        Optional<Usuario> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id:" + id + ", tipo: " + Usuario.class.getName()));
    }

    public List<Usuario> findAll() {
        return repository.findAll();
    }

    public Usuario update(Integer id, Usuario obj) {
        Usuario newObj = findById(id);
        newObj.setNome(obj.getNome());
        newObj.setLogin(obj.getLogin());
        newObj.setSenha(obj.getSenha());
        return repository.save(newObj);
    }

    public Usuario create(Usuario obj) {
        obj.setId(null);
        return repository.save(obj);
    }

    public void delete(Integer id) {
        findById(id);
        repository.deleteById(id);
    }
}