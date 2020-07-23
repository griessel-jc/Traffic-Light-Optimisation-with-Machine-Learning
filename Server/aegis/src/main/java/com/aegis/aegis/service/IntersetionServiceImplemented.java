 
package com.aegis.aegis.service;
 
import com.aegis.aegis.dao.IntersectionDAO;
import com.aegis.aegis.modal.Intersection; 
import com.aegis.aegis.modal.Statistic;
import dto.intersectionDto;
import dto.statisticDto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class IntersetionServiceImplemented implements IntersectionService {
    @Autowired
    private IntersectionDAO intersectionDao;
    
    @Transactional
    @Override
    public List<Intersection> getIntersections() {
        return intersectionDao.get();
    }

    @Override
    public void addIntersection(intersectionDto i) {
        intersectionDao.addIntersection(i);
    }

    @Override
    public void addStatistic(statisticDto statistic) {
        intersectionDao.addStatistic(statistic);
    }
    
}
