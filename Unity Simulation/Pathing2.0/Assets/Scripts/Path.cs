using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using Mirror;

public class Path : NetworkBehaviour {	
	private GameObject curves;

    [SyncVar]
	private int nextCurve;
	private float speed;

    [SyncVar]
	private Vector3 carPosition;
	[SyncVar]
    private float t;
	[SyncVar]
    private bool coroutingAllowed;

    private int[] moveToArray;
    private int[][] nextCurveOptions;

    void Start(){

        curves = GameObject.Find("roads");

        int[] startpoint = new int[] {0, 20,31 ,44, 42};
        nextCurve = startpoint[Random.Range(0,startpoint.Length)];
        t = 0f;
        speed = 0.4f;
        coroutingAllowed = true;

        nextCurveOptions = new int[52][];
        nextCurveOptions[0] = new int[] {2, 13};
        nextCurveOptions[1] = new int[] {36, 37, 38};
        nextCurveOptions[2] = new int[] {3};
        nextCurveOptions[3] = new int[] {4};
        nextCurveOptions[4] = new int[] {5};
        nextCurveOptions[5] = new int[] {6, 8};
        nextCurveOptions[6] = new int[] {7};
        nextCurveOptions[7] = new int[] {11, 12, 32};
        nextCurveOptions[8] = new int[] {9};
        nextCurveOptions[9] = new int[] {-1};
        nextCurveOptions[10] = new int[] {14, 15};
        nextCurveOptions[11] = new int[] {10};
        nextCurveOptions[12] = new int[] {30};
        nextCurveOptions[13] = new int[] {1};
        nextCurveOptions[14] = new int[] {3};
        nextCurveOptions[15] = new int[] {16};
        nextCurveOptions[16] = new int[] {-1};
        nextCurveOptions[17] = new int[] {18, 19};
        nextCurveOptions[18] = new int[] {16};
        nextCurveOptions[19] = new int[] {1};
        nextCurveOptions[20] = new int[] {21, 22};
        nextCurveOptions[21] = new int[] {7};
        nextCurveOptions[22] = new int[] {23};
        nextCurveOptions[23] = new int[] {24};
        nextCurveOptions[24] = new int[] {17};
        nextCurveOptions[25] = new int[] {48, 49};
        nextCurveOptions[26] = new int[] {33, 34, 35};
        nextCurveOptions[27] = new int[] {28, 29};
        nextCurveOptions[28] = new int[] {9};
        nextCurveOptions[29] = new int[] {23};
        nextCurveOptions[30] = new int[] {-1};
        nextCurveOptions[31] = new int[] {39, 40, 41};
        nextCurveOptions[32] = new int[] {25};
        nextCurveOptions[33] = new int[] {27};
        nextCurveOptions[34] = new int[] {30};
        nextCurveOptions[35] = new int[] {10};
        nextCurveOptions[36] = new int[] {30};
        nextCurveOptions[37] = new int[] {27};
        nextCurveOptions[38] = new int[] {25};
        nextCurveOptions[39] = new int[] {10};
        nextCurveOptions[40] = new int[] {25};
        nextCurveOptions[41] = new int[] {27};
        nextCurveOptions[42] = new int[] {46, 47};
        nextCurveOptions[43] = new int[] {-1};
        nextCurveOptions[44] = new int[] {50, 51};
        nextCurveOptions[45] = new int[] {-1};
        nextCurveOptions[46] = new int[] {26};
        nextCurveOptions[47] = new int[] {45};
        nextCurveOptions[48] = new int[] {43};
        nextCurveOptions[49] = new int[] {45};
        nextCurveOptions[50] = new int[] {26};
        nextCurveOptions[51] = new int[] {43};
    }

    // Update is called once per frame
    void Update(){
        if(coroutingAllowed)
        	StartCoroutine(MoveCar(nextCurve));
        
    }

    private IEnumerator MoveCar(int curveNum){
    	coroutingAllowed = false;
    	Vector3 p0 = curves.transform.Find("Curve (" + curveNum.ToString() + ")").GetChild(0).position;
    	Vector3 p1 = curves.transform.Find("Curve (" + curveNum.ToString() + ")").GetChild(1).position;
    	Vector3 p2 = curves.transform.Find("Curve (" + curveNum.ToString() + ")").GetChild(2).position;
    	Vector3 p3 = curves.transform.Find("Curve (" + curveNum.ToString() + ")").GetChild(3).position;

        Vector3 carPositionPrev;
        Vector3 directionVector;
        carPosition = p0;

    	while(t < 1){
    		t = t + (Time.deltaTime * speed);
            carPositionPrev = carPosition;
    		carPosition = (Mathf.Pow(1 - t, 3) * p0) +
    			(3 * Mathf.Pow(1 - t, 2) * t * p1) +
    			(3 * (1-t) * Mathf.Pow(t, 2) * p2) +
    			(Mathf.Pow(t, 3) * p3);

    		transform.position = carPosition;
            directionVector = carPosition - carPositionPrev;
            transform.rotation = Quaternion.LookRotation(directionVector);
    		yield return new WaitForEndOfFrame();
    	}

    	t = 0f;

        moveToArray = nextCurveOptions[nextCurve];
        nextCurve = moveToArray[Random.Range(0,moveToArray.Length)];

        if(nextCurve == -1){
            Destroy(gameObject);
        }
    	// if(nextCurve > curves.Length - 1){
    	// 	nextCurve = 0;
    	// }

    	coroutingAllowed = true;
    }
}
