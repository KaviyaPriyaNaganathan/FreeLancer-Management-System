package com.freelancing.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.freelancing.enums.FreelancerStatus;
import com.freelancing.models.Freelancer;

public class FreelancerServiceImplTest {

    private Freelancer freelancer1;
    private Freelancer freelancer2;
    private List<Freelancer> freelancers;

    @BeforeEach
    void setUp()
    {
        freelancer1 = new Freelancer();
        freelancer1.setFreelancerId(1L);
        freelancer1.setName("Kaviya");
        freelancer1.setEmail("kaviya@gmail.com");
        freelancer1.setStatus(FreelancerStatus.ACTIVE);

        
        
        freelancer2 = new Freelancer();
        freelancer2.setFreelancerId(2L);
        freelancer2.setName("Priya");
        freelancer2.setEmail("priya@gmail.com");
        freelancer2.setStatus(FreelancerStatus.ACTIVE);

        
        
        freelancers = new ArrayList<>();
        freelancers.add(freelancer1);
        freelancers.add(freelancer2);
    }

    @Test
    void testFreelancerNames() 
    {
        assertEquals("Kaviya", freelancers.get(0).getName());
        
        assertEquals("Priya", freelancers.get(1).getName());
        
        assertNotEquals("John", freelancers.get(0).getName());
    }

    @Test
    void testDeactivateFreelancer()
    {
        freelancer1.setStatus(FreelancerStatus.INACTIVE);
        
        assertEquals(FreelancerStatus.INACTIVE, freelancer1.getStatus());
        
        assertNotEquals(FreelancerStatus.ACTIVE, freelancer1.getStatus());
    }

    @Test
    void testFreelancerEmails() 
    {
        
    	assertEquals("kaviya@gmail.com", freelancer1.getEmail());
        
    	assertNotEquals("test@gmail.com", freelancer1.getEmail());
    }
}
