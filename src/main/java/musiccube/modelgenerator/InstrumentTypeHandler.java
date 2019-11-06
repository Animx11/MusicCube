package musiccube.modelgenerator;

import musiccube.entities.InstrumentType;
import musiccube.services.instrumentType.InstrumentTypeService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InstrumentTypeHandler {

    @Autowired
    private InstrumentTypeService service;
    private Logger logger = Logger.getLogger(InstrumentTypeHandler.class);

    public InstrumentType handleType(String typeName) {
        if (service.existsByName(typeName)) {
            return service.getOneByName(typeName);
        } else {
            InstrumentType type = new InstrumentType();
            type.setInstrumentTypeName(typeName);
            service.save(type);
            logger.info("Instrument type " + type.getInstrumentTypeName() + Constants.SAVED);
            return type;
        }
    }
}
