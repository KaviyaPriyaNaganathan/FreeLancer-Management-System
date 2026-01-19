package com.freelancing.serviceImpl;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.freelancing.dto.request.ReviewRequestDTO;
import com.freelancing.dto.response.ReviewResponseDTO;
import com.freelancing.enums.ApplicationStatus;
import com.freelancing.enums.ReviewedBy;
import com.freelancing.models.Application;
import com.freelancing.models.Freelancer;
import com.freelancing.models.JobPost;
import com.freelancing.models.Project;
import com.freelancing.models.Review;
import com.freelancing.repository.ApplicationRepository;
import com.freelancing.repository.FreelancerRepository;
import com.freelancing.repository.JobPostRepository;
import com.freelancing.repository.ProjectRepository;
import com.freelancing.repository.ReviewRepository;

public class ReviewServiceImplTest {

    @Mock
    private ReviewRepository reviewRepository;

    @Mock
    private FreelancerRepository freelancerRepository;

    @Mock
    private JobPostRepository jobRepository;

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private ApplicationRepository applicationRepository;

    @InjectMocks
    private ReviewServiceImpl reviewService;

    private Freelancer defaultFreelancer;
    private JobPost defaultJob;
    private Project defaultProject;
    private Application defaultApplication;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Project
        defaultProject = new Project();
        defaultProject.setProjectId(1L);
        defaultProject.setTitle("Website Project");

        // Freelancer
        defaultFreelancer = new Freelancer();
        defaultFreelancer.setFreelancerId(1L);
        defaultFreelancer.setName("John Doe");

        // Job
        defaultJob = new JobPost();
        defaultJob.setJobId(1L);
        defaultJob.setTitle("Frontend Developer");
        defaultJob.setProject(defaultProject); // important: job must reference project

        // Application
        defaultApplication = new Application();
        defaultApplication.setJob(defaultJob);
        defaultApplication.setFreelancer(defaultFreelancer);
        defaultApplication.setStatus(ApplicationStatus.ACCEPTED);
    }


    @Test
    void testAddReview_Success() {
        ReviewRequestDTO dto = new ReviewRequestDTO();
        dto.setFreelancerId(1L);
        dto.setJobId(1L);
        dto.setRating(5);
        dto.setComment("Excellent work");

        when(freelancerRepository.findById(1L)).thenReturn(Optional.of(defaultFreelancer));
        when(jobRepository.findById(1L)).thenReturn(Optional.of(defaultJob));
        when(applicationRepository.findByJob_JobIdAndFreelancer_FreelancerId(1L, 1L))
                .thenReturn(Optional.of(defaultApplication));
        when(reviewRepository.save(any(Review.class))).thenAnswer(invocation -> invocation.getArgument(0));

        ReviewResponseDTO response = reviewService.addReview(dto);

        assertNotNull(response);
        assertEquals(5, response.getRating());
        assertEquals("Excellent work", response.getComment());

        verify(freelancerRepository, times(1)).findById(1L);
        verify(jobRepository, times(1)).findById(1L);
        verify(applicationRepository, times(1)).findByJob_JobIdAndFreelancer_FreelancerId(1L, 1L);
        verify(reviewRepository, times(1)).save(any(Review.class));
    }

    @Test
    void testAddReview_FreelancerNotFound() {
        ReviewRequestDTO dto = new ReviewRequestDTO();
        dto.setFreelancerId(1L);

        when(freelancerRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> reviewService.addReview(dto));
        assertTrue(ex.getMessage().contains("Freelancer not found"));
    }

    @Test
    void testAddReview_JobNotFound() {
        ReviewRequestDTO dto = new ReviewRequestDTO();
        dto.setFreelancerId(1L);
        dto.setJobId(1L);

        when(freelancerRepository.findById(1L)).thenReturn(Optional.of(defaultFreelancer));
        when(jobRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> reviewService.addReview(dto));
        assertTrue(ex.getMessage().contains("Job not found"));
    }

    @Test
    void testAddReview_ApplicationNotAccepted() {
        ReviewRequestDTO dto = new ReviewRequestDTO();
        dto.setFreelancerId(1L);
        dto.setJobId(1L);

        defaultApplication.setStatus(ApplicationStatus.APPLIED);

        when(freelancerRepository.findById(1L)).thenReturn(Optional.of(defaultFreelancer));
        when(jobRepository.findById(1L)).thenReturn(Optional.of(defaultJob));
        when(applicationRepository.findByJob_JobIdAndFreelancer_FreelancerId(1L, 1L))
                .thenReturn(Optional.of(defaultApplication));

        RuntimeException ex = assertThrows(RuntimeException.class, () -> reviewService.addReview(dto));
        assertTrue(ex.getMessage().contains("Review allowed only for accepted applications"));
    }

    @Test
    void testGetReviewForFreelancer_Success() {
        Review review = new Review();
        review.setReviewId(1L);
        review.setFreelancer(defaultFreelancer);
        review.setProject(defaultProject);
        review.setJob(defaultJob);
        review.setRating(5);
        review.setComments("Great job");
        review.setReviewedBy(ReviewedBy.MANAGER);
        review.setReviewedDate(LocalDateTime.now());

        when(freelancerRepository.findById(1L)).thenReturn(Optional.of(defaultFreelancer));
        when(reviewRepository.findByFreelancer_FreelancerId(1L))
            .thenReturn(Collections.singletonList(review));

        List<ReviewResponseDTO> reviews = reviewService.getReviewForFreelancer(1L);

        assertEquals(1, reviews.size());
        assertEquals(5, reviews.get(0).getRating());
        assertEquals("Great job", reviews.get(0).getComment());
        assertEquals(defaultFreelancer.getFreelancerId(), reviews.get(0).getFreelancerId());
        assertEquals(defaultProject.getProjectId(), reviews.get(0).getProjectId());
    }

    
    @Test
    void testGetReviewForFreelancer_NoReviews() {
        when(freelancerRepository.findById(1L)).thenReturn(Optional.of(defaultFreelancer));
        when(reviewRepository.findByFreelancer_FreelancerId(1L)).thenReturn(Collections.emptyList());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> reviewService.getReviewForFreelancer(1L));
        assertTrue(ex.getMessage().contains("No reviews found"));
    }

    @Test
    void testGetReviewForProject_Success() {
        Review review = new Review();
        review.setReviewId(1L);
        review.setFreelancer(defaultFreelancer);
        review.setProject(defaultProject);
        review.setJob(defaultJob);
        review.setRating(4);
        review.setComments("Good work");
        review.setReviewedBy(ReviewedBy.MANAGER);
        review.setReviewedDate(LocalDateTime.now());

        when(projectRepository.findById(1L)).thenReturn(Optional.of(defaultProject));
        when(reviewRepository.findByProject_ProjectId(1L))
            .thenReturn(Collections.singletonList(review));

        List<ReviewResponseDTO> reviews = reviewService.getReviewForProject(1L);

        assertEquals(1, reviews.size());
        assertEquals(4, reviews.get(0).getRating());
        assertEquals("Good work", reviews.get(0).getComment());
        assertEquals(defaultFreelancer.getFreelancerId(), reviews.get(0).getFreelancerId());
        assertEquals(defaultProject.getProjectId(), reviews.get(0).getProjectId());
    }


    @Test
    void testGetReviewForProject_NoReviews() {
        when(projectRepository.findById(1L)).thenReturn(Optional.of(defaultProject));
        when(reviewRepository.findByProject_ProjectId(1L)).thenReturn(Collections.emptyList());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> reviewService.getReviewForProject(1L));
        assertTrue(ex.getMessage().contains("No reviews found"));
    }
}
