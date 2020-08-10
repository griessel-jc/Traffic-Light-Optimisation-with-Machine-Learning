﻿using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class FourWayIntersection : IntersectionParent
{
    /*Car Counters*/
    public GameObject inX1;
    public GameObject inX2;
    public GameObject outX1;
    public GameObject outX2;
    public GameObject inZ1;
    public GameObject inZ2;
    public GameObject outZ1;
    public GameObject outZ2;

    /*Traffic Lights*/
    public GameObject tlX1;
    public GameObject tlX2;
    public GameObject tlZ1;
    public GameObject tlZ2;
    
    /*Traffic Light Reg-Green cycle*/
    private float timeOut = 16.0f;    
    private float timeOutBothRed = 4.0f;
    private float timeLeft; 
    private float timeLeftBothRed;
    public bool light_configruation = false;
    bool isZ = false;
   
    void Start()
    {
        reset();
    }

    void reset()
    {
        timeLeft = timeOut;
        timeLeftBothRed = timeOutBothRed;
        light_configruation = !light_configruation;
        changeLights();
    }

    public override TrafficIntersection getIntersection()
    {
        float stationaryX = 0, stationaryY =0, movingY = 0, movingX = 0;
        stationaryX += inX1.GetComponent<IncomingCounter>().getNumberCars();
        stationaryX += inX2.GetComponent<IncomingCounter>().getNumberCars();
        stationaryY += inZ1.GetComponent<IncomingCounter>().getNumberCars();
        stationaryY += inZ2.GetComponent<IncomingCounter>().getNumberCars();
        
        movingX     += outX1.GetComponent<OutgoingCounter>().getNumberCars();
        movingX     += outX2.GetComponent<OutgoingCounter>().getNumberCars();
        movingY     += outZ1.GetComponent<OutgoingCounter>().getNumberCars();
        movingY     += outZ2.GetComponent<OutgoingCounter>().getNumberCars();
        
        TrafficIntersection intersection = new TrafficIntersection();
        
        intersection.stationaryX   = stationaryX;
        intersection.stationaryY   = stationaryY;
        intersection.movingX       = movingX;
        intersection.movingY       = movingY;

        return intersection;
    }

    public void changeLights()
    {
        if(light_configruation)
        {
            tlX1.tag = "Green"; //Green
            tlX2.tag = "Green"; //Green
            tlZ1.tag = "Red";
            tlZ2.tag = "Red";
            isZ = false;
        }
        else
        {
            tlX1.tag = "Red";
            tlX2.tag = "Red";
            tlZ1.tag = "Green"; //Green
            tlZ2.tag = "Green"; //Green
            isZ = true;
        }
    }

    // Update is called once per frame
    void Update()
    {
        StartCoroutine(Waiter());
    }

    IEnumerator Waiter()
    {
        //reset();
        timeLeft -= Time.deltaTime;
        if(timeLeft <= 0f && isZ == true)
        {
            tlX1.tag = "Red";
            tlX2.tag = "Red";
            tlZ1.tag = "Orange"; 
            tlZ2.tag = "Orange"; 
            timeLeftBothRed -= Time.deltaTime;
            if(timeLeftBothRed <= 0f) 
            {
                reset();
            }
        }
        else if(timeLeft <= 0f && isZ == false)
        {
            tlX1.tag = "Orange";
            tlX2.tag = "Orange";
            tlZ1.tag = "Red"; 
            tlZ2.tag = "Red"; 
            timeLeftBothRed -= Time.deltaTime;
            if(timeLeftBothRed <= 0f) 
            {
                reset();
            }
        }
        yield return null;
    }

    public override void updateTimeOut(float newTimeOut)
    {
        timeOut = newTimeOut;
    }
}