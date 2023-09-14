package org.example.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserManagementResponseDTO {
    private int id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private boolean isAdmin;
}
