package com.freelancing.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.freelancing.enums.ManagerStatus;
import com.freelancing.models.Manager;

public class ManagerServiceImplTest {

    private Manager manager1;
    private Manager manager2;
   
    private List<Manager> managers;

    @BeforeEach
    void setUp() 
    {
        manager1 = new Manager();
        manager1.setManagerId(1L);
        manager1.setName("Ramesh");
        manager1.setEmail("ramesh@gmail.com");
        manager1.setDepartment("IT");
        manager1.setStatus(ManagerStatus.ACTIVE);

        
        
        manager2 = new Manager();
        manager2.setManagerId(2L);
        manager2.setName("Suresh");
        manager2.setEmail("suresh@gmail.com");
        manager2.setDepartment("HR");
        manager2.setStatus(ManagerStatus.ACTIVE);

        
        
        managers = new ArrayList<>();
        managers.add(manager1);
        managers.add(manager2);
    }

    @Test
    void testAddManager() 
    {
        Manager newManager = new Manager();
        
        newManager.setManagerId(3L);
        newManager.setName("Kumar");
        newManager.setEmail("kumar@gmail.com");
        newManager.setDepartment("Finance");
        newManager.setStatus(ManagerStatus.ACTIVE);

        
        managers.add(newManager);

        
        assertEquals(3, managers.size());
        assertEquals("Kumar", managers.get(2).getName());
        assertNotEquals("Ramesh", managers.get(2).getName());
    }

    @Test
    void testDeactivateManager()
    {
        manager1.setStatus(ManagerStatus.INACTIVE);

        assertEquals(ManagerStatus.INACTIVE, manager1.getStatus());
        assertNotEquals(ManagerStatus.ACTIVE, manager1.getStatus());
    }

    @Test
    void testGetAllManagers() 
    {
        assertEquals(2, managers.size());
        assertEquals("Ramesh", managers.get(0).getName());
        assertEquals("Suresh", managers.get(1).getName());
    }
}
