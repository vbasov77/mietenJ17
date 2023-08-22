package mieten17.services;


import mieten17.models.Address;
import mieten17.models.Rule;
import mieten17.repositories.RuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RuleService {

    @Autowired
    private RuleRepository ruleRepository;

    public void createRules(Long objId, String children, String animals, String smoking, String party,
                            String documents, String monthly) {

        Rule checkRules = ruleRepository.findRuleByObjId(objId);
        if (checkRules == null) {
            Rule rules = new Rule();
            rules.setObjId(objId);
            rules.setChildren(children);
            rules.setAnimals(animals);
            rules.setSmoking(smoking);
            rules.setParty(party);
            rules.setDocuments(documents);
            rules.setMonthly(monthly);
            ruleRepository.save(rules);
        } else {
            ruleRepository.updateRules(children, animals, smoking, party, documents, monthly, objId);
        }
    }

}
