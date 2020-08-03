using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using Mirror;

[System.Serializable]
public class spawning : NetworkBehaviour{

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
            NetworkServer.Spawn(cloneObject);
            yield return new WaitForSeconds(60/speed);
		}
    }

    // Update is called once per frame
    void Update()
    {
        
    }
}
