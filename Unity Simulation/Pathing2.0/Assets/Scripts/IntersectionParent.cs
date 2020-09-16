using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using Mirror;

public class IntersectionParent : NetworkBehaviour
{
    public virtual TrafficIntersection getIntersection()
    {
        return null;
    }

    public virtual void updateTimeOut(float newTimeOut) 
    {

    }

    public virtual void makeChange()
    {

    }
    
    public virtual void resetGeneration()
    {

    }
}
