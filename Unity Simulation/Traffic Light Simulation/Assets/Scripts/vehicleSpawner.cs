using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class vehicleSpawner : MonoBehaviour
{
    public GameObject spawnee;
    public bool stopSpawning = false;
    public float spawnTimeInit;
    public float spawnDelay;
    public bool random = false;
    public float randomStartInit, randomEndInit;
    public float randomStartDelay, randomEndDelay;
    private GameObject go;

    // Start is called before the first frame update
    void Start()
    {
        if (random)
        {
            InvokeRepeating("SpawnObject", Random.Range(randomStartInit, randomEndInit), Random.Range(randomStartDelay, randomEndDelay));
        }else{
            InvokeRepeating("SpawnObject", spawnTimeInit, spawnDelay);
        }
        
    }

    // Update is called once per frame
    void Update()
    {

    }

    public void SpawnObject()
    {
        go = Instantiate(spawnee, transform.position, transform.rotation);
        if (stopSpawning)
        {
            CancelInvoke("SpawnObject");
        }
    }
}
