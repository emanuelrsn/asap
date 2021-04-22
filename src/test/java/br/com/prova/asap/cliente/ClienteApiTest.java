//package br.com.prova.asap.cliente;
//
//import br.com.prova.asap.ProvaAsapLogApplication;
//import br.com.prova.asap.Util.Util;
//import br.com.prova.asap.api.cliente.dtos.ClienteDTO;
//import br.com.prova.asap.cliente.DTO.ClienteResponseDTO;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import org.springframework.http.HttpEntity;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.List;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes= ProvaAsapLogApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class ClienteApiTest {
//
//
//    @Autowired
//    Util util;
//
//    @Test
//    public void BuscarClienteTeste(){
//        ClienteDTO cliente = (ClienteDTO) util.get("/api/v1/cliente/9", ClienteDTO.class).getBody();
//        System.out.println(cliente.toString());
//
//    }
//
//    @Test
//    public void BuscarClientesTeste(){
//        List<ClienteDTO> cliente = (List<ClienteDTO>) util.gets("/api/v1/cliente/").getBody();
//        System.out.println(cliente.toString());
//
//    }
//
//    @Test
//    public void SalvarClienteTeste(){
//
//        ClienteDTO cdto = new ClienteDTO();
//        cdto.setNome("JUNIOR FERREIRA");
//        cdto.setUf("PA");
//        cdto.setCpf("79380756046");
//        cdto.setCidade("BELEM");
//
//        HttpEntity request = new HttpEntity<>(cdto);
//        ResponseEntity response = util.post("/api/v1/cliente", request);
//
//
//        //ResponseEntity response = rest.postForEntity("/api/v1/cliente",cdto,null);
//        System.out.println(response.getStatusCode());
//        System.out.println(response.toString());
//        ClienteResponseDTO teste = (ClienteResponseDTO) response.getBody();
//        System.out.println(teste.getErrors().get(0));
//        System.out.println(response.getBody());
//
//    }
//
//}
