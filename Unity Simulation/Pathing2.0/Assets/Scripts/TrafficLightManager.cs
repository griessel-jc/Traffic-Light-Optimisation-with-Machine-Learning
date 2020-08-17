using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using Mirror;

public class TrafficLightManager : NetworkBehaviour
{
    [SyncVar]
    public string currentColour;

    public GameObject greenLight;
    public GameObject orangeLight;
    public GameObject redLight;

    /*private void Start()
    {
        redLight.SetActive(false);
        orangeLight.SetActive(false);
        greenLight.SetActive(false);
    }*/

    private void Update()
    {
        if (currentColour == "Red")
        {
            redLight.SetActive(true);
            orangeLight.SetActive(false);
            greenLight.SetActive(false);
        }
        else if (currentColour == "Orange")
        {
            redLight.SetActive(false);
            orangeLight.SetActive(true);
            greenLight.SetActive(false);
        }
        else if (currentColour == "Green")
        {
            redLight.SetActive(false);
            orangeLight.SetActive(false);
            greenLight.SetActive(true);
        }
    }

    public void changeLight(string colour)
    {
        currentColour = colour;
    }
}
