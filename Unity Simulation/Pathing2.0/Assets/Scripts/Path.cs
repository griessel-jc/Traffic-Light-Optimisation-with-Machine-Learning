using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using Mirror;

public class Path : NetworkBehaviour 
{	
	private GameObject curves;

    [SyncVar]
	private int nextCurve;
    
    [SyncVar]
	public float speed;

    [SyncVar]
	private Vector3 carPosition;
	
    [SyncVar]
    private float t;

	[SyncVar]
    private bool coroutingAllowed;

    [SyncVar]
    private SyncListCustom moveToArray;
    
    private int[][] nextCurveOptions;

    [SyncVar]
    private SyncListCustom startpoint;

    //The max speed of any car
    [SyncVar]
    private float maxSpeed = 0.4f;

    void Start()
    {

        curves = GameObject.Find("roads");

        startpoint.Add(0);
        startpoint.Add(20);
        startpoint.Add(44);
        startpoint.Add(42);
        startpoint.Add(52);
        startpoint.Add(56);
        startpoint.Add(57);
        startpoint.Add(62);
        startpoint.Add(63);
        startpoint.Add(65);
        
        //startpoint = new int[] {0, 20, 31, 44, 42};

        nextCurve = startpoint[Random.Range(0,startpoint.Count)];
        t = 0f;
        speed = 0.1f;
        coroutingAllowed = true;

        nextCurveOptions = new int[90][];
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
        nextCurveOptions[30] = new int[] {67, 68};
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
        nextCurveOptions[52] = new int[] {70, 71};
        nextCurveOptions[53] = new int[] {-1};
        nextCurveOptions[54] = new int[] {72, 78, 79, 80, 81};
        nextCurveOptions[55] = new int[] {66, 69};
        nextCurveOptions[56] = new int[] {77, 89};
        nextCurveOptions[57] = new int[] {76, 88};
        nextCurveOptions[58] = new int[] {-1};
        nextCurveOptions[59] = new int[] {-1};
        nextCurveOptions[60] = new int[] {-1};
        nextCurveOptions[61] = new int[] {-1};
        nextCurveOptions[62] = new int[] {74, 86};
        nextCurveOptions[63] = new int[] {75, 87};
        nextCurveOptions[64] = new int[] {-1};
        nextCurveOptions[65] = new int[] {73, 82, 83, 84, 85};
        nextCurveOptions[66] = new int[] {31};
        nextCurveOptions[67] = new int[] {54};
        nextCurveOptions[68] = new int[] {53};
        nextCurveOptions[69] = new int[] {53};
        nextCurveOptions[70] = new int[] {54};
        nextCurveOptions[71] = new int[] {31};
        nextCurveOptions[72] = new int[] {64};
        nextCurveOptions[73] = new int[] {55};
        nextCurveOptions[74] = new int[] {58};
        nextCurveOptions[75] = new int[] {59};
        nextCurveOptions[76] = new int[] {61};
        nextCurveOptions[77] = new int[] {60};
        nextCurveOptions[78] = new int[] {60};
        nextCurveOptions[79] = new int[] {61};
        nextCurveOptions[80] = new int[] {58};
        nextCurveOptions[81] = new int[] {59};
        nextCurveOptions[82] = new int[] {59};
        nextCurveOptions[83] = new int[] {58};
        nextCurveOptions[84] = new int[] {61};
        nextCurveOptions[85] = new int[] {60};
        nextCurveOptions[86] = new int[] {55};
        nextCurveOptions[87] = new int[] {64};
        nextCurveOptions[88] = new int[] {64};
        nextCurveOptions[89] = new int[] {55};

        
    }

    // Update is called once per frame
    void Update()
    {	

        if(coroutingAllowed)
        	StartCoroutine(MoveCar(nextCurve));
        
    }


    private IEnumerator MoveCar(int curveNum)
    {
    	coroutingAllowed = false;
    	Vector3 p0 = curves.transform.Find("Curve (" + curveNum.ToString() + ")").GetChild(0).position;
    	Vector3 p1 = curves.transform.Find("Curve (" + curveNum.ToString() + ")").GetChild(1).position;
    	Vector3 p2 = curves.transform.Find("Curve (" + curveNum.ToString() + ")").GetChild(2).position;
    	Vector3 p3 = curves.transform.Find("Curve (" + curveNum.ToString() + ")").GetChild(3).position;
		
		maxSpeed = 10.0f/(Vector3.Distance(p0,p3));


        Vector3 carPositionPrev;
        Vector3 directionVector;
        carPosition = p0;
        carPositionPrev = carPosition;

        RaycastHit hit;
        Ray myRay;

    	while (t < 1)
        {
            myRay = new Ray(transform.position, transform.TransformDirection(Vector3.forward));
            if(Physics.Raycast(myRay, out hit, 30) && hit.collider.gameObject.CompareTag("Car")){
                if(hit.distance < 5.0f){
                    speed = 0;
                }
                else{
                	Debug.Log("xxxxxxxxxxxxxxxxxxxxxxxx");
                    speed = maxSpeed*(Mathf.Pow(2,hit.distance/30.0f) - 1.15f);
                }
            }
            else if(speed < maxSpeed && speed < (maxSpeed - 0.01f)){
                speed = speed + 0.01f;
            }
            else{
                speed = maxSpeed;
            }

            if(speed > 0f){
                carPositionPrev = carPosition;
            }
            t = t + (Time.deltaTime * speed);
            carPosition = (Mathf.Pow(1 - t, 3) * p0) + 
            (3 * Mathf.Pow(1 - t, 2) * t * p1) + 
            (3 * (1 - t) * Mathf.Pow(t, 2) * p2) + 
            (Mathf.Pow(t, 3) * p3);

            transform.position = carPosition;
            directionVector = carPosition - carPositionPrev;
            if (directionVector != Vector3.zero)
            {
                transform.rotation = Quaternion.LookRotation(directionVector);
            }
            yield return new WaitForEndOfFrame();
        }


        //clean up and prep for next curve
    	t = 0f;

        for (int i = 0; i < nextCurveOptions[nextCurve].Length; i++){
            moveToArray.Add(nextCurveOptions[nextCurve][i]);
        }

        //moveToArray = nextCurveOptions[nextCurve];
        nextCurve = moveToArray[Random.Range(0,moveToArray.Count)];

        if(nextCurve == -1){
            Destroy(gameObject);
        }

    	coroutingAllowed = true;
        moveToArray.Clear();
    }
}

[System.Serializable]
public class SyncListCustom : SyncList<int> { }