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
        							+ "stationary cars: " + stopped.ToString() + "\n"
        							+ "moving cars: " + (cars.Length - stopped).ToString() + "\n";
        	yield return new WaitForSeconds(1);
    	}
    }
}
