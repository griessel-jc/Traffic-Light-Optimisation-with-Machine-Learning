using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.AI;

public class pathStart : MonoBehaviour
{
    public GameObject Right, Left, Forward;
    private NavMeshPath path;

    /// <summary>
    /// Start is called on the frame when a script is enabled just before
    /// any of the Update methods is called the first time.
    /// </summary>
    void Start()
    {
        path = new NavMeshPath();
    }

    public Transform getNextPoint(Vector3 target){
        /*NavMesh.CalculatePath(Right.gameObject.transform.position, target, NavMesh.AllAreas, path);
        float right = Right.GetComponent<NavMeshAgent>().remainingDistance;
        NavMesh.CalculatePath(Left.gameObject.transform.position, target, NavMesh.AllAreas, path);
        float left = Left.GetComponent<NavMeshAgent>().remainingDistance;
        NavMesh.CalculatePath(Forward.gameObject.transform.position, target, NavMesh.AllAreas, path);
        float forward = Forward.GetComponent<NavMeshAgent>().remainingDistance;*/

        Right.GetComponent<NavMeshAgent>().destination = target;
        Left.GetComponent<NavMeshAgent>().destination = target;
        Forward.GetComponent<NavMeshAgent>().destination = target;

        float right = Right.GetComponent<NavMeshAgent>().remainingDistance;
        float left = Left.GetComponent<NavMeshAgent>().remainingDistance;
        float forward = Forward.GetComponent<NavMeshAgent>().remainingDistance;

        /*float right = Vector3.Distance(Right.gameObject.transform.position, target);
        float left = Vector3.Distance(Left.gameObject.transform.position, target);
        float forward = Vector3.Distance(Forward.gameObject.transform.position, target);*/

        Debug.Log("right - " + right);
        Debug.Log("left - " + left);
        Debug.Log("forward - " + forward);
        if (right < left && right < forward)
        {
            return Right.transform;
        }else if (left < right && left < forward)
        {
            return Left.transform;
        }else if (forward < right && forward < left)
        {
            return Forward.transform;
        }

        return null;
    }
}
