using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.Networking;
using SimpleJSON;
using System.Text;
using System;
public class CommandCenter : MonoBehaviour
{
    IntersectionParent[] intersections;
    private float timeout = 3.5f;
    private float timeleft;
    private readonly static string localSpringServerURL = "localhost:8080/simu/addStatistics"; //needs to be changed when we have a server setup

    [SerializeField]
    public JSONNode apiRequestInfo;

    [SerializeField]
    public string response;

    [SerializeField]
    public string json;
    // Start is called before the first frame update
    void Start()
    {
        GameObject[] temp = GameObject.FindGameObjectsWithTag("Intersection");
        intersections = new IntersectionParent[temp.Length];
        for (int i = 0; i < temp.Length; i++)
        {
            intersections[i] = temp[i].GetComponent<IntersectionParent>();
        }
        reset();
    }

    // Update is called once per frame
    void Update()
    {
        timeleft -= Time.deltaTime;
        if(timeleft <= 0f)
        {
            reset();
            StartCoroutine(Upload());
        }
    } 

    void reset()
    {
        timeleft = timeout;
        Debug.Log("Reseting");
    }

    IEnumerator Upload()
    {
        int i = 0;
        TrafficIntersection obj = null;
        String json = "[";
        for (; i < intersections.Length - 1; i++)
        {
            try
            {
                obj = intersections[i].getIntersection();
            }
            catch (NullReferenceException)
            {
                obj = intersections[i].getIntersection();
            }
            obj.name = "intersection" + (i+1);
            json += obj.toJson(i+1);
            json += ",";
        }
        try
        {
            obj = intersections[i].getIntersection();
        } 
        catch (NullReferenceException)
        {
            obj = intersections[i].getIntersection();
        }
        obj.name = "intersection" + (i+1);
        json += obj.toJson(i+1);
        json += "]";
        Debug.Log("Sending: " + json);
        byte[] bytes = Encoding.UTF8.GetBytes(json);
        UnityWebRequest apiRequest = UnityWebRequest.Put(localSpringServerURL, bytes);//.SetRequestHeader("content-type", "application/json" );
        apiRequest.method = "POST";
        apiRequest.SetRequestHeader("accept", "application/json; charset=UTF-8");
        apiRequest.SetRequestHeader("content-type", "application/json; charset=UTF-8");
        yield return apiRequest.SendWebRequest();

        if (apiRequest.isNetworkError || apiRequest.isNetworkError)
        {
            Debug.LogError(apiRequest.error);
            yield break;
        }
        else
        {
            Debug.Log("response: " + (string)apiRequest.downloadHandler.text);

            for (int j = 0; j < intersections.Length; j++)
            {
                intersections[j].updateTimeOut(apiRequest.downloadHandler.text[j]);
            }
        }
    }
}


[Serializable]
public class TrafficIntersection
{
    public float stationaryX, stationaryY, movingY, movingX;
    public string name;

    public string toJson(int id)
    {
        return "{\"name\":\"" + name + "\"," + 
                "\"intersection_Id\":" + id + "," +
                "\"stationaryY\":" + stationaryY + ","+
                "\"stationaryX\":" + stationaryX + ","+ 
                "\"movingY\":" + movingY + ","+
                "\"movingX\":" + movingX + "}";
    }
}