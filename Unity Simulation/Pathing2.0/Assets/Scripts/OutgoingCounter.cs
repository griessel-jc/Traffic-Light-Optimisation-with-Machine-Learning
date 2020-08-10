using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class OutgoingCounter : MonoBehaviour
{
    bool started;
    private int numMovingCars;
    // Start is called before the first frame update
    void Start()
    {
       started = true; 
    }

    // Update is called once per frame
    void Update()
    {
        
    }

    public void reset(){
        numMovingCars = 0;
    }

    public int getNumberCars(){
        return numMovingCars;
    }

    private void OnCollisionEnter(Collision other) {
        ++numMovingCars;
    }
    private void OnTriggerEnter(Collider other) {
        ++numMovingCars;  
    }

    //Draw the Box Overlap as a gizmo to show where it currently is testing. Click the Gizmos button to see this
    void OnDrawGizmos()
    {
        Gizmos.color = Color.red;
        //Check that it is being run in Play Mode, so it doesn't try to draw this in Editor mode
        if (started)
            //Draw a cube where the OverlapBox is (positioned where your GameObject is as well as a size)
            Gizmos.DrawWireCube(transform.position, transform.localScale);
    }
}
