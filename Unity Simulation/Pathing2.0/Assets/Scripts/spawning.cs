using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using Mirror;

[System.Serializable]
public class spawning : NetworkBehaviour{

    // Start is called before the first frame update

	[SerializeField]
	[Range(1f,1000f)]
	public float speed;

    private GameObject car;

    IEnumerator Start()
    {	
    	while(true){
            car = Instantiate(GameObject.Find("NetworkManager").GetComponent<NetworkManager>().spawnPrefabs.Find(prefab => prefab.name == "car"));
            NetworkServer.Spawn(car);
            yield return new WaitForSeconds(60/speed);
        }
    }

    // Update is called once per frame
    void Update()
    {
        
    }
}
