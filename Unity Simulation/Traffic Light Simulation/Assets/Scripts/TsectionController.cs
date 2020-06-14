using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class TsectionController : MonoBehaviour
{	
	public GameObject Entrance;
	public GameObject Left;
	public GameObject Right;
	public GameObject Split;
	
	public GameObject Lnorth;
	public GameObject Lsouth;
	public GameObject Least;
	public GameObject Lwest;
	
	public int z = 8;
	public int x = 8;
	
	// Start is called before the first frame update
	void Start(){
		StartCoroutine(waiter());
	}
	
	IEnumerator waiter(){
		while(true){
			Entrance.SetActive(false);
			Left.SetActive(true);
			Right.SetActive(true);
			Split.SetActive(false);
			yield return new WaitForSeconds(z);
			
			Entrance.SetActive(true);
			Left.SetActive(true);
			Right.SetActive(true);
			Split.SetActive(false);
			yield return new WaitForSeconds(3);
			
			Entrance.SetActive(true);
			Left.SetActive(false);
			Right.SetActive(false);
			Split.SetActive(true);
			yield return new WaitForSeconds(x);
			
			Entrance.SetActive(true);
			Left.SetActive(true);
			Right.SetActive(true);
			Split.SetActive(false);
			yield return new WaitForSeconds(2);
			
			//put api call here
			
			
			yield return new WaitForSeconds(1);
		}
		
	}
	
	// Update is called once per frame
	void Update(){
        
	}
}
