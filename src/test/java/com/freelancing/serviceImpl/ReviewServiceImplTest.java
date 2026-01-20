package com.freelancing.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.freelancing.enums.ApplicationStatus;
import com.freelancing.enums.ReviewedBy;
import com.freelancing.models.Application;
import com.freelancing.models.Freelancer;
import com.freelancing.models.JobPost;
import com.freelancing.models.Project;
import com.freelancing.models.Review;

public class ReviewServiceImplTest {

    private Freelancer freelancer;
    private Project project;
    
    
    private JobPost job;
    
    private Application application;
    private List<Review> reviews;

    @BeforeEach
    void setUp() 
    {
        freelancer = new Freelancer();
        freelancer.setFreelancerId(1L);
        freelancer.setName("John Doe");

        
        project = new Project();
        project.setProjectId(1L);
        project.setTitle("Website Project");

        
        job = new JobPost();
        job.setJobId(1L);
        job.setTitle("Frontend Developer");
        job.setProject(project);

        
        application = new Application();
        application.setJob(job);
        application.setFreelancer(freelancer);
        application.setStatus(ApplicationStatus.ACCEPTED);

        reviews = new ArrayList<>();
    }

    @Test
    void testAddReview() 
    {
        Review review = new Review();
        
        review.setFreelancer(freelancer);
        review.setProject(project);
        review.setJob(job);
        review.setRating(5);
        review.setComments("Excellent work");
        review.setReviewedBy(ReviewedBy.MANAGER);
        review.setReviewedDate(LocalDateTime.now());

        reviews.add(review);

        assertEquals(1, reviews.size());
        assertEquals(5, reviews.get(0).getRating());
        assertEquals("Excellent work", reviews.get(0).getComments());
        assertEquals(freelancer.getFreelancerId(), reviews.get(0).getFreelancer().getFreelancerId());
        assertNotEquals(4, reviews.get(0).getRating());
    }

    @Test
    void testGetReviewForFreelancer() 
    {
        Review review = new Review();
        review.setFreelancer(freelancer);
        review.setProject(project);
        review.setJob(job);
        review.setRating(4);
        review.setComments("Good work");

        reviews.add(review);

        List<Review> freelancerReviews = new ArrayList<>();
        for (Review r : reviews)
        {
            if (r.getFreelancer().getFreelancerId() == freelancer.getFreelancerId()) 
            {
                freelancerReviews.add(r);
            }
        }

        assertEquals(1, freelancerReviews.size());
        assertEquals("Good work", freelancerReviews.get(0).getComments());
        assertEquals(4, freelancerReviews.get(0).getRating());
    }

    @Test
    void testGetReviewForProject()
    {
        Review review = new Review();
        
        review.setFreelancer(freelancer);
        review.setProject(project);
        review.setJob(job);
        review.setRating(3);
        review.setComments("Satisfactory");

        reviews.add(review);

        List<Review> projectReviews = new ArrayList<>();
        for (Review r : reviews)
        {
            if (r.getProject().getProjectId() == project.getProjectId())
            {
                projectReviews.add(r);
            }
        }

        assertEquals(1, projectReviews.size());
        assertEquals("Satisfactory", projectReviews.get(0).getComments());
        assertEquals(3, projectReviews.get(0).getRating());
        assertNotEquals(5, projectReviews.get(0).getRating());
    }
}
