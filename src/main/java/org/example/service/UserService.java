package org.example.service;

import org.example.dto.request.UserDTO;
import org.example.dto.response.UserAuthResponseDTO;
import org.example.dto.response.UserManagementResponseDTO;
import org.example.util.RestClient;

import java.util.List;

public class UserService {
    private RestClient _restClient;

    public UserService() {
        _restClient = new RestClient<UserManagementResponseDTO, UserDTO>();
    }
    public List<UserManagementResponseDTO> getByLastName(String lastName) {
        return (List<UserManagementResponseDTO>) _restClient.get("api/user/"+lastName, UserManagementResponseDTO.class);
    }
    public UserManagementResponseDTO getById(int id) {
        return (UserManagementResponseDTO) _restClient.get("api/user/"+id, UserManagementResponseDTO.class);
    }

}
