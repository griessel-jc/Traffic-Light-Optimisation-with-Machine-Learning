using System.Collections;
using UnityEngine;
using Mirror;

public class CustomNetworkManager : NetworkManager
{
    /*public override void Start()
    {
        StartServer();
    }*/

    public override void OnServerAddPlayer(NetworkConnection conn)
    {
        base.OnServerAddPlayer(conn);

        //car = Instantiate(spawnPrefabs.Find(prefab => prefab.name == "car"));
        //NetworkServer.Spawn(car);
    }

    public override void OnServerDisconnect(NetworkConnection conn)
    {
        /*if (car != null)
            NetworkServer.Destroy(car);*/

        base.OnServerDisconnect(conn);
    }
}
