package mieten17.models;



public class Filter {
    public static final String PERCENT = "%";


    private String localityName;


    private String localityId;
    private Integer capacity;
    private String countRooms;
    private Integer priceFrom;
    private Integer priceTo;
    private Integer areaFrom;
    private Integer areaTo;
    private String balcony;
    private Integer notFirst;
    private String children;
    private String animals;
    private String smoking;
    private String party;
    private String documents;
    private String monthly;


    public String getLocalityName() {
        return localityName;
    }


    public String getLocalityId() {
        localityId = (this.localityId.equals(PERCENT)) ? null : this.localityId;
        return localityId;
    }

    public Integer getCapacity() {
        capacity = (this.capacity == 0) ? null : this.capacity;
            return capacity;
    }

    public String getCountRooms() {
        countRooms = (this.countRooms.equals(PERCENT)) ? null : this.countRooms;
        return countRooms;
    }

    public Integer getPriceFrom() {
        priceFrom = (this.priceFrom == 0) ? null : this.priceFrom;
        return priceFrom;
    }

    public Integer getPriceTo() {
        priceTo = (this.priceTo == Integer.MAX_VALUE) ? null : this.priceTo;
        return priceTo;
    }

    public Integer getAreaFrom() {
        areaFrom = (this.areaFrom == 0) ? null : this.areaFrom;
        return areaFrom;
    }

    public Integer getAreaTo() {
        areaTo = (this.areaTo == Integer.MAX_VALUE) ? null : this.areaTo;
        return areaTo;
    }

    public String getBalcony() {
        balcony = (this.balcony.equals(PERCENT)) ? null : this.balcony;
        return balcony;
    }

    public Integer getNotFirst() {
        notFirst = (this.notFirst == Integer.MAX_VALUE) ? null : this.notFirst;
        return notFirst;
    }

    public String getChildren() {
        children = (this.children.equals(PERCENT)) ? null : this.children;
        return children;
    }

    public String getAnimals() {
        animals = (this.animals.equals(PERCENT)) ? null : this.animals;
        return animals;
    }

    public String getSmoking() {
        smoking = (this.smoking.equals(PERCENT)) ? null : this.smoking;
        return smoking;
    }

    public String getParty() {
        party = (this.party.equals(PERCENT)) ? null : this.party;
        return party;
    }

    public String getDocuments() {
        documents = (this.documents.equals(PERCENT)) ? null : this.documents;
        return documents;
    }

    public String getMonthly() {
        monthly = (this.monthly.equals(PERCENT)) ? null : this.monthly;
        return monthly;
    }

    public void setLocalityName(String localityName) {
        this.localityName = localityName;
    }

    public void setLocalityId(String localityId) {
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
