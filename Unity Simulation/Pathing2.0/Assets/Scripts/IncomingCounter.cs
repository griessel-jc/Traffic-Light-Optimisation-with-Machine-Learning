using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class IncomingCounter : MonoBehaviour
{
    bool started;
    public LayerMask layerMask;

    private Collider[] hitColliders;

    void Start()
    {
        started = true;
    }

    void Update()
    {
        
    }

    public int getNumberCars(){
        hitColliders = Physics.OverlapBox(gameObject.transform.position, transform.localScale / 2, Quaternion.identity, layerMask);
        return hitColliders.Length;
    }

    void OnDrawGizmos()
    {
        Gizmos.color = Color.red;
        if (started){
            Gizmos.DrawWireCube(transform.position, transform.localScale);
        }
            
    }
}
