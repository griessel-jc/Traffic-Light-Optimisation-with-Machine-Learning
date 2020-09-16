using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class DataPanelManager : MonoBehaviour
{
    [SerializeField]
    public GameObject[] dataPanels;

    void Start()
    {
        for (int i = 0; i < dataPanels.Length; i++)
        {
            dataPanels[i].SetActive(false);
        }
    }
}
