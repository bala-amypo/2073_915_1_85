@Service
public class RatingService {
    private final RatingResultRepository ratingRepo;
    private final FacilityScoreRepository scoreRepo;
    private final PropertyRepository propRepo;

    // Use a manual constructor to satisfy Step 0 constraints
    public RatingService(RatingResultRepository ratingRepo, 
                         FacilityScoreRepository scoreRepo, 
                         PropertyRepository propRepo) {
        this.ratingRepo = ratingRepo;
        this.scoreRepo = scoreRepo;
        this.propRepo = propRepo;
    }
}