using System.Collections;
using System.Collections.Generic;
using UnityEngine;

[System.Serializable]
public class spawning : MonoBehaviour
{
    // Start is called before the first frame update
	[SerializeField]
	private GameObject test;
	[SerializeField]
	[Range(1f,1000f)]
	public float speed;

    IEnumerator Start()
    {	
    	while(true){
        GameObject cloneObject = Instantiate(test);
		cloneObject.SetActive(true);
		yield return new WaitForSeconds(10/speed);
		}
    }

    // Update is called once per frame
    void Update()
    {
        
    }
}
