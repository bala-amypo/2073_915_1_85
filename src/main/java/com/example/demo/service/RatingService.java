@Service
public class RatingServiceImpl implements RatingService {

    private final RatingResultRepository ratingRepo;
    private final FacilityScoreRepository scoreRepo;
    private final PropertyRepository propRepo;

    public RatingServiceImpl(RatingResultRepository ratingRepo,
                             FacilityScoreRepository scoreRepo,
                             PropertyRepository propRepo) {
        this.ratingRepo = ratingRepo;
        this.scoreRepo = scoreRepo;
        this.propRepo = propRepo;
    }

    public RatingResult generateRating(Long propertyId) {
        Property property = propRepo.findById(propertyId)
                .orElseThrow(() -> new ResourceNotFoundException("Property not found"));

        FacilityScore score = scoreRepo.findByProperty(property)
                .orElseThrow(() -> new BadRequestException("Facility score missing"));

        double avg = (score.getSchoolProximity() +
                      score.getHospitalProximity() +
                      score.getTransportAccess() +
                      score.getSafetyScore()) / 4.0;

        String category =
                avg >= 8 ? "EXCELLENT" :
                avg >= 6 ? "GOOD" :
                avg >= 4 ? "AVERAGE" : "POOR";

        return ratingRepo.save(new RatingResult(property, avg, category));
    }

    public RatingResult getRating(Long propertyId) {
        Property property = propRepo.findById(propertyId)
                .orElseThrow(() -> new ResourceNotFoundException("Property not found"));

        return ratingRepo.findByProperty(property)
                .orElseThrow(() -> new ResourceNotFoundException("Rating not found"));
    }
}
