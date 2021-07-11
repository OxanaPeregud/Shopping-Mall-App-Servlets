package com.peregud.shoppingcenter.service;

import com.peregud.shoppingcenter.model.Shop;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ServletShopServiceTest {
    ServletShopService servletShopService = new ServletShopService();
    Shop shop1 = Shop.builder()
            .id(1)
            .name("Shop1")
            .build();
    Shop shop2 = Shop.builder()
            .id(2)
            .name("Shop2")
            .build();
    Shop shop3 = Shop.builder()
            .id(3)
            .name("Shop3")
            .build();

    @Test
    void save_success() {
        assertEquals(shop1, servletShopService.save(shop1));
    }

    @Test
    void save_fail() {
        assertNotEquals(shop2, servletShopService.save(shop1));
    }

    @Test
    void getById_success() {
        servletShopService.save(shop1);
        assertEquals(shop3, servletShopService.getById(Shop.class, shop3.getId()));
    }

    @Test
    void getById_fail() {
        servletShopService.save(shop1);
        servletShopService.save(shop2);
        assertNotEquals(shop2, servletShopService.getById(Shop.class, shop1.getId()));
    }

    @SuppressWarnings("unchecked")
    @Test
    void getList_success() {
        ServletShopService servletShopService = mock(ServletShopService.class);
        List<?> list = List.of(shop1, shop2);
        when(servletShopService.getList(any(Class.class))).thenReturn(list);
        assertEquals(list, servletShopService.getList(Shop.class));
    }

    @SuppressWarnings("unchecked")
    @Test
    void getList_fail() {
        ServletShopService servletShopService = mock(ServletShopService.class);
        List<?> list = List.of(shop1, shop2);
        when(servletShopService.getList(any(Class.class))).thenReturn(list);
        assertNotEquals(List.of(shop1), servletShopService.getList(Shop.class));
    }

    @Test
    void delete_success() {
        servletShopService.save(shop1);
        servletShopService.delete(Shop.class, shop1.getId());
        assertNull(servletShopService.getById(Shop.class, shop1.getId()));
    }

    @Test
    void delete_fail() {
        servletShopService.save(shop1);
        servletShopService.save(shop2);
        servletShopService.delete(Shop.class, shop1.getId());
        assertNotNull(servletShopService.getById(Shop.class, shop2.getId()));
    }

    @SuppressWarnings("unchecked")
    @Test
    void selectIdForSet_success() {
        ServletShopService servletShopService = mock(ServletShopService.class);
        when(servletShopService.selectIdForSet(shop1, shop1.getId())).thenReturn(any(List.class));
        assertEquals(any(List.class), servletShopService.selectIdForSet(shop1, shop1.getId()));
    }

    @SuppressWarnings("unchecked")
    @Test
    void selectIdForSet_fail() {
        ServletShopService servletShopService = mock(ServletShopService.class);
        when(servletShopService.selectIdForSet(shop1, shop1.getId())).thenReturn(any(List.class));
        assertNotEquals(any(List.class), servletShopService.selectIdForSet(shop1, shop2.getId()));
    }
}
