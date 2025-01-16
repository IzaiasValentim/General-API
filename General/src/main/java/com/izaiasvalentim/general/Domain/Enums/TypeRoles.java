package com.IzaiasValentim.general_api.Domain.Enums;

public enum TypeRoles {
    ADMIN(1, 100, "ADMINISTRATOR"),
    MANAGER(2, 80, "MANAGER"),
    SELLER(3, 60, "SELLER"),
    INTERN(4, 40, "INTERN");

    private final int id;
    private final int level;
    private final String name;

    private TypeRoles(int id, int level, String name) {
        this.id = id;
        this.level = level;
        this.name = name;
    }

    public static int getLevelById(int id) {
        for (TypeRoles role : TypeRoles.values()) {
            if (role.id == id) {
                return role.getLevel();
            }
        }
        return 0;
    }

    public static String getNameById(int id) {
        for (TypeRoles role : TypeRoles.values()) {
            if (role.id == id) {
                return role.getName();
            }
        }
        return "";
    }

    public static TypeRoles getTypeById(int id) {
        for (TypeRoles role : TypeRoles.values()) {
            if (role.id == id) {
                return role;
            }
        }
        return null;
    }

    public int getId() {
        return id;
    }

    public int getLevel() {
        return level;
    }

    public String getName() {
        return name;
    }
}
