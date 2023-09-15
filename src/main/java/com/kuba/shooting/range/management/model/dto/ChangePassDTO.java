package com.kuba.shooting.range.management.model.dto;

import com.kuba.shooting.range.management.model.User;
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
