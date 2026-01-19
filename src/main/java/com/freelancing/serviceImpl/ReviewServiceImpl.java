package com.freelancing.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.freelancing.dto.request.ReviewRequestDTO;
import com.freelancing.dto.response.ReviewResponseDTO;
import com.freelancing.enums.ApplicationStatus;
import com.freelancing.enums.ReviewedBy;
import com.freelancing.exception.BadRequestException;
import com.freelancing.exception.ResourceNotFoundException;
import com.freelancing.mappers.ReviewMapper;
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
import com.freelancing.service.ReviewService;
@Service
public class ReviewServiceImpl implements ReviewService{

	private final ReviewRepository reviewRepository;
	private final FreelancerRepository freelancerRepository;
	private final JobPostRepository jobRepository;
	private final ProjectRepository projectRepository;
	private final ApplicationRepository applicationRepository;




	@Autowired
	public ReviewServiceImpl(ReviewRepository reviewRepository, 
			FreelancerRepository freelancerRepository, JobPostRepository jobRepository,
			ProjectRepository projectRepository,
			ApplicationRepository applicationRepository) {
		super();
		this.reviewRepository = reviewRepository;
		this.freelancerRepository = freelancerRepository;
		this.jobRepository = jobRepository;
		this.projectRepository = projectRepository;
		this.applicationRepository = applicationRepository;
		
	}

    private static final Logger log = LoggerFactory.getLogger(ReviewServiceImpl.class);

	@Override
	public ReviewResponseDTO addReview(ReviewRequestDTO dto) {
        log.info("Adding review for freelancerId: {} and jobId: {}", dto.getFreelancerId(), dto.getJobId());

	    Freelancer freelancer = freelancerRepository.findById(dto.getFreelancerId())
	        .orElseThrow(() -> new ResourceNotFoundException("Freelancer not found"));

	    JobPost job = jobRepository.findById(dto.getJobId())
	        .orElseThrow(() -> new ResourceNotFoundException("Job not found"));

	    Application application = applicationRepository.findByJob_JobIdAndFreelancer_FreelancerId(dto.getJobId(), dto.getFreelancerId())
	    	    .orElseThrow(() -> new BadRequestException( "Freelancer did not apply for this job"));


	    if (application.getStatus() != ApplicationStatus.ACCEPTED)
	    {            
	    	log.warn("Attempted to add review for unaccepted application: applicationId={}", application.getApplicationId());

	        throw new BadRequestException("Review allowed only for accepted applications");
	    }
	    
	    

	    Review review = ReviewMapper.toEntity(dto, freelancer, job);
	    review.setReviewedBy(ReviewedBy.MANAGER);
	    review.setReviewedDate(LocalDateTime.now());
	    review.setProject(job.getProject());

	    
	    
	    
	    Review savedReview = reviewRepository.save(review);

        log.info("Review added successfully: reviewId={}", savedReview.getReviewId());

	    return ReviewMapper.toResponse(savedReview);
	}


	@Override
	public List<ReviewResponseDTO> getReviewForFreelancer(Long freelancerId) {
		// TODO Auto-generated method stub
		
        log.info("Fetching reviews for freelancerId: {}", freelancerId);

		Freelancer freelancer = freelancerRepository.findById(freelancerId)
				.orElseThrow(()->new ResourceNotFoundException("Freelancer id not found. "+freelancerId));
		
	    List<Review> reviews = reviewRepository.findByFreelancer_FreelancerId(freelancerId);

	    if(reviews.isEmpty())
	    {
            log.warn("No reviews found for freelancerId: {}", freelancerId);
	        throw new BadRequestException("No reviews found for freelancer " + freelancerId);

	    }
        log.info("Found {} reviews for freelancerId: {}", reviews.size(), freelancerId);

	    return reviews.stream().map(ReviewMapper::toResponse).toList();
	}

	@Override
	public List<ReviewResponseDTO> getReviewForProject(Long projectId) {
		// TODO Auto-generated method stub
        log.info("Fetching reviews for projectId: {}", projectId);

		
		Project project = projectRepository.findById(projectId)
				.orElseThrow(()->new ResourceNotFoundException("Project id not found. "+projectId));
		
		List<Review > review = reviewRepository.findByProject_ProjectId(projectId);
		
		
		if(review.isEmpty())
		{            
			log.warn("No reviews found for projectId: {}", projectId);

	        throw new BadRequestException("No reviews found for project " + projectId);
		}
		
        log.info("Found {} reviews for projectId: {}", review.size(), projectId);

		return review.stream().map(ReviewMapper::toResponse).toList();
	}
	
	
	
}
