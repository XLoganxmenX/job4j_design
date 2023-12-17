package ru.job4j.generics;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class RoleStoreTest {

    @Test
    public void whenAddNewRoleAndFindById() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("1", "Client"));
        Role result = roleStore.findById("1");
        assertThat(result.getRoleName()).isEqualTo("Client");
    }

    @Test
    public void whenAddAndFindIsNull() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("1", "Client"));
        Role result = roleStore.findById("50");
        assertThat(result).isNull();
    }

    @Test
    public void whenAddEqualsId() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("1", "Client"));
        roleStore.add(new Role("1", "Agent"));
        Role result = roleStore.findById("1");
        assertThat(result.getRoleName()).isEqualTo("Client");
    }

    @Test
    public void whenReplaceAndFind() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("1", "Client"));
        roleStore.replace("1", new Role("1", "Agent"));
        Role result = roleStore.findById("1");
        assertThat(result.getRoleName()).isEqualTo("Agent");
    }

    @Test
    public void whenReplaceAbsentRoleAndFind() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("1", "Client"));
        roleStore.replace("50", new Role("50", "Agent"));
        Role result = roleStore.findById("1");
        assertThat(result.getRoleName()).isEqualTo("Client");
    }

    @Test
    public void whenReplaceInEmptyStoreAndFind() {
        RoleStore roleStore = new RoleStore();
        roleStore.replace("1", new Role("1", "Agent"));
        Role result = roleStore.findById("1");
        assertThat(result).isNull();
    }

    @Test
    public void whenDeleteAndFind() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("1", "Client"));
        roleStore.delete("1");
        Role result = roleStore.findById("1");
        assertThat(result).isNull();
    }

    @Test
    public void whenDeleteAbsentRoleAndFind() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("1", "Client"));
        roleStore.delete("50");
        Role result = roleStore.findById("1");
        assertThat(result.getRoleName()).isEqualTo("Client");
    }

    @Test
    public void whenDeleteInEmptyStoreAndFind() {
        RoleStore roleStore = new RoleStore();
        roleStore.delete("1");
        Role result = roleStore.findById("1");
        assertThat(result).isNull();
    }
}