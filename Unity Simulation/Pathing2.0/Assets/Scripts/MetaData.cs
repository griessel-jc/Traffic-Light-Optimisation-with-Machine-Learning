using UnityEngine;
using System.Collections;
using System.Collections.Generic;
using TMPro;

public class MetaData : MonoBehaviour
{
    // Start is called before the first frame update
	public TextMeshProUGUI UI_intersection;
	private GameObject[] cars;
	public int stopped;
    public GameObject time;
    public GameObject spawner;

    void Start(){
        StartCoroutine(Calc());
    }

    private IEnumerator Calc(){
    	while(true){
    		stopped = 0;
    		cars = GameObject.FindGameObjectsWithTag("CarSpawned");
    		for(int i =0; i < cars.Length; i++){
    			if(cars[i].GetComponent<Path>().speed == 0){
    				stopped = stopped + 1;
    			}
    		}


        	UI_intersection.text = "Number of cars: " + cars.Length.ToString() +"\n"
        							+ "Stationary cars: " + stopped.ToString() + "\n"
        							+ "Moving cars: " + (cars.Length - stopped).ToString() + "\n"
                                    + "Time: " + (Mathf.Floor(time.GetComponent<LightingManager>().timeOfDay)+100).ToString().Remove(0,1) + ":" + ((Mathf.Floor((time.GetComponent<LightingManager>().timeOfDay %1) * 60) +100).ToString()).Remove(0,1) + "\n"
                                    + "Spawn Rate: " + Mathf.Floor(spawner.GetComponent<spawning>().speed).ToString() + " cars/min";
        	yield return new WaitForSeconds(1);
    	}
    }
}
