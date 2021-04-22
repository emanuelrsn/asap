package br.com.prova.asap.cliente.DTO;

import br.com.prova.asap.api.cliente.dtos.ClienteDTO;

import java.util.List;

public class ClienteResponseDTO {
    private ClienteDTO clienteDto;
    private List<String> errors;

    public ClienteDTO getClienteDto() {
        return clienteDto;
    }

    public void setClienteDto(ClienteDTO clienteDto) {
        this.clienteDto = clienteDto;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
