﻿using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class ThreeWayIntersection : IntersectionParent
{
    /*Car Counters*/
    public GameObject inX;
    public GameObject outX;
    public GameObject inZ1;
    public GameObject inZ2;
    public GameObject outZ1;
    public GameObject outZ2;

    /*Traffic Lights*/
    public GameObject tlX1;
    public GameObject tlZ1;
    public GameObject tlZ2;

    /*Traffic Light Reg-Green cycle*/
    private float timeOut = 16.0f;    
    private float timeOutBothRed = 4.0f;
    private float timeLeft; 
    private float timeLeftBothRed;
    public bool light_configruation = false;
    bool isZ = false;
    bool isX = false;
    bool isXZ = false;
    bool insideLightChange = false;
    bool isMakeChange = false;
    
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
        float stationaryX = 0, stationaryY = 0, movingY = 0, movingX = 0, phase = 0;
        stationaryX += inX.GetComponent<IncomingCounter>().getNumberCars();
        stationaryY += inZ1.GetComponent<IncomingCounter>().getNumberCars();
        stationaryY += inZ2.GetComponent<IncomingCounter>().getNumberCars();
        
        movingX     += outX.GetComponent<OutgoingCounter>().getNumberCars();
        movingY     += outZ1.GetComponent<OutgoingCounter>().getNumberCars();
        movingY     += outZ2.GetComponent<OutgoingCounter>().getNumberCars();

        TrafficIntersection intersection = new TrafficIntersection();
        
        intersection.stationaryX   = stationaryX;
        intersection.stationaryY   = stationaryY;
        intersection.movingX       = movingX;
        intersection.movingY       = movingY;

        if (isX)
        {
            intersection.phase = 0;
        }
        else if (isZ)
        {
            intersection.phase = 1;
        }
        else
        {
            intersection.phase = 2;
        }

        return intersection;
    }

    public void changeLights()
    {
        if(light_configruation)
        {
            tlX1.tag = "Green"; //Green
            tlZ1.tag = "Car";
            tlZ2.tag = "Car";
            isZ = false;
            isX = true;
            isXZ = true;
        }
        else
        {
            tlX1.tag = "Car";
            tlZ1.tag = "Green"; //Green
            tlZ2.tag = "Green"; //Green
            isZ = true;
            isX = false;
            isXZ = true;
        }
    }

    // Update is called once per frame
    void Update()
    {
        StartCoroutine(Waiter());
        if(isMakeChange)
        {
            StartCoroutine(APILightChange());
        }
    }

    IEnumerator Waiter()
    {
        //reset();
        timeLeft -= Time.deltaTime;
        if(timeLeft <= 0f && isZ == true && insideLightChange == false)
        {
            //isX = false; isZ = false;
            isXZ = false;
            tlX1.tag = "Car";
            tlZ1.tag = "Car"; //Orange
            tlZ2.tag = "Car"; //Orange
            timeLeftBothRed -= Time.deltaTime;
            if(timeLeftBothRed <= 0f)
            {
                reset();
            }
        }
        else if(timeLeft <= 0f && isZ == false && insideLightChange == false)
        {
            //isX = false; isZ = false;
            isXZ = false;
            tlX1.tag = "Car"; //Orange
            tlZ1.tag = "Car"; 
            tlZ2.tag = "Car"; 
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

    public override void makeChange()
    {
        isMakeChange = true;
    }

    IEnumerator APILightChange()
    {
        if (!isXZ)
        {
            //nothing happens
            isMakeChange = false;
        }
        else if (isZ)
        {
            insideLightChange = true;
            tlX1.tag = "Car";
            tlZ1.tag = "Car"; //Orange
            tlZ2.tag = "Car"; //Orange

            timeLeftBothRed -= Time.deltaTime;
            if (timeLeftBothRed <= 0f)
            {
                isMakeChange = false;
                reset();
            }
        }
        else if (!isZ)
        {
            insideLightChange = true;
            tlX1.tag = "Car"; //Orange
            tlZ1.tag = "Car";
            tlZ2.tag = "Car";

            timeLeftBothRed -= Time.deltaTime;
            if (timeLeftBothRed <= 0f)
            {
                isMakeChange = false;
                reset();
            }
        }
        yield return null;
    }
}
