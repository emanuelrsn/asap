/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prova.asap.api.apolice;

import br.com.prova.asap.api.apolice.util.GenerateSequence;
import com.prova.asap.api.usuario.*;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import org.springframework.data.mongodb.core.MongoOperations;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

/**
 *
 * @author Santana
 */
@Service
public class ApoliceService {

    @Autowired
    private ApoliceRepository apoliceRepository;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private MongoOperations mongoOperations;
    

    public ApoliceDTO insert(ApoliceDTO apoliceDTO) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        ClienteDTO dto = clienteService.getClientesById(apoliceDTO.getIdCliente());
        GenerateSequence<ApoliceSequence, MongoOperations> seq=new GenerateSequence<>(new ApoliceSequence(), mongoOperations);
        
        Integer idGerado = seq.generateSequence(Apolice.SEQUENCE_NAME);

        Apolice apolice = ApoliceDTO.create(apoliceDTO);
        apolice.setCliente(ClienteDTO.create(dto));
        apolice.setId(String.valueOf(idGerado));
        ApoliceDTO apolicereturn = ApoliceDTO.create(apoliceRepository.save(apolice));
        return apolicereturn;

    }

//    public void delete(String cpf) {
//        usuarioRepository.deleteById(cpf);
//    } 
//    public UsuarioDTO update(String cpf, UsuarioDTO usuarioDto) {
//
//        Optional<Usuario> optional = usuarioRepository.findById(cpf);
//        if (!optional.isPresent()) {
//            throw new ObjectNotFoundException("Usuário não encontrado.");
//        }
//        Usuario bd = optional.get();
//
//        bd.setCidade(usuarioDto.getCidade());
//        bd.setNome(usuarioDto.getNome());
//        bd.setUf(usuarioDto.getUf());
//
//        usuarioRepository.save(bd);
//
//        return UsuarioDTO.create(bd);
//    }
//    
//    public UsuarioDTO getUsuariosByCpf(String cpf) {
//        return usuarioRepository.findByCpf(cpf).map(UsuarioDTO::create)
//                .orElseThrow(()->new ObjectNotFoundException("Usuário não encontrado."));
//       
//    }
//
    public List<ApoliceConsultaDTO> getApolices() {
       
            List<Apolice> ap = apoliceRepository.findAll();
            if(!ap.isEmpty()){
                ap.remove(0);
            }
            return ap.stream()
                .map(f -> ApoliceConsultaDTO.create(f)).collect(Collectors.toList());
    }
}
