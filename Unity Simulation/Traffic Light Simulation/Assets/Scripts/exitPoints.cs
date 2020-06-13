using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class exitPoints : MonoBehaviour
{
    Transform[] exitPointsList;

    /// <summary>
    /// Start is called on the frame when a script is enabled just before
    /// any of the Update methods is called the first time.
    /// </summary>
    void Start()
    {
        exitPointsList = GetComponentsInChildren<Transform>();
    }

    public Transform[] getExitPoints(){
        return exitPointsList;
    }
}
