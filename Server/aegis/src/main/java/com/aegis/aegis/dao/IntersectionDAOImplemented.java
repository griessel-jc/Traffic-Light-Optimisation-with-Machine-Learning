package com.aegis.aegis.dao;
import com.aegis.aegis.modal.Generation;
import com.aegis.aegis.modal.Intersection;
import com.aegis.aegis.modal.Statistic;
import com.aegis.aegis.modal.Statistic2;
import dto.intersectionDto;
import dto.intersectionDto2;
import dto.intersectionsDto;
import dto.statisticDto;
import exception.RecordNotFoundException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;
import org.hibernate.Session;
import org.hibernate.procedure.ProcedureOutputs;
import org.hibernate.query.Query; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class IntersectionDAOImplemented implements IntersectionDAO{

    @Autowired
    private EntityManager entityManager;
    
    @Override
    public List<intersectionDto> get() {
        Session currSession = entityManager.unwrap(Session.class);
        Query query = currSession.createQuery("from Intersection", Intersection.class);
        List<Intersection> db_intersections= query.getResultList(); 
        List<intersectionDto> intersections = new ArrayList();
        db_intersections.stream().map(i -> {
            intersectionDto intersection = new intersectionDto();
            intersection.setName(i.getName());
            intersection.setTl_Id(i.getId());
            return intersection;
        }).map(intersection -> { 
            Integer tl_id           = intersection.getTl_Id();
            intersection.setStatistics((List<Statistic>)currSession.createQuery("from Statistic as o where o.tl_id="+tl_id+" order by o.timestamp desc").setMaxResults(20).list());
            return intersection;
        }).forEachOrdered(intersection -> {
            intersections.add(intersection);
        });
        return intersections;         
    } 
    
    
    @Override
    public intersectionsDto get2() {
        Session currSession = entityManager.unwrap(Session.class);
        Query query = currSession.createQuery("from Intersection", Intersection.class);
        List<Intersection> db_intersections= query.getResultList(); 
        List<intersectionDto> ai = new ArrayList();
        
        db_intersections.stream().map(i -> {
            intersectionDto intersection = new intersectionDto();
            intersection.setName(i.getName());
            intersection.setTl_Id(i.getId());
            return intersection;
        }).map(intersection -> { 
            Integer tl_id           = intersection.getTl_Id();
            intersection.setStatistics((List<Statistic>)currSession.createQuery("from Statistic as o where o.tl_id="+tl_id+" order by o.timestamp desc").setMaxResults(20).list());
            return intersection;
        }).forEachOrdered(intersection -> {
            ai.add(intersection);
        });
        query = currSession.createQuery("from Intersection", Intersection.class);  
        db_intersections= query.getResultList(); 
        List<intersectionDto2> normal = new ArrayList();
        db_intersections.stream().map(i -> {
            intersectionDto2 intersection = new intersectionDto2();
            intersection.setName(i.getName());
            intersection.setTl_Id(i.getId());
            return intersection;
        }).map(intersection -> { 
            Integer tl_id           = intersection.getTl_Id();
            intersection.setStatistics((List<Statistic2>)currSession.createQuery("from Statistic2 as o where o.tl_id="+tl_id+" order by o.timestamp desc").setMaxResults(20).list());
            return intersection;
        }).forEachOrdered(intersection -> {
            normal.add(intersection);
        });
        intersectionsDto intersections = new intersectionsDto();
        intersections.setAi(ai);
        intersections.setNormal(normal);
        return intersections; 
    }
    
    @Override
    public void addIntersection(intersectionDto i) {
        Intersection intersection = new Intersection();
        intersection.setName(i.getName());
        Session currSession = entityManager.unwrap(Session.class);
        currSession.saveOrUpdate(intersection);
    }

    @Override
    public void addStatistic(statisticDto s) {
        Intersection i;
        try{
            i = this.findbyName(s.getName());
            if(i != null){
                Statistic statistic = new Statistic();
                statistic.setPhase(s.getPhase());
                statistic.setIntersection_Id(i.getId());
                statistic.setMovingX(s.getMovingX());
                statistic.setMovingY(s.getMovingY());
                statistic.setStationaryX(s.getStationaryX());
                statistic.setStationaryY(s.getStationaryY());
                Session currSession = entityManager.unwrap(Session.class);
                currSession.saveOrUpdate(statistic);
            }
        }catch(RecordNotFoundException re){}
        
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("clean_db");
        try{
            query.execute();
        }finally{
            query.unwrap(ProcedureOutputs.class).release();
        }
    }

    @Override
    public void addStatistic2(statisticDto s) {
        Intersection i;
        try{
            i = this.findbyName(s.getName());
            if(i != null){
                Statistic2 statistic = new Statistic2();
                statistic.setPhase(s.getPhase());
                statistic.setIntersection_Id(i.getId());
                statistic.setMovingX(s.getMovingX());
                statistic.setMovingY(s.getMovingY());
                statistic.setStationaryX(s.getStationaryX());
                statistic.setStationaryY(s.getStationaryY());
                Session currSession = entityManager.unwrap(Session.class);
                currSession.saveOrUpdate(statistic);
            }
        }catch(RecordNotFoundException re){}
        
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("clean_db_2");
        try{
            query.execute();
        }finally{
            query.unwrap(ProcedureOutputs.class).release();
        }
    }
    
    
    @Override
    public Intersection findbyName(String name) {
        Session currSession = entityManager.unwrap(Session.class);
        String SQL_QUERY    = "from Intersection as o where o.name=?0";
        Query query         = currSession.createQuery(SQL_QUERY);
        query.setParameter(0, name);
        List list           = query.list();
        if((list != null) && (!list.isEmpty())){
            Intersection i = (Intersection) list.get(0);
            return i;
        }
        throw new RecordNotFoundException("No Intersection record exists for given username", name);
    }

    @Override
    public void addGeneration() {
        try{
            Generation gen = new Generation();
            Session currSession = entityManager.unwrap(Session.class);
            currSession.saveOrUpdate(gen);
        }catch(RecordNotFoundException re){} 
    }

     
}
