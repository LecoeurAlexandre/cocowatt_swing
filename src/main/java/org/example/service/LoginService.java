package org.example.service;

import org.example.dto.request.UserDTO;
import org.example.dto.response.UserAuthResponseDTO;
import org.example.dto.response.UserManagementResponseDTO;
import org.example.util.RestClient;

public class LoginService {
    private RestClient _restClient;

    public LoginService() {
        _restClient = new RestClient<UserAuthResponseDTO>();
    }
    public UserAuthResponseDTO login(UserDTO user) {
        return (UserAuthResponseDTO) _restClient.post("api/auth/login", user, UserAuthResponseDTO.class);
    }
}
