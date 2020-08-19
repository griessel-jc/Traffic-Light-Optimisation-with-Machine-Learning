using UnityEngine;
using TMPro;

public class IntersectionSelection : MonoBehaviour
{
    public TextMeshProUGUI UI_intersection;

    /*private void OnMouseDown() {
        UI_intersection.text = this.name;
    }*/

    private void OnMouseEnter() {
        UI_intersection.text = this.name;
    }

    private /// <summary>
    /// Called when the mouse is not any longer over the GUIElement or Collider.
    /// </summary>
    void OnMouseExit()
    {
        UI_intersection.text = "";
    }
}
