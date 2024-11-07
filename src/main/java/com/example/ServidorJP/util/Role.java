package com.example.ServidorJP.util;

import java.util.Arrays;
import java.util.List;

public enum Role {

    USER(Arrays.asList(Permission.SEARCH_BY_RUC, Permission.LIST_PROVEEDOR)),

    ADMIN(Arrays.asList(Permission.SAVE_PROVEEDOR, Permission.SEARCH_BY_RUC,
            Permission.LIST_PROVEEDOR, Permission.DELETE_BY_RUC, Permission.FILTER_BY_DATE));

    private List<Permission> permissions;

    Role(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }
}
