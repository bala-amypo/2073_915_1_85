@Service
public class FacilityScoreServiceImpl implements FacilityScoreService {

    private final FacilityScoreRepository scoreRepo;
    private final PropertyRepository propRepo;

    public FacilityScoreServiceImpl(FacilityScoreRepository scoreRepo,
                                    PropertyRepository propRepo) {
        this.scoreRepo = scoreRepo;
        this.propRepo = propRepo;
    }

    public FacilityScore addScore(Long propertyId, FacilityScore score) {
        Property property = propRepo.findById(propertyId)
                .orElseThrow(() -> new ResourceNotFoundException("Property not found"));

        if (scoreRepo.findByProperty(property).isPresent())
            throw new BadRequestException("Score already exists");

        score.setProperty(property);
        return scoreRepo.save(score);
    }

    public FacilityScore getScoreByProperty(Long propertyId) {
        Property property = propRepo.findById(propertyId)
                .orElseThrow(() -> new ResourceNotFoundException("Property not found"));

        return scoreRepo.findByProperty(property)
                .orElseThrow(() -> new ResourceNotFoundException("Score not found"));
    }
}
