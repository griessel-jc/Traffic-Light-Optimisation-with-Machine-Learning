using UnityEngine;
using UnityEngine.AI;

public class agentController : MonoBehaviour
{
    public Camera cam;
    public NavMeshAgent agent;
    //Vector3[] destinationPoint = new [] {new Vector3(2f,1.6f,50.77f), new Vector3(51.561f,1.6f,33f), new Vector3(51.561f,1.6f,0.7f), new Vector3(3.5f,1.6f,-46.84f) };
    Vector3 target;
    float destinationReachedTreshold;
    
    //private Vector3 right, forward, left;
    private Transform[] exitPointList;
    private Vector3[] destinationPoint;
    private bool finalDestinationSet = true;
    private NavMeshPath path;

    /// <summary>
    /// Update is called every frame, if the MonoBehaviour is enabled.
    /// </summary>
    void Update()
    {
        //checkPath();
        //checkDestination();
    }

    /// <summary>
    /// Start is called on the frame when a script is enabled just before
    /// any of the Update methods is called the first time.
    /// </summary>
    void Start()
    {
        path = new NavMeshPath();
        //getExitPointChildren();
        //setAgentDestination();
    }

    void setAgentDestination(){
        target = destinationPoint[Random.Range(1, exitPointList.Length)];
        while (Vector3.Distance(agent.gameObject.transform.position, target) < 10)
        {
            Debug.Log(Vector3.Distance(agent.gameObject.transform.position, target));
            target = destinationPoint[Random.Range(1, exitPointList.Length)];
        }
        agent.destination = target;
    }

    void getExitPointChildren(){
        exitPointList = GameObject.Find("ExitPoints").GetComponent<exitPoints>().getExitPoints();
        destinationPoint = new Vector3[exitPointList.Length];
        for (int i = 1; i < exitPointList.Length; i++)
        {
            destinationPoint[i] = exitPointList[i].position;
        }
    }

    void OnTriggerEnter(Collider other)
    {
        if (other.tag == "stop")
        {
            Debug.Log("stop");       
            agent.isStopped = true;
            Invoke("resume", 1);
        }

        if (other.tag == "path")
        {
            Debug.Log("path start");

            Transform nextPoint = other.gameObject.GetComponent<pathStart>().getNextPoint(target);
            Debug.Log("start");
            Debug.Log(nextPoint);
            Debug.Log("end");
            agent.destination = nextPoint.position;
            finalDestinationSet = false;
            Debug.Log("path end");
        }

        if (other.tag == "despawner"){
            Debug.Log("collided");
            Destroy(gameObject);
        }

        if (other.CompareTag("roadStart")){
            Debug.Log("roadstart");
            target = other.gameObject.GetComponent<roadStart>().getRoadEnd();
            agent.destination = target;
            //NavMesh.CalculatePath(transform.position, target, NavMesh.AllAreas, path);
            //agent.SetPath(path);

        }

        if (other.CompareTag("roadEnd")){
            Debug.Log("roadEnd");
            //left = other.gameObject.GetComponent<roadEnd>().getLeftPoint();
            //right = other.gameObject.GetComponent<roadEnd>().getRightPoint();
            //forward = other.gameObject.GetComponent<roadEnd>().getForwardPoint();
            target = other.gameObject.GetComponent<roadEnd>().getExitPoint();
            if (!NavMesh.CalculatePath(transform.position, target, NavMesh.AllAreas, path)){
                target = other.gameObject.GetComponent<roadEnd>().getExitPoint();
            }
            agent.destination = target;
            //NavMesh.CalculatePath(transform.position, target, NavMesh.AllAreas, path);
            //agent.SetPath(path);
        }
    }

    void resume(){
        Debug.Log("resume");      
        agent.isStopped = false;
    }

    /*void checkPath(){
        if (!agent.pathPending)
        {
            if (agent.remainingDistance <= agent.stoppingDistance)
            {
                if (!agent.hasPath || agent.velocity.sqrMagnitude == 0f)
                {
                    finalDestinationSet = true;
                    agent.destination = target;
                }
            }
        }
    }

    void checkDestination(){
        if (!agent.pathPending)
        {
            if (agent.remainingDistance <= agent.stoppingDistance)
            {
                if (!agent.hasPath || agent.velocity.sqrMagnitude == 0f)
                {
                    if (finalDestinationSet)
                    {
                        Destroy(gameObject);
                    }
                }
            }
        }
    }*/
}
