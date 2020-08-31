using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class IncomingCounter : MonoBehaviour
{
    /*bool started;
    public LayerMask layerMask;

    private Collider[] hitColliders;*/

    private int numStationaryCars;

    void Start()
    {
        //started = true;
        numStationaryCars = 0;
    }

    void Update()
    {
        
    } 

    public int getNumberCars(){
        //hitColliders = Physics.OverlapBox(gameObject.transform.position, transform.localScale / 2, Quaternion.identity, layerMask);
        //Debug.Log("<color=blue> Number stat cars: "+hitColliders.Length+"</color>");
        //return hitColliders.Length;
        return numStationaryCars;
    }

    private void OnTriggerEnter(Collider other)
    {
        if (other.gameObject.CompareTag("CountingTag"))
        {
            numStationaryCars += 1;
        }
    }

    private void OnTriggerExit(Collider other)
    {
        if (other.gameObject.CompareTag("CountingTag"))
        {
            numStationaryCars -= 1;
        }
    }

    /*void OnDrawGizmos()
    {
        Gizmos.color = Color.red;
        if (started){
            Gizmos.DrawWireCube(transform.position, transform.localScale);
        }
            
    }*/
}
