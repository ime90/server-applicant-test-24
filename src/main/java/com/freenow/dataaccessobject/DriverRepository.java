package com.freenow.dataaccessobject;

import com.freenow.domainobject.DriverDO;
import com.freenow.domainvalue.OnlineStatus;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 * Database Access Object for driver table.
 * <p/>
 */
public interface DriverRepository extends CrudRepository<DriverDO, Long>
{

    DriverDO findByUsername(String name);

    List<DriverDO> findByOnlineStatus(OnlineStatus onlineStatus);

    DriverDO findByCarDOLicensePlate(String licensePlate);

    List<DriverDO> findByCarDO_Rating(Integer rating);
}
