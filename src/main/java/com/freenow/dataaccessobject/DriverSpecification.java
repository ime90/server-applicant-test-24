package com.freenow.dataaccessobject;

import com.freenow.domainobject.CarDO;
import com.freenow.domainobject.DriverDO;
import com.freenow.helper.DriverDORequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class DriverSpecification implements Specification<DriverDO> {

    DriverDORequest criteria = new DriverDORequest();

    public DriverSpecification(DriverDORequest criteria) {
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<DriverDO> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        final List<Predicate> predicates = new ArrayList<>();
        if (criteria.getUsername() != null) {
            final Predicate username = builder.equal(root.<String>get("username"), criteria.getUsername());
            predicates.add(username);
        }
        if (criteria.getOnlineStatus() != null) {
            final Predicate onlineStatus = builder.equal(root.<String>get("onlineStatus"), criteria.getOnlineStatus());
            predicates.add(onlineStatus);
        }
        if (criteria.getCarDO() != null) {
            Join<DriverDO, CarDO> car = root.join("carDO");
            final Predicate licensePlate = builder.equal(car.get("licensePlate"), criteria.getCarDO().getLicensePlate());
            predicates.add(licensePlate);
        }
        if (criteria.getCarDO() != null) {
            Join<DriverDO, CarDO> car = root.join("carDO");
            final Predicate rating = builder.equal(car.get("rating"), criteria.getCarDO().getRating());
            predicates.add(rating);
        }
        return builder.and(predicates.toArray(new Predicate[predicates.size()]));
    }

}