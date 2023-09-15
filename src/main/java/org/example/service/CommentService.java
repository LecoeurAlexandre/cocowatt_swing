package org.example.service;

import org.example.dto.response.CommentDTO;
import org.example.util.RestClient;

import java.util.List;

public class CommentService {
    private RestClient _restClient;

    public CommentService() {
        _restClient = new RestClient<CommentDTO>();
    }

    public List<CommentDTO> getAllComments() {
        return (List<CommentDTO>) _restClient.get("api/rating", List.class);
    }
    public CommentDTO getCommentById(int id) {
        return (CommentDTO) _restClient.get("api/rating/"+id, CommentDTO.class);
    }
    public void delete(int id) {
        _restClient.delete("api/rating/"+id, String.class);
    }
}
