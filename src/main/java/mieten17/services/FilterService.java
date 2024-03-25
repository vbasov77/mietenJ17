package mieten17.services;

import jakarta.servlet.http.HttpSession;
import mieten17.models.Filter;
import mieten17.models.Locality;
import mieten17.models.Obj;
import mieten17.repositories.LocalityRepository;
import mieten17.repositories.ObjRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FilterService {
    public static final String PERCENT = "%";
    @Autowired
    private LocalityRepository localityRepository;
    @Autowired
    private ObjRepository objRepository;

    public String countRoomsSession(String countRooms, HttpSession session) {
        String countRoomsDb = PERCENT;
        if (countRooms.isEmpty()) {
            session.setAttribute("countRooms - ", "%");
        } else {
            countRoomsDb = countRooms;
            session.setAttribute("countRooms", countRooms);
        }
        return countRoomsDb;
    }

    public Integer capacitySession(Integer capacity, HttpSession session) {
        Integer capacityDb = 0;
        if (capacity == null) {
            session.setAttribute("capacity", capacityDb);
        } else {
            capacityDb = capacity;
            session.setAttribute("capacity", capacity);
        }
        return capacityDb;
    }

    public Integer priceFromSession(Integer priceFrom, HttpSession session) {
        Integer priceFromDb = 0;
        if (priceFrom == null) {
            session.setAttribute("priceFrom", 0);
        } else {
            priceFromDb = priceFrom;
            session.setAttribute("priceFrom", priceFrom);
        }
        return priceFromDb;
    }

    public Integer priceToDbSession(Integer priceTo, HttpSession session) {
        Integer priceToDb = Integer.MAX_VALUE;
        if (priceTo == null) {
            session.setAttribute("priceTo", Integer.MAX_VALUE);
        } else {
            priceToDb = priceTo;
            session.setAttribute("priceTo", priceTo);
        }
        return priceToDb;
    }

    public Integer areaFromSession(Integer areaFrom, HttpSession session) {
        Integer areaFromDb = 0;
        if (areaFrom == null) {
            session.setAttribute("areaFrom", 0);
        } else {
            areaFromDb = areaFrom;
            session.setAttribute("areaFrom", areaFrom);
        }
        return areaFromDb;
    }

    public Integer areaToDbSession(Integer areaTo, HttpSession session) {
        Integer areaToDb = Integer.MAX_VALUE;
        if (areaTo == null) {
            session.setAttribute("areaTo", Integer.MAX_VALUE);
        } else {
            areaToDb = areaTo;
            session.setAttribute("areaTo", areaTo);
        }
        return areaToDb;
    }


    public String balconySession(String  balcony, HttpSession session) {
        String balconyDb = PERCENT;
        session.setAttribute("balcony", PERCENT);
        if (balcony != null && !balcony.equals(PERCENT)) {
            session.setAttribute("balcony", String.join(",", balcony));
            balconyDb = "%" + balcony + "%";
        }
        return balconyDb;
    }

    public Integer notFirstSession(Integer notFirst, HttpSession session) {
        Integer notFirstDb = Integer.MAX_VALUE;
        if (notFirst == null || notFirst == Integer.MAX_VALUE) {
            session.setAttribute("notFirst", Integer.MAX_VALUE);
        } else {
            notFirstDb = 1;
            session.setAttribute("notFirst", 1);
        }
        return notFirstDb;
    }

    public Integer notEndSession(Integer notEnd, HttpSession session) {
        Integer notEndDb = 1;
        if (notEnd == null) {
            notEndDb = 0;
            session.setAttribute("notEnd", 0);
        } else {
            session.setAttribute("notEnd", 1);
        }
        return notEndDb;
    }

    public String localityIdSession(String localityName, HttpSession session) {
        String localityId = PERCENT;
        if (localityName != null && !localityName.isEmpty()) {
            // Получим данные по выбранному городу и запишем всё в сессию localityId localityName.
            Optional<Locality> loc = localityRepository.findByLocality(localityName);
            session.setAttribute("localityId", loc.get().getId());
            session.setAttribute("localityName", localityName);
            localityId = String.valueOf(loc.get().getId());
        } else {
            session.setAttribute("localityId", null);
            session.removeAttribute("localityName");
        }
        return localityId;
    }
    
    public String childrenSession(String children, HttpSession session) {
        String childrenDb = PERCENT;
        if (children == null) {
            session.setAttribute("children", PERCENT);
        } else {
            childrenDb = children;
            session.setAttribute("children", children);
        }
        return childrenDb;
    }

    public String animalsSession(String animals, HttpSession session) {
        String animalsDb = PERCENT;
        if (animals == null) {
            session.setAttribute("animals", PERCENT);
        } else {
            animalsDb = animals;
            session.setAttribute("animals", animals);
        }
        return animalsDb;
    }

    public String smokingSession(String smoking, HttpSession session) {
        String smokingDb = PERCENT;
        if (smoking == null) {
            session.setAttribute("smoking", PERCENT);
        } else {
            smokingDb = smoking;
            session.setAttribute("smoking", smoking);
        }
        return smokingDb;
    }

    public String partySession(String party, HttpSession session) {
        String partyDb = PERCENT;
        if (party == null) {
            session.setAttribute("party", PERCENT);
        } else {
            partyDb = party;
            session.setAttribute("party", party);
        }
        return partyDb;
    }

    public String documentsSession(String documents, HttpSession session) {
        String documentsDb = PERCENT;
        if (documents == null) {
            session.setAttribute("documents", PERCENT);
        } else {
            documentsDb = documents;
            session.setAttribute("documents", documents);
        }
        return documentsDb;
    }

    public String monthlySession(String monthly, HttpSession session) {
        String monthlyDb = PERCENT;
        if (monthly == null) {
            session.setAttribute("monthly", PERCENT);
        } else {
            monthlyDb = monthly;
            session.setAttribute("monthly", monthly);
        }
        return monthlyDb;
    }


    public Filter getFilter(HttpSession session) {
        Filter filter = new Filter();
        filter.setLocalityName((String) session.getAttribute("localityName"));
        filter.setLocalityId((Long) session.getAttribute("localityId"));

        Integer capacity = (session.getAttribute("capacity") == "") ? null : (Integer) session.getAttribute("capacity");
        filter.setCapacity(capacity);

        filter.setCountRooms((String) session.getAttribute("countRooms"));
        filter.setPriceFrom((Integer) session.getAttribute("priceFrom"));
        filter.setPriceTo((Integer) session.getAttribute("priceTo"));
        filter.setAreaFrom((Integer) session.getAttribute("areaFrom"));
        filter.setAreaTo((Integer) session.getAttribute("areaTo"));
        filter.setBalcony((String) session.getAttribute("balcony"));
        filter.setNotFirst((Integer) session.getAttribute("notFirst"));
        filter.setNotEnd((Integer) session.getAttribute("notEnd"));
        filter.setChildren((String) session.getAttribute("children"));
        filter.setAnimals((String) session.getAttribute("animals"));
        filter.setSmoking((String) session.getAttribute("smoking"));
        filter.setParty((String) session.getAttribute("party"));
        filter.setDocuments((String) session.getAttribute("documents"));
        filter.setMonthly((String) session.getAttribute("monthly"));

        return filter;
    }

    public void removeSessionFilter(HttpSession session) {
        /**
         * Удаляет все сессии фильтра, кроме localityName и localityId
         * для того, чтобы изменить город поиска, юзеру предложено это сделать в шапке сайта или в самом фильтре.
         */

        session.setAttribute("capacity", "");
        session.removeAttribute("countRooms");
        session.removeAttribute("priceFrom");
        session.removeAttribute("priceTo");
        session.removeAttribute("areaFrom");
        session.removeAttribute("areaTo");
        session.removeAttribute("balcony");
        session.removeAttribute("notFirst");
        session.removeAttribute("notEnd");
        session.removeAttribute("children");
        session.removeAttribute("animals");
        session.removeAttribute("smoking");
        session.removeAttribute("party");
        session.removeAttribute("documents");
        session.removeAttribute("monthly");
    }

    public List<Obj> variablesByFilter(Filter filter, HttpSession session) {
        String localityId = localityIdSession(filter.getLocalityName(), session);
        String countRooms = filter.getCountRooms();

        if (countRooms == null || countRooms.isEmpty()) {
            countRooms = "";
        }
        String countRoomsDb = countRoomsSession(countRooms, session);
        Integer capacityDb = capacitySession(filter.getCapacity(), session);
        Integer priceFromDb = priceFromSession(filter.getPriceFrom(), session);
        Integer priceToDb = priceToDbSession(filter.getPriceTo(), session);
        Integer areaFromDb = areaFromSession(filter.getAreaFrom(), session);
        Integer areaToDb = areaToDbSession(filter.getAreaTo(), session);
        String balconyDb = balconySession(filter.getBalcony(), session);
        Integer notFirstDb = notFirstSession(filter.getNotFirst(), session);
        Integer notEndDb = notEndSession(filter.getNotEnd(), session);
        String childrenDb = childrenSession(filter.getChildren(), session);
        String animalsDb = animalsSession(filter.getAnimals(), session);
        String smokingDb = smokingSession(filter.getSmoking(), session);
        String partyDb = partySession(filter.getParty(), session);
        String documentsDb = documentsSession(filter.getDocuments(), session);
        String monthlyDb = monthlySession(filter.getMonthly(), session);

        return objRepository.getFilterObj(localityId, capacityDb, countRoomsDb, priceFromDb, priceToDb, areaFromDb, areaToDb,
                balconyDb, notFirstDb, notEndDb, childrenDb, animalsDb, smokingDb, partyDb, documentsDb, monthlyDb);
    }

}
