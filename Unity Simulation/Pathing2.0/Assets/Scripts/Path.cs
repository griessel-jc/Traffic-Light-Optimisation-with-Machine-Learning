using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Path : MonoBehaviour {	
	[SerializeField]
	private Transform[] curves;
	private int nextCurve;
	private float speed;
	private Vector3 carPosition;
	private float t;
	private bool coroutingAllowed;

    void Start(){
        nextCurve = 0;
        t = 0f;
        speed = 0.4f;
        coroutingAllowed = true;
    }

    // Update is called once per frame
    void Update(){
        if(coroutingAllowed)
        	StartCoroutine(MoveCar(nextCurve));
        
    }

    private IEnumerator MoveCar(int curveNum){
    	coroutingAllowed = false;
    	Vector3 p0 = curves[curveNum].GetChild(0).position;
    	Vector3 p1 = curves[curveNum].GetChild(1).position;
    	Vector3 p2 = curves[curveNum].GetChild(2).position;
    	Vector3 p3 = curves[curveNum].GetChild(3).position;

    	while(t < 1){
    		t = t + (Time.deltaTime * speed);

    		carPosition = (Mathf.Pow(1 - t, 3) * p0) +
    			(3 * Mathf.Pow(1 - t, 2) * t * p1) +
    			(3 * (1-t) * Mathf.Pow(t, 2) * p2) +
    			(Mathf.Pow(t, 3) * p3);

    		transform.position = carPosition;
    		yield return new WaitForEndOfFrame();
    	}

    	t = 0f;

    	nextCurve = nextCurve + 1;

    	if(nextCurve > curves.Length - 1){
    		nextCurve = 0;
    	}

    	coroutingAllowed = true;
    }
}
