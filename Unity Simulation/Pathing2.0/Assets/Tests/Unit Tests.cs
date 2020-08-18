using System.Collections;
using System.Collections.Generic;
using NUnit.Framework;
using UnityEngine;
using UnityEngine.TestTools;
using UnityEngine.Networking;

namespace Tests
{
    public class UnitTests
    {
        /*[Test]
        public void UnitTestsSimplePasses()
        {
            Assert.IsNotNull(Resources.Load("roads"));
        }*/

        [Test]
        public void UnitTestsLights()
        {
            GameObject trafficLight = MonoBehaviour.Instantiate(Resources.Load<GameObject>("Assets/Prefabs/TrafficLights/Traffic_light_1 Variant.prefab"));
            //GameObject temp = trafficLight.GetComponent<>();
            Assert.IsNotNull(trafficLight);

            //var go = new GameObject("MyGameObject");
            //Assert.AreEqual("MyGameObject", go.name);
        }

        [UnityTest]
        public IEnumerator UnitTestCreateCarPrefab()
        {
            Assert.IsNotNull(Resources.Load("roads"));
            yield return new WaitForSeconds(0.1f);
        }
    }
}
