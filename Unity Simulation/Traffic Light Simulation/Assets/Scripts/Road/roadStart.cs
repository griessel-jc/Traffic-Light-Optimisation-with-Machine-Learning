using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.AI;

public class roadStart : MonoBehaviour
{
    public GameObject roadEnd;
    // Start is called before the first frame update
    void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {
        
    }

    public Vector3 getRoadEnd(){
        return roadEnd.gameObject.transform.position;
    }
}
