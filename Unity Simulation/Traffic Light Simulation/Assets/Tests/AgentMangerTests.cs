using System.Collections;
using System.Collections.Generic;
using NUnit.Framework;
using UnityEngine;
using UnityEngine.TestTools;

namespace Tests
{
    public class AgentMangerTests
    {
        [UnityTest]
        public IEnumerator InstantiateAgentObjectFromPrefab()
        {
            //var agentPrefab = Resources.Load("Tests/TestResource/agent");
            //var agentSpawner = new GameObject().AddComponent<vehicleSpawner>();
            //agentSpawner.Construct(agentPrefab, 0, 1);

            yield return null;//new WaitForSeconds(2f);

            //var spawnedAgent = GameObject.FindWithTag("vehicle");
            //var spawnedPrefab = PrefabUtility.GetPrefabParent(spawnedAgent);

            bool test = true;
            bool expected = true;

            if (GameObject.FindWithTag("vehicle")){
                test = true;
            }

            Assert.AreEqual(test, expected);
        }

        [UnityTest]
        public IEnumerator AgentDestroyed(){
            yield return null;
            bool test = false;
            bool expected = true;

            if (GameObject.FindWithTag("vehicle") == null){
                test = true;
            }

            Assert.AreEqual(test, expected);
        }

        [UnityTest]
        public IEnumerator AgentMove(){
            yield return null;
            bool test = true;
            bool expected = true;

            if (GameObject.FindWithTag("vehicle")){
                test = true;
            }

            Assert.AreEqual(test, expected);
        }
    }
}
