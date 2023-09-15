package org.example.service;

import org.example.dto.response.StatDTO;
import org.example.util.RestClient;

public class StatService {
    private RestClient _restClient;

    public StatService() {
        _restClient = new RestClient<StatDTO>();
    }

    public StatDTO getStats() {
        return (StatDTO) _restClient.get("api/stats", StatDTO.class);
    }

}
