using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class IncomingCounterTest : MonoBehaviour
{
    private int cars;

    private void Start()
    {
        cars = 0;
    }

    private void OnTriggerEnter(Collider other)
    {
        if (other.gameObject.CompareTag("CarSpawned"))
        {
            cars += 1;
        }
    }

    private void OnTriggerExit(Collider other)
    {
        if (other.gameObject.CompareTag("CarSpawned"))
        {
            cars -= 1;
        }
    }
}