/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prova.asap.api.apolice;

import br.com.prova.asap.api.apolice.util.GenerateSequence;
import br.com.prova.asap.api.apolice.util.Util;
import com.prova.asap.api.infra.exception.ObjectNotFoundException;
import com.prova.asap.api.usuario.*;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
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

    private String GerarNumeroProcesso(ClienteDTO dto, Integer id) {
        int ano = Calendar.getInstance().get(Calendar.YEAR);
        return ano + dto.getCpf().substring(0, 3) + String.format("%010d", id);
    }

    public ApoliceConsultaDTO insert(ApoliceDTO apoliceDTO) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        ClienteDTO dto = clienteService.getClientesById(apoliceDTO.getIdCliente());
        GenerateSequence<ApoliceSequence, MongoOperations> seq = new GenerateSequence<>(new ApoliceSequence(), mongoOperations);

        Integer idGerado = seq.generateSequence(Apolice.SEQUENCE_NAME);

        Apolice apolice = ApoliceDTO.create(apoliceDTO);
        apolice.setNumero(GerarNumeroProcesso(dto, idGerado));
        apolice.setCliente(ClienteDTO.create(dto));
        apolice.setId(String.valueOf(idGerado));
        return ApoliceConsultaDTO.create(apoliceRepository.save(apolice));
    }

    public void delete(Integer id) {
        apoliceRepository.deleteById(id);
    }

    public ApoliceConsultaDTO update(Integer id, ApoliceDTO apoliceDTO) {

        Optional<Apolice> apolice = apoliceRepository.findById(id);
        if (!apolice.isPresent()) {
            throw new ObjectNotFoundException("Apólice não encontrada.");
        }
        Apolice bd = apolice.get();
        bd.setPlacaVeiculo(apoliceDTO.getPlacaVeiculo());
        bd.setValor(apoliceDTO.getValor());
        bd.setVigenciaFim(apoliceDTO.getVigenciaFim());
        bd.setVigenciaInicio(apoliceDTO.getVigenciaInicio());
        return ApoliceConsultaDTO.create(apoliceRepository.save(bd));
    }

    public ApoliceConsultaDTO getApolicesById(Integer id) {

        return apoliceRepository.findById(id).map(ApoliceConsultaDTO::create).
                orElseThrow(() -> new ObjectNotFoundException("Apólice não encontrada."));

    }

    public ApoliceConsultaDTO getApolicesByNumero(String numero) {
        //diferencaDias
        Optional<Apolice> ap = apoliceRepository.findByNumero(numero);
        if (!ap.isPresent()) {
            throw new ObjectNotFoundException("Apólice não encontrada.");
        }
        ApoliceConsultaDTO apoDto = ApoliceConsultaDTO.create(ap.get());
        
        
        Long dias = Util.diferencaDias(apoDto.getVigenciaFim());
        
        apoDto.setSituacao((dias<0?"Vencida a: "+dias*-1: "Prazo a vencer: "+dias)+" dia(s)");
        

        return apoDto;

    }

    public List<ApoliceConsultaDTO> getApolices() {

        List<Apolice> ap = apoliceRepository.findAll();
        if (!ap.isEmpty()) {
            ap.remove(0);
        }
        return ap.stream()
                .map(f -> ApoliceConsultaDTO.create(f)).collect(Collectors.toList());
    }
}
