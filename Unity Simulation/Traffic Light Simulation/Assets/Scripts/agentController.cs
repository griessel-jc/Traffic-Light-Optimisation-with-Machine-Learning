using UnityEngine;
using UnityEngine.AI;

public class agentController : MonoBehaviour
{
    public Transform goal;
    public Camera cam;
    public NavMeshAgent agent;

    Vector3 target;
    float destinationReachedTreshold;
    Vector3[] destinationPoint = new [] {new Vector3(2f,1.6f,50.77f), new Vector3(51.561f,1.6f,33f), new Vector3(51.561f,1.6f,0.7f), new Vector3(3.5f,1.6f,-46.84f) };

    // Update is called once per frame
    void Update()
    {
        /*if (Input.GetMouseButtonDown(0)){
            Ray ray = cam.ScreenPointToRay(Input.mousePosition);
            RaycastHit hit;

            if (Physics.Raycast(ray, out hit)){
                agent.SetDestination(hit.point);
                agent.isStopped = false;
            }
        }else if (Input.GetMouseButtonDown(1)){
            if (agent.isStopped == true)
                agent.isStopped = false;
            else if (agent.isStopped == false)
                agent.isStopped = true;
        }*/
        
        checkDestination();

    }

    void Start()
    {
        //Debug.Log(goal.position);
        //agent.destination = goal.position; 

        target = destinationPoint[Random.Range(0, 4)];
        agent.destination = target;
    }

    void OnTriggerEnter(Collider other)
    {
        if (other.tag == "stop")
        {
            Debug.Log("stop");       
            agent.isStopped = true;
            Invoke("resume", 1);
        }        
    }

    void resume(){
        Debug.Log("resume");      
        agent.isStopped = false;
    }

    void checkDestination(){
        if (!agent.pathPending)
        {
            if (agent.remainingDistance <= agent.stoppingDistance)
            {
                if (!agent.hasPath || agent.velocity.sqrMagnitude == 0f)
                {
                    Debug.Log("complete");
                    Destroy(gameObject);
                }
            }
        }
    }
}
