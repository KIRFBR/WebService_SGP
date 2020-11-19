package controller;

import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import repository.UserRepository;
import responses.BaseResponse;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    private UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "/save")
    public BaseResponse addUser(@RequestBody User u) {
        BaseResponse response = new BaseResponse();
        try{
            System.out.println(u);
            this.userRepository.save(u);

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
    public List<User> getUserName(@RequestParam(value = "name", defaultValue = "") String name) {
        if(name.length() > 0){
            return this.userRepository.findByName(name);
        } else {
            return this.userRepository.findAll();
        }
    }


    @RequestMapping(value = "/delete")
    public BaseResponse deleteUser(@RequestParam(value = "name", defaultValue = "") String name){
        BaseResponse response = new BaseResponse();

        try {
            if (name.length() > 0) {
                userRepository.delete(userRepository.findByName(name));
            }

            response.setCode(200);
            response.setStatus("User successfully updated.");
        } catch (Exception e){
            response.setCode(e.hashCode());
            response.setStatus(e.getMessage());
        }

        return response;
    }
}
