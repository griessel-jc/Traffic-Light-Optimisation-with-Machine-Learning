using System.Collections;
using System.Collections.Generic;
using NUnit.Framework;
using UnityEngine;
using UnityEngine.TestTools;
using UnityEngine.Networking;
using Unity.PerformanceTesting;

namespace Tests
{
    public class IntegrationTests
    {
        /*[Test]
        public void IntegrationTestsSimplePasses()
        {
            //Simply to test whether the Integration Test script is working
        }*/

        [UnityTest]
        public IEnumerator IntegrationTestConnectToSpringServer()
        {
            string localSpringServerURL = "127.0.0.1:8080/api/testCall";
            /*get JSON*/
            UnityWebRequest apiRequest = UnityWebRequest.Get(localSpringServerURL);
            yield return apiRequest.SendWebRequest();

            if (apiRequest.isNetworkError || apiRequest.isHttpError)
            {
                UnityEngine.Debug.LogError(apiRequest.error);
                yield break;
            }
            else
            {
                Assert.IsNotNull(apiRequest.downloadHandler.text);
            }
            //yield return null;
        }

        [Test, Performance]
        public void IntegrationTestScalability()
        {
            /*This test spawns many cubes (1000) in the scene to simulate the situation of 
             when the simulation may have many cars on screen at once*/
            Measure.Method(() =>
            {
                for (int i = 0; i < 2; i++) //test takes place 2 seperate times
                {
                    var cube = GameObject.CreatePrimitive(PrimitiveType.Cube);
                    for (var j = 0; j < 1000; j++)//simulate 1000 cars on screen (minus their planes) on screen at once
                    {
                        UnityEngine.Object.Instantiate(cube);
                    }
                }
            })
            .MeasurementCount(10)
            .IterationsPerMeasurement(5)
            .Run();
        }

        /*
        [UnityTest]
        public IEnumerator IntegrationTestAvailablity()
        {
           
        }
        */

        /*
        [Test, Performance]
        public IEnumerator TestFrames()
        {
            return 0;
        }
        */
    }
}
