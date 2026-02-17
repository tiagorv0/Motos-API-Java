package com.vazzoller.motosapi.domain.specification;

import com.vazzoller.motosapi.domain.model.Motorcycle;
import org.springframework.data.jpa.domain.Specification;

public class MotorcycleSpecification {
    public static Specification<Motorcycle> findPlate(String plate){
        return (root, query, cb) ->
                plate == null ? null : cb.like(cb.lower(root.get("plate")), "%" + plate.toLowerCase() + "%");
    }

//    public static Specification<Motorcycle> activeTrue(){
//        return (root, query, cb) ->
//                cb.isTrue(root.get("is_active"));
//    }
}
