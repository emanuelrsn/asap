/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.prova.asap.api.apolice.services;

import br.com.prova.asap.api.abstracts.AService;
import br.com.prova.asap.api.apolice.models.Apolice;
import br.com.prova.asap.api.apolice.models.ApoliceSequence;
import br.com.prova.asap.api.apolice.repositorys.ApoliceRepository;
import br.com.prova.asap.api.apolice.dtos.ApoliceDTO;
import br.com.prova.asap.api.apolice.dtos.ApoliceConsultaDTO;
import br.com.prova.asap.api.cliente.services.ClienteService;
import br.com.prova.asap.api.apolice.util.Util;
import br.com.prova.asap.api.cliente.models.Cliente;
import br.com.prova.asap.api.infra.exception.ObjectNotFoundException;
import java.util.Calendar;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Santana
 */
@Service
public class ApoliceService extends AService<Apolice, ApoliceDTO, ApoliceSequence> {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    public ApoliceService(ApoliceRepository apoliceRepository, ApoliceDTO apoliceDTO,
                          Apolice apolice, ApoliceSequence s) {
        super(apoliceRepository, apoliceDTO, apolice, s);
    }


    @Override
    public ApoliceDTO insert(ApoliceDTO apoliceDTO) {
        Cliente cliente = clienteService.getEntityById(apoliceDTO.getIdCliente());
        Apolice apolice = apoliceDTO.create();
        apolice.setCliente(cliente);
        return super.insert(apolice);
    }

    public ApoliceConsultaDTO getApolicesByNumero(String numero) {
        Optional<Apolice> ap = ((ApoliceRepository) this.mongoRepository).findByNumero(numero);
        if (!ap.isPresent()) {
            throw new ObjectNotFoundException("Apólice não encontrada.");
        }
        ApoliceConsultaDTO apoDto = new ApoliceConsultaDTO().create(ap.get());

        Long dias = Util.diferencaDias(apoDto.getVigenciaFim());

        apoDto.setSituacao((dias < 0 ? "Vencida a: " + dias * -1 : "Prazo a vencer: " + dias) + " dia(s)");

        return apoDto;

    }

    @Override
    public void validationsBeforeInsert(ApoliceDTO t) {
    }

    @Override
    public void validationsBeforeUpdate(ApoliceDTO t) {
    }

}
