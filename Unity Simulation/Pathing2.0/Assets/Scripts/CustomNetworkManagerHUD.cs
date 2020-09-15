using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using Mirror;

public class CustomNetworkManagerHUD : NetworkManagerHUD
{
    void OnGUI()
    {
        if (!showGUI)
            return;

        GUILayout.BeginArea(new Rect(10 + offsetX, 30 + offsetY, 215, 9999));
        if (!NetworkClient.isConnected)
        {
            StartButtons();
        }
        else
        {
            StatusLabels();
        }

        // client ready
        if (NetworkClient.isConnected && !ClientScene.ready)
        {
            //if (GUILayout.Button("Client Ready"))
            //{
                ClientScene.Ready(NetworkClient.connection);

                if (ClientScene.localPlayer == null)
                {
                    ClientScene.AddPlayer(NetworkClient.connection);
                }
            //}
        }

        StopButtons();

        GUILayout.EndArea();
    }

    void StartButtons()
    {
        if (!NetworkClient.active)
        {
            TelepathyTransport tpt = GetComponent<TelepathyTransport>();
            // Client + IP
            //GUILayout.BeginHorizontal();
            if (GUILayout.Button("Join AI"))
            {
                //Add right ip with port
                manager.networkAddress = "142.93.139.199";
                tpt.port = 7777;
                manager.StartClient();
            }
            //manager.networkAddress = "142.93.139.199"/*GUILayout.TextField(manager.networkAddress)*/;
            //GUILayout.EndHorizontal();
            if (GUILayout.Button("Join no AI"))
            {
                //Add right ip with port
                manager.networkAddress = "142.93.139.199";
                tpt.port = 7778;
                manager.StartClient();
            }
        }
        else
        {
            // Connecting
            GUILayout.Label("Connecting to " + manager.networkAddress + "..");
            if (GUILayout.Button("Cancel Connection Attempt"))
            {
                manager.StopClient();
            }
        }
    }

    void StatusLabels()
    {
        if (NetworkClient.isConnected)
        {
            GUILayout.Label("Client: address=" + manager.networkAddress);
        }
    }

    void StopButtons()
    {
        if (NetworkClient.isConnected)
        {
            if (GUILayout.Button("Leave simulation"))
            {
                manager.StopClient();
            }
        }
    }
}
