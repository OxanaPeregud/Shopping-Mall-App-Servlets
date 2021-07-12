package com.peregud.shoppingcenter.servlet;

import org.junit.jupiter.api.Test;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ListDiscountsServletTest extends MockInit {

    @Test
    void doGet() throws ServletException, IOException {
        when(request.getRequestDispatcher(any(String.class))).thenReturn(requestDispatcher);
        assertEquals(requestDispatcher, request.getRequestDispatcher("view/discounts-list.jsp"));

        new ListDiscountsServlet().doGet(request, response);
        verify(requestDispatcher).forward(request, response);

        List<?> listDiscounts = new ArrayList<>();
        verify(request).setAttribute("listDiscounts", listDiscounts);
    }

    @Test
    void doPost() throws ServletException, IOException {
        when(request.getRequestDispatcher(any(String.class))).thenReturn(requestDispatcher);
        assertEquals(requestDispatcher, request.getRequestDispatcher("view/discounts-list.jsp"));

        when(request.getParameterValues("deleteDiscount")).thenReturn(new String[]{});

        new ListDiscountsServlet().doPost(request, response);
        verify(requestDispatcher).forward(request, response);

        List<?> listDiscounts = new ArrayList<>();
        verify(request).setAttribute("listDiscounts", listDiscounts);
        verify(request, atLeast(1)).getParameterValues("deleteDiscount");
    }
}
