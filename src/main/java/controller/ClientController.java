package controller;

import entity.Client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import repository.ClientRepository;
import responses.BaseResponse;

import java.util.List;

@RestController
@RequestMapping(value = "/client")
public class ClientController {

    private ClientRepository clientRepository;

    @Autowired
    public ClientController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @RequestMapping(value = "/save")
    public BaseResponse addClient(@RequestBody Client u) {
        BaseResponse response = new BaseResponse();
        try{
            System.out.println(u);
            this.clientRepository.save(u);

            response.setCode(200);
            response.setStatus("Data inserted with success");
            return response;
        } catch (Exception e){
            response.setCode(e.hashCode());
            response.setStatus(e.getMessage());
        }

        return response;
    }

    @RequestMapping(value = "/get")
    public List<Client> getClientName(@RequestParam(value = "name", defaultValue = "") String name) {
        if(name.length() > 0){
            return this.clientRepository.findByName(name);
        } else {
            return this.clientRepository.findAll();
        }
    }


    @RequestMapping(value = "/delete")
    public BaseResponse deleteClient(@RequestParam(value = "cpf", defaultValue = "") String cpf){
        BaseResponse response = new BaseResponse();

        try {
            clientRepository.delete(clientRepository.findOneByCpf(cpf));
            response.setCode(200);
            response.setStatus("Client successfully deleted.");
        } catch (Exception e){
            response.setCode(e.hashCode());
            response.setStatus(e.getMessage());
        }

        return response;
    }
    
    @RequestMapping(value = "/update")
    public BaseResponse updateClient(@RequestBody Client c){
        BaseResponse response = new BaseResponse();
        try {
        	String cpf = c.getCpf();
            Client oldC = clientRepository.findOneByCpf(cpf);

            if(oldC.getCpf().equals(c.getCpf())) {
            	oldC = this.atualizarValores(c, oldC);
	            clientRepository.save(oldC);
	            response.setCode(200);
	            response.setStatus("Product successfully updated.");
            }else {
                response.setStatus("Não encontrado registro com este CPF");
            }

        }catch (Exception e){
            response.setCode(e.hashCode());
            response.setStatus(e.getMessage());
        }

        return response;
    } 
    
    //Função responsável por atualizar os valores editados antes de persistir
    private Client atualizarValores(Client c, Client oldC) {
    	
    	oldC.setEndereco(c.getEndereco());
    	oldC.setEmail(c.getEmail());
    	oldC.setFone(c.getFone());
    	oldC.setObs(c.getObs());
    	oldC.setPis(c.getPis());
    	
    	return oldC;
    }
    
}
