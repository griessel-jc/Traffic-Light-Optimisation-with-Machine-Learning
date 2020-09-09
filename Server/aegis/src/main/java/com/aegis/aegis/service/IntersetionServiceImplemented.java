 
package com.aegis.aegis.service;
 
import com.aegis.aegis.dao.IntersectionDAO;
import dto.intersectionDto;
import dto.intersectionsDto;
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
    public List<intersectionDto> getIntersections() {
        return intersectionDao.get();
    }
    
    @Transactional
    @Override
    public intersectionsDto getIntersections2() {
        return intersectionDao.get2();
    }
    @Override
    public void addIntersection(intersectionDto i) {
        intersectionDao.addIntersection(i);
    }

    @Override
    public void addStatistic(statisticDto statistic) {
        intersectionDao.addStatistic(statistic);
    }
    
    @Override
    public void addStatistic2(statisticDto statistic) {
        intersectionDao.addStatistic2(statistic);
    }


    
}
