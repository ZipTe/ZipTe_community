package org.gdg.zipte_gdg.domain.role;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    OAUTH_FIRST_JOIN ("ROLE_FIRST_JOIN_OAUTH_USER"),
    USER ("ROLE_USER"),
    ADMIN ("ROLE_ADMIN"),
    EMPLOYER("ROLE_EMPLOYER"),
    BANNER ("ROLE_BANNER"),
    VIP ("VIP"),
    VVIP ("VVIP"),
    SVIP ("SVIP");

    private final String role;

    @JsonValue
    public String getRole() {
        return role;
    }
//    public static String getIncludingRoles(String role){
//        return Role.valueOf(role).getRole();
//    }
//    public static String addRole(Role role, String addRole){
//        String priorRoles = role.getRole();
//        priorRoles += ","+addRole;
//        return priorRoles;
//    }
//    public static String addRole(String roles, Role role){
//        return roles + "," + role.getRole();

    }
