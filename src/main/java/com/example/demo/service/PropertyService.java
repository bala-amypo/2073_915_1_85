@Service
public class PropertyServiceImpl implements PropertyService {

    private final PropertyRepository repo;

    public PropertyServiceImpl(PropertyRepository repo) {
        this.repo = repo;
    }

    public Property addProperty(Property property) {
        if (property.getPrice() < 0 || property.getAreaSqFt() < 100)
            throw new BadRequestException("Invalid property data");

        return repo.save(property);
    }

    public List<Property> getAllProperties() {
        return repo.findAll();
    }
}
