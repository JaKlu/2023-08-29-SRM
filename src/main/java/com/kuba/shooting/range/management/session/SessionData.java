package com.kuba.shooting.range.management.session;

import com.kuba.shooting.range.management.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@NoArgsConstructor
@Getter
@Setter
@Component
@SessionScope
public class SessionData {
    private User user = null;
    private String formInfo = null;
    private String formError = null;

    public boolean isLogged() {
        return this.user != null;
    }

    public boolean isAdmin() {
        if (this.user == null) {
            return false;
        }
        return this.user.getRole() == User.Role.ADMIN;
    }

    public boolean isEmployee() {
        if (this.user == null) {
            return false;
        }
        return this.user.getRole() == User.Role.EMPLOYEE;
    }

    public boolean isAdminOrEmployee() {
        if (this.user == null) {
            return false;
        }
        return this.user.getRole() == User.Role.ADMIN
                || (this.user.getRole() == User.Role.EMPLOYEE);
    }

    public String getFormInfo() {
        if (this.formInfo == null) {
            return "";
        } else {
            String temp = this.formInfo;
            this.formInfo = null;
            return temp;
        }
    }

    public String getFormError() {
        if (this.formError == null) {
            return "";
        } else {
            String temp = this.formError;
            this.formError = null;
            return temp;
        }
    }
}
