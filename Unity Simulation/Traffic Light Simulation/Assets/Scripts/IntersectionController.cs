using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class IntersectionController : MonoBehaviour
{	
	public GameObject North;
	public GameObject South;
	public GameObject East;
	public GameObject West;
	public GameObject WestEastSplit;
	public GameObject NorthSouthSplit;
	
	public GameObject Lnorth;
	public GameObject Lsouth;
	public GameObject Least;
	public GameObject Lwest;
	
	
	
	// Start is called before the first frame update
	void Start(){
		StartCoroutine(waiter());
	}
	
	IEnumerator waiter(){
		while(true){
			North.SetActive(false);
			South.SetActive(false);
			East.SetActive(true);
			West.SetActive(true);
			WestEastSplit.SetActive(false);
			NorthSouthSplit.SetActive(true);
			yield return new WaitForSeconds(10);
			
			North.SetActive(true);
			South.SetActive(true);
			East.SetActive(true);
			West.SetActive(true);
			WestEastSplit.SetActive(false);
			NorthSouthSplit.SetActive(false);
			yield return new WaitForSeconds(3);
			
			North.SetActive(true);
			South.SetActive(true);
			East.SetActive(false);
			West.SetActive(false);
			WestEastSplit.SetActive(true);
			NorthSouthSplit.SetActive(false);
			yield return new WaitForSeconds(10);
			
			North.SetActive(true);
			South.SetActive(true);
			East.SetActive(true);
			West.SetActive(true);
			WestEastSplit.SetActive(false);
			NorthSouthSplit.SetActive(false);
			yield return new WaitForSeconds(3);
		}
		
	}
	
	void toGreen(GameObject x){
			GameObject green = x.transform.GetChild (1).gameObject.transform.GetChild (1).gameObject;
		}
	
	// Update is called once per frame
	void Update(){
        
	}
}
