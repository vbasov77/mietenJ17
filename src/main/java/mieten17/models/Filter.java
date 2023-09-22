package mieten17.models;


public class Filter {
    public static final String PERCENT = "%";


    private String localityName;


    private Long localityId;
    private Integer capacity;
    private String countRooms;
    private Integer priceFrom;
    private Integer priceTo;
    private Integer areaFrom;
    private Integer areaTo;
    private String balcony;
    private Integer notFirst;
    private Integer notEnd;
    private String children;
    private String animals;
    private String smoking;
    private String party;
    private String documents;
    private String monthly;


    public String getLocalityName() {
        return localityName;
    }


    public Long getLocalityId() {
        return localityId;
    }

    public Integer getCapacity() {
        if (capacity == null || capacity == 0) {
            capacity = null;
        }
        return capacity;
    }

    public String getCountRooms() {
        return countRooms;
    }

    public Integer getPriceFrom() {
        if (priceFrom == null || priceFrom == 0) {
            priceFrom = null;
        }
        return priceFrom;
    }

    public Integer getPriceTo() {
        if (priceTo == null || priceTo == Integer.MAX_VALUE) {
            priceTo = null;
        }
        return priceTo;
    }

    public Integer getNotEnd() {
        if (notEnd == null || notEnd == 0) {
            notEnd = null;
        }
        return notEnd;
    }

    public void setNotEnd(Integer notEnd) {
        this.notEnd = notEnd;
    }

    public Integer getAreaFrom() {
        if (areaFrom == null || areaFrom == 0) {
            areaFrom = null;
        }
        return areaFrom;
    }

    public Integer getAreaTo() {
        if (areaTo == null || areaTo == Integer.MAX_VALUE) {
            areaTo = null;
        }
        return areaTo;
    }

    public String getBalcony() {
        return balcony;
    }

    public Integer getNotFirst() {
        return notFirst;
    }

    public String getChildren() {

        return children;
    }

    public String getAnimals() {

        return animals;
    }

    public String getSmoking() {

        return smoking;
    }

    public String getParty() {

        return party;
    }

    public String getDocuments() {
        return documents;
    }

    public String getMonthly() {

        return monthly;
    }

    public void setLocalityName(String localityName) {
        this.localityName = localityName;
    }

    public void setLocalityId(Long localityId) {
        this.localityId = localityId;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public void setCountRooms(String countRooms) {
        this.countRooms = countRooms;
    }

    public void setPriceFrom(Integer priceFrom) {
        this.priceFrom = priceFrom;
    }

    public void setPriceTo(Integer priceTo) {
        this.priceTo = priceTo;
    }

    public void setAreaFrom(Integer areaFrom) {
        this.areaFrom = areaFrom;
    }

    public void setAreaTo(Integer areaTo) {
        this.areaTo = areaTo;
    }

    public void setBalcony(String balcony) {
        this.balcony = balcony;
    }

    public void setNotFirst(Integer notFirst) {
        this.notFirst = notFirst;
    }

    public void setChildren(String children) {
        this.children = children;
    }

    public void setAnimals(String animals) {
        this.animals = animals;
    }

    public void setSmoking(String smoking) {
        this.smoking = smoking;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public void setDocuments(String documents) {
        this.documents = documents;
    }

    public void setMonthly(String monthly) {
        this.monthly = monthly;
    }


    @Override
    public String toString() {
        return "Filter{" +
                "localityName='" + localityName + '\'' +
                ", localityId='" + localityId + '\'' +
                ", capacity=" + capacity +
                ", countRooms='" + countRooms + '\'' +
                ", priceFrom=" + priceFrom +
                ", priceTo=" + priceTo +
                ", areaFrom=" + areaFrom +
                ", areaTo=" + areaTo +
                ", balcony='" + balcony + '\'' +
                ", notFirst=" + notFirst +
                ", children='" + children + '\'' +
                ", animals='" + animals + '\'' +
                ", smoking='" + smoking + '\'' +
                ", party='" + party + '\'' +
                ", documents='" + documents + '\'' +
                ", monthly='" + monthly + '\'' +
                '}';
    }
}
