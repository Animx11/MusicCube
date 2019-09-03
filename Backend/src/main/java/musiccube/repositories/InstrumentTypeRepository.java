package musiccube.repositories;

import musiccube.entities.InstrumentType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface InstrumentTypeRepository extends CrudRepository<InstrumentType, Integer>, PagingAndSortingRepository<InstrumentType, Integer> {

    @Query("SELECT i FROM InstrumentType i WHERE LOWER(i.instrumentTypeName) LIKE LOWER(CONCAT(?1,'%'))")
    Iterable<InstrumentType> findByInstrumentTypeName(String instrumentTypeName);

    boolean existsByInstrumentTypeName(String typeName);
    InstrumentType findOneByInstrumentTypeName(String typeName);

}
