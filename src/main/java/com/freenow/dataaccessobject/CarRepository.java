package com.freenow.dataaccessobject;

import com.freenow.domainobject.CarDO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * Database Access Object for car table.
 * <p/>
 */
public interface CarRepository extends CrudRepository<CarDO, Long>
{

    List<CarDO> findByIsBeingUsed(boolean check);

}