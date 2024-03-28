package ovh.jakubk.shooting.range.management.model.dto;

import ovh.jakubk.shooting.range.management.model.User;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class ChangePassDTO {
    private User user;
    private String oldPass;
    private String newPass;
    private String newPassRepeat;

    public ChangePassDTO(User user) {
        this.user = user;
    }
}
