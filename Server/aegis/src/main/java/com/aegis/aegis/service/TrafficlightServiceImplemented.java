 
package com.aegis.aegis.service;
 
import com.aegis.aegis.dao.TrafficlightDAO;
import com.aegis.aegis.modal.Trafficlight;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TrafficlightServiceImplemented implements TrafficlightService{
    @Autowired
    private TrafficlightDAO trafficlightDao;
    
    @Transactional
    @Override
    public List<Trafficlight> getTrafficlights() {
        return trafficlightDao.get();
    }
    
    
    
}
