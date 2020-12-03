package controller;

import entity.MyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import repository.MyServiceRepository;
import responses.BaseResponse;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;

@RestController
@RequestMapping(value = "/myService")
public class MyServiceController {

    private MyServiceRepository myServiceRepository;

    @Autowired
    public MyServiceController(MyServiceRepository myServiceRepository) {
        this.myServiceRepository = myServiceRepository;
    }

    @RequestMapping(value = "/save")
    public BaseResponse addMyService(@RequestBody MyService u) {
        BaseResponse response = new BaseResponse();
        try{
            System.out.println(u);
            this.myServiceRepository.save(u);

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
    public List<MyService> getMyService(@RequestParam(value = "name", defaultValue = "") String name) {
        if(name.length() > 0){
            return this.myServiceRepository.findByClient(name);
        } else {
            return this.myServiceRepository.findAll();
        }
    }
    
    @RequestMapping(value = "/vencidos")
    public Iterable<MyService> getVencidos() {
    	BaseResponse response = new BaseResponse();
    	//Date dataAtual = new Date(System.currentTimeMillis());
    	try {
    		response.setCode(200);
    		Iterable<MyService> myServices = myServiceRepository.findAllDtVenc();
    		return myServices;
    	} catch(Exception e) {
            response.setCode(e.hashCode());
            response.setStatus(e.getMessage());
            return null;
    	}
		
    }    


    @RequestMapping(value = "/delete")
    public BaseResponse deleteMyService(@RequestParam Long id){
        BaseResponse response = new BaseResponse();

        try {
        	myServiceRepository.delete(myServiceRepository.findOneById(id));
            response.setCode(200);
            response.setStatus("Client successfully deleted.");
        } catch (Exception e){
            response.setCode(e.hashCode());
            response.setStatus(e.getMessage());
        }

        return response;
    }
    
    
    @RequestMapping(value = "/update")
    public BaseResponse updateMyService(@RequestBody MyService c){
        BaseResponse response = new BaseResponse();
        try {
        	Long id= c.getId();
        	
        	MyService oldC = myServiceRepository.findOneById(id);
        	
            if(oldC.getId() == c.getId()) {
            	oldC = this.atualizarValores(c, oldC);
            	myServiceRepository.save(oldC);
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
    private MyService atualizarValores(MyService c, MyService oldC) {
    	
        oldC.setDtPgm(c.getDtPgm());
        oldC.setDtVenc(c.getDtVenc());
        oldC.setObs(c.getObs());
        oldC.setQtdeParcela(c.getQtdeParcela());
        oldC.setVlrTotal(c.getVlrTotal());
        
    	return oldC;
    }
    
}
