package com.aegis.aegis.dao;

import com.aegis.aegis.modal.Trafficlight;
import java.util.List;
import javax.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TrafficlightDAOImplemented implements TrafficlightDAO{

    @Autowired
    private EntityManager entityManager;
    
    @Override
    public List<Trafficlight> get() {
        Session currSession = entityManager.unwrap(Session.class);
        Query<Trafficlight> query = currSession.createQuery("from Trafficlight", Trafficlight.class);
        List<Trafficlight> list = query.getResultList();
        return list;
    }
    
}
