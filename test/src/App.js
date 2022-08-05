import './App.css';
import React, { Component } from "react";
import SideButtonsAndStuff from "./SideButtonsAndStuff";
 
class App extends Component {
  render() {
    return (
      <div className="container">
        <h3>React Form - HTML</h3>
        <SideButtonsAndStuff /> 
        <br /> 
      </div> 
    );
  }
}

export default App;
