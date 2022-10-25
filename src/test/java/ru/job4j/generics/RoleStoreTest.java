package ru.job4j.generics;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RoleStoreTest {

    @Test
    void whenAddAndRoleAndFindById() {
        RoleStore store = new RoleStore();
        store.add(new Role("1"));
        Role result = store.findById("1");
        assertThat(result.equals("1"));
    }

    @Test
    void whenAddAndFindThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1"));
        Role result = store.findById("2");
        assertThat(result).isNull();
    }

    @Test
    void whenAddDuplicateAndFindRoleId() {
        RoleStore store = new RoleStore();
        store.add(new Role("5"));
        store.add(new Role("5"));
        Role result = store.findById("5");
        assertThat(result.equals("5"));
    }

    @Test
    public void whenReplaceRoleThanOk() {
        RoleStore store = new RoleStore();
        Role[] roles = new Role[]{
                new Role("1"),
                new Role("2"),
                new Role("3"),
        };
        store.add(roles[0]);
        store.add(roles[1]);
        boolean result = store.replace("2", roles[2]);
        assertThat(result).isTrue();
    }

    @Test
    public void whenReplaceRoleThanNotOk() {
        RoleStore store = new RoleStore();
        Role[] roles = new Role[]{
                new Role("1"),
                new Role("2"),
                new Role("3"),
        };
        store.add(roles[0]);
        store.add(roles[1]);
        boolean result = store.replace("3", roles[2]);
        assertThat(result).isFalse();
    }

    @Test
    void whenDeleteOkThenTrue() {
        RoleStore store = new RoleStore();
        store.add(new Role("6"));
        boolean result = store.delete("6");
        assertThat(result).isTrue();
    }

    @Test
    void whenDeleteOkThenFalse() {
        RoleStore store = new RoleStore();
        store.add(new Role("666"));
        boolean result = store.delete("66");
        assertThat(result).isFalse();
    }
}