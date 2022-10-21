Item {
  id: myItem
  width: 200
  height: 200

  Rectangle {
    id: myRect
    width: 100
    height: 100
    color: "red"
  }

  element: [
    100,
    myElem,
    State1 {
      name: "moved"
      PropertyChanges {
        target: myRect
        x: 50
        y: 50
      }
    },
    "black"
  ]
 
  MouseArea {
    anchors.fill: parent
    onClicked: myItem.state
  }
}

