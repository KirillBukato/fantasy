package by.bsu.fantasy.dto;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class UserDTO {
    private Long id;

    private String username;
    private String name;
    private String role;

    private List<Long> pick_ids;
}
