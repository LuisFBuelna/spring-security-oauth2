package com.buelna.springsecurity.entities.util;

import lombok.*;

import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public enum Role {

    ROLE_ADMINISTRATOR(Arrays.asList(
            RolePermisson.READ_ALL_PRODUCTS,
            RolePermisson.READ_ONE_PRODUCT,
            RolePermisson.CREATE_ONE_PRODUCT,
            RolePermisson.UPDATE_ONE_PRODUCT,
            RolePermisson.DISABLE_ONE_PRODUCT,

            RolePermisson.READ_ALL_CATEGORIES,
            RolePermisson.READ_ONE_CATEGORY,
            RolePermisson.CREATE_ONE_CATEGORY,
            RolePermisson.UPDATE_ONE_CATEGORY,
            RolePermisson.DISABLE_ONE_CATEGORY,

            RolePermisson.READ_MY_PROFILE
    )),
    ROLE_ASSISTANT_ADMINISTRATOR(Arrays.asList(
            RolePermisson.READ_ALL_PRODUCTS,
            RolePermisson.READ_ONE_PRODUCT,
            RolePermisson.UPDATE_ONE_PRODUCT,

            RolePermisson.READ_ALL_CATEGORIES,
            RolePermisson.READ_ONE_CATEGORY,
            RolePermisson.UPDATE_ONE_CATEGORY,

            RolePermisson.READ_MY_PROFILE
    )),
    ROLE_CUSTOMER(Arrays.asList(
            RolePermisson.READ_MY_PROFILE
    ));

    private List<RolePermisson> permissons;

    public List<RolePermisson> getPermissons() {
        return permissons;
    }

    public void setPermissons(List<RolePermisson> permissons) {
        this.permissons = permissons;
    }
}
