package com.freenow.dataaccessobject;

import com.freenow.domainobject.DriverDO;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface CustomDriverRepository
        extends JpaRepository<DriverDO, Long>, JpaSpecificationExecutor<DriverDO> {

}