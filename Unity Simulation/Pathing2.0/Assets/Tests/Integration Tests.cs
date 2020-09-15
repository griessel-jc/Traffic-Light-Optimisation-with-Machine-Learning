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
            string localSpringServerURL = "localhost:8080/api/testCall";
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
        public void Testeeeee()
        {
            Measure.Method(() =>
            {
                for (int i = 0; i < 1000000; i++)
                {
                }
            })
            .MeasurementCount(10)
            .IterationsPerMeasurement(5)
            .Run();
        }
    }
}
