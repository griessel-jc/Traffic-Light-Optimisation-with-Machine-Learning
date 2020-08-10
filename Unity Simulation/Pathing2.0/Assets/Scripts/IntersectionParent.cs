using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class IntersectionParent : MonoBehaviour
{
    public virtual TrafficIntersection getIntersection()
    {
        return null;
    }

    public virtual void updateTimeOut(float newTimeOut) {

    }
}
