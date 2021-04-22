package br.com.prova.asap.cliente;

import br.com.prova.asap.ProvaAsapLogApplication;
import br.com.prova.asap.Util.Util;
import br.com.prova.asap.api.cliente.dtos.ClienteDTO;
import br.com.prova.asap.api.cliente.services.ClienteService;
import br.com.prova.asap.api.infra.exception.ObjectNotFoundException;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes= ProvaAsapLogApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ClienteTest {

	@Autowired
	private ClienteService clienteService;


	@Test
	void contextLoads() {
		ClienteDTO dto = new ClienteDTO();
		dto.setCidade("RIO DE JANEIRO");
		dto.setCpf("06158413097");
		dto.setUf("RJ");
		dto.setNome("JOAO ANDRADE");
		ClienteDTO dtoInsert = clienteService.insert(dto);

		//Verifiando se retorno da inserção
		Assert.assertNotNull(dtoInsert);

		//Verificando se inseriu
		ClienteDTO dtoConsulta = clienteService.getById(Integer.parseInt(dtoInsert.getId()));
		Assert.assertTrue(dtoInsert.getId() !=null);

		//Validando dados
		Assert.assertEquals("RIO DE JANEIRO", dtoConsulta.getCidade());
		Assert.assertEquals("06158413097", dtoConsulta.getCpf());
		Assert.assertEquals("JOAO ANDRADE", dtoConsulta.getNome());

		//Autualizando o registo
		dtoConsulta.setNome("JOAO ANDRADE FILHO");
		clienteService.update(Integer.parseInt(dtoConsulta.getId()), dtoConsulta);

		//Verificando se atualizou
		dtoConsulta = clienteService.getById(Integer.parseInt(dtoConsulta.getId()));
		Assert.assertEquals("JOAO ANDRADE FILHO", dtoConsulta.getNome());


		//Deletando um cliente
		clienteService.delete(Integer.parseInt(dtoConsulta.getId()));

		//Verifica se deletou
		try {
			dtoConsulta = clienteService.getById(Integer.parseInt(dtoInsert.getId()));
		} catch(Exception ex){
			if(!(ex instanceof ObjectNotFoundException)) {
				Assert.fail("Exceção Não Esperada!!!");
			} else {
				dtoConsulta = new ClienteDTO();
			}
		}
		Assert.assertNull(dtoConsulta.getId());

	}

}
