package controller;

import entity.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import repository.ServiceRepository;
import responses.BaseResponse;

import java.util.List;

@RestController
@RequestMapping(value = "/service")
public class ServiceController {

    private ServiceRepository serviceRepository;

    @Autowired
    public ServiceController(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @RequestMapping(value = "/save")
    public BaseResponse addService(@RequestBody Service u) {
        BaseResponse response = new BaseResponse();
        try{
            System.out.println(u);
            this.serviceRepository.save(u);

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
    public List<Service> getServiceName(@RequestParam(value = "name", defaultValue = "") String name) {
        if(name.length() > 0){
            return this.serviceRepository.findByName(name);
        } else {
            return this.serviceRepository.findAll();
        }
    }


    @RequestMapping(value = "/delete")
    public BaseResponse deleteService(@RequestParam(value = "name", defaultValue = "") String name){
        BaseResponse response = new BaseResponse();

        try {
            serviceRepository.delete(serviceRepository.findOneByName(name));
            response.setCode(200);
            response.setStatus("Service successfully deleted.");
        } catch (Exception e){
            response.setCode(e.hashCode());
            response.setStatus(e.getMessage());
        }

        return response;
    }
    
    @RequestMapping(value = "/update")
    public BaseResponse updateService(@RequestBody Service c){
        BaseResponse response = new BaseResponse();
        try {
        	String cpf = c.getName();
            Service oldC = serviceRepository.findOneByName(cpf);

            if(oldC.getName().equals(c.getName())) {
            	oldC = this.atualizarValores(c, oldC);
	            serviceRepository.save(oldC);
	            response.setCode(200);
	            response.setStatus("Service successfully updated.");
            }else {
                response.setStatus("Não encontrado registro com este Nome");
            }

        }catch (Exception e){
            response.setCode(e.hashCode());
            response.setStatus(e.getMessage());
        }

        return response;
    } 
    
    //Função responsável por atualizar os valores editados antes de persistir
    private Service atualizarValores(Service c, Service oldC) {
    	
    	oldC.setPrecoMin(c.getPrecoMin());
    	oldC.setObs(c.getObs());
    	
    	return oldC;
    }
    
}
