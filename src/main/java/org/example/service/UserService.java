package org.example.service;

import org.example.dto.request.UserDTO;
import org.example.dto.response.UserAuthResponseDTO;
import org.example.dto.response.UserManagementResponseDTO;
import org.example.dto.response.UsersListDTO;
import org.example.util.RestClient;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class UserService {
    private RestClient _restClient;

    public UserService() {
        _restClient = new RestClient<UserManagementResponseDTO>();
    }
    public UsersListDTO getByLastName(String lastName) {
        return (UsersListDTO) _restClient.get("api/user/lastname/"+lastName, UsersListDTO.class);
    }
    public UserManagementResponseDTO getById(int id) {
        return (UserManagementResponseDTO) _restClient.get("api/user/adm/"+id, UserManagementResponseDTO.class);
    }
    public void delete(int id) {
        _restClient.delete("api/user/"+id, String.class);
    }
}
