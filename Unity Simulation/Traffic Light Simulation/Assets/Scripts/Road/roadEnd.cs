using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.AI;

public class roadEnd : MonoBehaviour
{
    public GameObject LeftExit, ForwardExit, RightExit;
    // Start is called before the first frame update
    void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {

    }

    public Vector3 getExitPoint(){
        int randomeIndex = Random.Range(0, 3);
        if (randomeIndex == 0)
            return LeftExit.gameObject.transform.position;
        else if (randomeIndex == 1)
            return RightExit.gameObject.transform.position;
        else return ForwardExit.gameObject.transform.position;
    }

    /*public Vector3 getLeftPoint(){
        return LeftExit.gameObject.transform.position;
    }

    public Vector3 getRightPoint(){
        return RightExit.gameObject.transform.position;
    }

    public Vector3 getForwardPoint(){
        return ForwardExit.gameObject.transform.position;
    }*/
}
