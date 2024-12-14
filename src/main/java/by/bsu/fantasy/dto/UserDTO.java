package by.bsu.fantasy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

// TODO: проверка на нулы во всех конверторах
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;

    private String username;
    private String name;
    private String role;

    private List<Long> pick_ids;
}
