using System.Collections;
using System.Collections.Generic;
using NUnit.Framework;
using UnityEngine;
using UnityEngine.TestTools;

namespace Tests
{
    public class IntersectionControllerTests
    {
        // A Test behaves as an ordinary method
        [Test]
        public void IntersectionTest()
        {
            // Use the Assert class to test conditions
        }
	[Test]
	public void TSectionTest()
        {
            // Use the Assert class to test conditions
        }

        // A UnityTest behaves like a coroutine in Play Mode. In Edit Mode you can use
        // `yield return null;` to skip a frame.
        [UnityTest]
        public IEnumerator IntersectionTestEnumerator()
        {
		yield return null;
		bool test = false;
		bool expected = true;

		if (true){
			test = true;
		}

		Assert.AreEqual(test, expected);
        }
	
	[UnityTest]
        public IEnumerator TSectionTestEnumerator()
        {
		yield return null;
		bool test = false;
		bool expected = true;

		if (true){
			test = true;
		}

		Assert.AreEqual(test, expected);
        }
    }
}
